package com.inconcept.task.service.utils;

import com.inconcept.task.service.dto.BookDto;
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
public class BookCSVParser {
    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel");
    }

    public List<BookDto> parseBookCsv(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT
                             .withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<BookDto> developerTutorialList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                BookDto bookDto = new BookDto(
                        csvRecord.get("Book-Title"),
                        csvRecord.get("Year-Of-Publication"),
                        csvRecord.get("Publisher"),
                        csvRecord.get("Image-URL-S")
                );
                bookDto.getBookAuthors().add(csvRecord.get("Book-Author"));
                developerTutorialList.add(bookDto);
            }
            return developerTutorialList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
