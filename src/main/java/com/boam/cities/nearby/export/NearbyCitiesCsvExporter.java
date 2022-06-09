package com.boam.cities.nearby.export;

import com.boam.cities.CsvFileWriter;
import com.boam.cities.nearby.NearbyCities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class NearbyCitiesCsvExporter {
    public File export(List<NearbyCities> nearbyCities) {
        String[] header = header(nearbyCities.stream().findFirst().orElseThrow().nearbyCities.size());
        List<String[]> content = nearbyCities.stream().map(NearbyCities::csvLine).collect(toList());

        return new CsvFileWriter().write(header, content);
    }

    public String[] header(int numberOfNearbyCities) {
        List<String> header = new ArrayList<>();
        header.add("name");
        for (int i = 1; i <= numberOfNearbyCities; i++) {
            header.add("nearby city " + i);
        }
        return header.toArray(new String[numberOfNearbyCities + 1]);
    }

}
