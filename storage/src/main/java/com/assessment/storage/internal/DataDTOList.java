package com.assessment.storage.internal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "dataList")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataDTOList {
    @XmlElement(name = "data")
    private List<DataDTO> dataList = new ArrayList<>();

    public List<DataDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataDTO> dataList) {
        this.dataList = dataList;
    }
}