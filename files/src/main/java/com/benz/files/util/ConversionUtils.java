package com.benz.files.util;

import com.benz.files.controller.dto.DataDTO;
import com.benz.files.controller.dto.DataDTOBuf;

public class ConversionUtils {
    public static DataDTOBuf.Data toBuf(DataDTO dto){
        return DataDTOBuf.Data.newBuilder()
                .setAge(dto.getAge())
                .setDob(dto.getDob().toEpochDay())
                .setName(dto.getName())
                .setSalary(dto.getSalary().longValue())
                .build();
    }
}
