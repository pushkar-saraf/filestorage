package com.assessment.storage.controller;

import com.assessment.storage.internal.DataDTOList;
import com.assessment.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("storage/")
@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    @GetMapping
    public ResponseEntity<DataDTOList> getData() {
        DataDTOList dataDTOList = new DataDTOList();
        dataDTOList.setDataList(storageService.readAll());
        return ResponseEntity.ok(dataDTOList);
    }
}
