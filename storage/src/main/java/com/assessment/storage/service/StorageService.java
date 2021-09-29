package com.assessment.storage.service;

import com.assessment.storage.internal.DataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StorageService {

    @Autowired
    FileServiceFactory fileServiceFactory;

    public void save(DataDTO dto, FileType fileType) {
        Arrays.stream(FileType.values()).forEach(type -> {
            try {
                fileServiceFactory.getInstance(type).remove(dto.getName());
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        });
        try {
            fileServiceFactory.getInstance(fileType).upsert(dto);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<DataDTO> readAll() {
        List<DataDTO> list = new ArrayList<>();
        Arrays.stream(FileType.values()).forEach(type -> {
            try {
                list.addAll(fileServiceFactory.getInstance(type).readAll());
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        });
        return list;
    }
}
