package com.assessment.storage.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String v) {
        int[] args = Arrays.stream(v.split("/")).mapToInt(Integer::valueOf).toArray();
        return LocalDate.of(args[2], args[1], args[0]);
    }

    public String marshal(LocalDate v) {
        return String.format("%s/%s/%s", v.getDayOfMonth(), v.getMonth().getValue(), v.getYear());
    }
}