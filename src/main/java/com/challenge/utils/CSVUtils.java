package com.challenge.utils;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class CSVUtils {

    private final static String PATH_FILE = "src/main/resources/logs_records/";
    private static final String FILE_NAME = "log-service.csv";

    private void writeICSVWriter(String[] message) throws IOException {

        try (ICSVWriter writer = new CSVWriterBuilder(
                new FileWriter(PATH_FILE + FILE_NAME, true))
                .withLineEnd(ICSVWriter.DEFAULT_LINE_END)
                .build()) {
            writer.writeAll(Collections.singleton(message));
        }
    }

    public void writeMessage(List<String> messages) throws IOException {
        final String[] toArray = messages.toArray(String[]::new);
        writeICSVWriter(toArray);
    }

}
