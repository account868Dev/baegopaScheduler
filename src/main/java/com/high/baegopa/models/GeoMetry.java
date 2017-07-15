package com.high.baegopa.models;

import java.util.List;
import lombok.Data;

import java.util.Map;

/**
 * Created by high on 2017. 7. 9..
 */
@Data
public class GeoMetry {
    private List<Map<String, Object>> results;
    private String status;
}
