package com.inconcept.task.controller;

import com.inconcept.task.persistence.entity.UserEntity;
import com.inconcept.task.service.AuthorService;
import com.inconcept.task.service.UserService;
import com.inconcept.task.service.criteria.SearchCriteria;
import com.inconcept.task.service.dto.UserDto;
import com.inconcept.task.service.model.ContentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ContentQuery<UserDto> getUsers(SearchCriteria searchCriteria) {
        return userService.getUsers(searchCriteria);
    }


    @PostMapping()
    public ResponseEntity<UserDto> registerUser(@RequestBody UserEntity userEntity) throws Exception {
        UserDto userDto = userService.registerUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable() Long id) throws Exception {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) throws Exception {
        if (id == null) {
            throw new Exception("Id cannot be null");
        }
        userService.deleteById(id);
    }

    @PostMapping("/uploadCsv")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new Exception("File cannot be null");
        }
        userService.saveUserCsv(file);
        return ResponseEntity.ok("Uploaded the file successfully");
    }
}
