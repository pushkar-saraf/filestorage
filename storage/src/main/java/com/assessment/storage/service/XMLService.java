package com.assessment.storage.service;

import com.assessment.storage.internal.DataDTO;
import com.assessment.storage.internal.DataDTOList;
import org.springframework.stereotype.Service;

import javax.xml.bind.*;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("XML")
public class XMLService implements FileService {

    @Override
    public void upsert(DataDTO dto) throws JAXBException {
        List<DataDTO> list = readAll();
        list.add(dto);
        writeList(list);
    }

    @Override
    public Optional<DataDTO> read(String name) throws JAXBException {
        return readAll().stream().filter(dto -> dto.getName().equals(name)).findAny();
    }

    @Override
    public List<DataDTO> readAll() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataDTOList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        DataDTOList dataDTOList;
        try {
            dataDTOList = (DataDTOList) jaxbUnmarshaller.unmarshal(new File("db/data.xml"));
        } catch (UnmarshalException ex) {
            dataDTOList = new DataDTOList();
        }
        return dataDTOList.getDataList();
    }

    @Override
    public void remove(String name) throws JAXBException {
        List<DataDTO> list = readAll().stream().filter(data -> !data.getName().equals(name)).collect(Collectors.toList());
        writeList(list);
    }

    private void writeList(List<DataDTO> list) throws JAXBException {
        DataDTOList dataDTOList = new DataDTOList();
        dataDTOList.setDataList(list);
        JAXBContext jaxbContext = JAXBContext.newInstance(DataDTOList.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(dataDTOList, new File("db/data.xml"));
    }
}
