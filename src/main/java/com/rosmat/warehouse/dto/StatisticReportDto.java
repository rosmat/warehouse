package com.rosmat.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticReportDto {
    private String name;
    private Number value;

    public static String getValueFieldName() {
        return "value";
    }
}
