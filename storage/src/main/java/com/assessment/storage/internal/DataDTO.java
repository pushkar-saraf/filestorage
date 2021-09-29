package com.assessment.storage.internal;


import com.assessment.storage.util.LocalDateAdapter;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataDTO {

    private String name;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dob;

    private BigDecimal salary;

    private Integer age;
}
