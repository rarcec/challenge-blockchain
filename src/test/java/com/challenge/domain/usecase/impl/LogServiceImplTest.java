package com.challenge.domain.usecase.impl;

import com.google.common.hash.Hashing;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LogServiceImplTest {

    @Test
    void createCSVFile() throws IOException {
        String fileName = "src/main/resources/logs_records/some_file.csv";
        CSVPrinter csvPrinter;
        CSVFormat csvFormat = CSVFormat.EXCEL;
        FileWriter fileWriter = new FileWriter(fileName);
        csvPrinter = new CSVPrinter(fileWriter, csvFormat);

        csvPrinter.printRecord("HASH");
        fileWriter.flush();
        fileWriter.close();
        csvPrinter.close();
    }

    @Test
    void createHash() {
        String random = RandomStringUtils.randomAlphanumeric(256);
        StringBuilder builder = new StringBuilder()
                .append(random)
                .append(",")
                .append("Hola Mundo")
                .append(",")
                .append(10);
        final String hash = Hashing.sha256().hashString(builder.toString(), StandardCharsets.UTF_8).toString();
        System.out.println(hash);
    }
}