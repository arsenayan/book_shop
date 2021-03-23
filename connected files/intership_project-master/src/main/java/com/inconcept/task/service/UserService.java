package com.inconcept.task.service;


import com.inconcept.task.persistence.entity.LocationEntity;
import com.inconcept.task.persistence.entity.UserEntity;
import com.inconcept.task.persistence.entity.enums.UserEnum;
import com.inconcept.task.persistence.repository.LocationRepository;
import com.inconcept.task.persistence.repository.UserRepository;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.UserDto;
import com.inconcept.task.service.model.ContentQuery;
import com.inconcept.task.service.utils.BookCSVParser;
import com.inconcept.task.service.utils.UserCSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.Location;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCSVParser userCSVParser;
    private final LocationRepository locationRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserCSVParser userCSVParser, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.userCSVParser = userCSVParser;
        this.locationRepository = locationRepository;
    }

    public ContentQuery<UserDto> getUsers(SearchCriteria searchCriteria) {

        Page<UserEntity> userDtoPage = userRepository.findAllWithPage(searchCriteria.composePageRequest());
        return new ContentQuery<UserDto>(userDtoPage.getTotalPages(), UserDto.castEntityToDo(userDtoPage.getContent()));

    }


    public UserDto registerUser(UserEntity userEntity) throws Exception {
        if (userRepository.existsUserByEmail(userEntity.getEmail())) {
            throw new Exception("Email is busy");
        }
        if (userEntity.getFirstName() == null || userEntity.getLastName() == null || userEntity.getEmail() == null || userEntity.getPassword() == null) {
            throw new Exception("Fill required fields");
        }


        if (userEntity.getLocation() != null) {
            LocationEntity locationEntity = locationRepository.findOneByCity(userEntity.getLocation().getCity());
            if (locationEntity == null) {
                locationEntity = new LocationEntity();
                locationEntity.setCity(userEntity.getLocation().getCity());
                int random_number = 9999 + (int) (Math.random() * ((99999 - 9999) + 1));
                locationEntity.setPostalCode(random_number);
            }
            locationRepository.save(locationEntity);
            userEntity.setLocation(locationEntity);
        }

        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        userEntity.setStatus("ACTIVE");
        userEntity.setUserEnum(UserEnum.USER);


        userRepository.save(userEntity);
        return UserDto.castEntityToDo(userEntity);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("User which id %d not found", id)));
        return UserDto.castEntityToDo(userEntity);
    }

    public void deleteById(Long id) throws Exception {
        userRepository.findById(id)
                .orElseThrow(() -> new Exception(String.format("User which id %d not found", id)));
        userRepository.deleteById(id);
    }

    public void saveUserCsv(MultipartFile multipartFile) throws Exception {
        if (BookCSVParser.hasCSVFormat(multipartFile)) {
            throw new Exception("File should be csv extension");
        }
        try {
            List<UserDto> userDtoList = userCSVParser.parseUserCsv(multipartFile.getInputStream());

            List<String> cityList = userDtoList.stream().map(UserDto::getLocationCity).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(cityList)) {
                List<LocationEntity> locationList = new ArrayList<>();
                for (String s : cityList) {
                    LocationEntity location = new LocationEntity();
                    location.setCity(s);
                    location.setPostalCode(new Random().nextInt(7));
                    locationList.add(location);
                }
                locationRepository.saveAll(locationList);
            }
            userRepository.saveAll(UserDto.castDtoToEntity(userDtoList));
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
