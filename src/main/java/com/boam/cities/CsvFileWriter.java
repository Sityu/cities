package com.boam.cities;

import com.opencsv.CSVWriter;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class CsvFileWriter {
    private static final Logger LOGGER = getLogger(CsvFileWriter.class);

    public File write(String[] header, List<String[]> content) {
        String csvFileName = "nearby-cities.csv";
        LOGGER.info("export started...");
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvFileName));
            writer.writeNext(header);
            content.forEach(writer::writeNext);
            writer.close();
            LOGGER.info("export finished");
            return new File(csvFileName);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
