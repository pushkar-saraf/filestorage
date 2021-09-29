package com.assessment.storage.service;

import com.assessment.storage.internal.DataDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("CSV")
public class CSVService implements FileService {

    @Override
    public void upsert(DataDTO dto) throws IOException {
        List<DataDTO> values = readAll().stream().filter(val -> !val.getName().equals(dto.getName())).collect(Collectors.toList());
        File file = new File("db/data.csv");
        try (
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().build());
        ) {
            values.forEach(value -> printDTO(value, csvPrinter));
            printDTO(dto, csvPrinter);
            csvPrinter.flush();
        }
    }

    private void printDTO(DataDTO dto, CSVPrinter csvPrinter) {
        String dob = String.format("%d/%d/%d", dto.getDob().getDayOfMonth(), dto.getDob().getMonth().getValue(), dto.getDob().getYear());
        try {
            csvPrinter.printRecord(dto.getName(), dto.getSalary(), dto.getAge(), dob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<DataDTO> read(String name) throws IOException {
        return readAll().stream().filter(val -> val.getName().equals(name)).findAny();
    }

    @Override
    public List<DataDTO> readAll() throws IOException {
        File file = new File("db/data.csv");
        try (Reader reader = new FileReader(file.getAbsoluteFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.builder()
                     .setIgnoreHeaderCase(true)
                     .setTrim(true)
                     .build());
        ) {
            List<DataDTO> result = new ArrayList<>();
            csvParser.getRecords().forEach(record -> {
                DataDTO dataDTO = new DataDTO();
                dataDTO.setName(record.get(0));
                dataDTO.setSalary(new BigDecimal(record.get(1)));
                dataDTO.setAge(Integer.valueOf(record.get(2)));
                String[] dobArgs = record.get(3).split("/");
                dataDTO.setDob(LocalDate.of(Integer.parseInt(dobArgs[2]), Integer.parseInt(dobArgs[1]), Integer.parseInt(dobArgs[0])));
                result.add(dataDTO);
            });
            return result;
        }
    }

    @Override
    public void remove(String name) throws IOException {
        List<DataDTO> values = readAll().stream().filter(val -> !val.getName().equals(name)).collect(Collectors.toList());
        File file = new File("db/data.csv");
        try (
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), false);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder().build());
        ) {
            values.forEach(value -> printDTO(value, csvPrinter));
            csvPrinter.flush();
        }
    }
}
