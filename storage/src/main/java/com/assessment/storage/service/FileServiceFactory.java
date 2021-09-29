package com.assessment.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory {

    @Autowired
    private XMLService xmlService;

    @Autowired
    private CSVService csvService;

    public FileService getInstance(FileType fileType) {
        switch (fileType) {
            case CSV:
                return csvService;
            case XML:
                return xmlService;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
