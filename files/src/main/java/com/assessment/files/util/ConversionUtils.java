package com.assessment.files.util;


import com.assessment.files.controller.dto.DataDTO;
import com.assessment.files.controller.dto.DataDTOBuf;
import com.assessment.files.controller.dto.FileType;

public class ConversionUtils {
    public static DataDTOBuf.Data toBuf(DataDTO dto, FileType fileType) {
        return DataDTOBuf.Data.newBuilder()
                .setAge(dto.getAge())
                .setDob(String.format("%d,%d,%d", dto.getDob().getDayOfMonth(), dto.getDob().getMonth().getValue(), dto.getDob().getYear()))
                .setName(dto.getName())
                .setSalary(dto.getSalary().longValue())
                .setFormat(fileType.name())
                .build();
    }
}
