package com.assessment.storage.service;

import com.assessment.storage.internal.DataDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {

    void upsert(DataDTO dto) throws IOException, JAXBException;

    Optional<DataDTO> read(String name) throws IOException, JAXBException;

    List<DataDTO> readAll() throws IOException, JAXBException;

    void remove(String name) throws IOException, JAXBException;
}
