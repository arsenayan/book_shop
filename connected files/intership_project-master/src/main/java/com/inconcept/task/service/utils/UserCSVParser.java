package com.inconcept.task.service.utils;

import com.inconcept.task.persistence.entity.enums.UserEnum;
import com.inconcept.task.service.dto.BookDto;
import com.inconcept.task.service.dto.UserDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserCSVParser {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        if (TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public List<UserDto> parseUserCsv(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT
                             .withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<UserDto> userDtoList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                UserDto userDto = new UserDto(
                        csvRecord.get("First Name"),
                        csvRecord.get("Last Name"),
                        csvRecord.get("Email"),
                        csvRecord.get("Status"),
                        UserEnum.valueOf(csvRecord.get("Type")),
                        csvRecord.get("Location")
                );
                userDtoList.add(userDto);
            }
            return userDtoList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
