package com.assessment.files.controller.dto;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class DataDTOList {

    private List<DataDTO> dataList = new ArrayList<>();

    public List<DataDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataDTO> dataList) {
        this.dataList = dataList;
    }
}