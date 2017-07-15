package com.high.baegopa.mapper.master;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by high on 2017. 7. 8..
 */
@Mapper
public interface LocationMapper {
    public void setLocation(Map<String, Object> param);
}
