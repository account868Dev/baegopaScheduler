package com.high.baegopa.jobs;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import com.high.baegopa.common.helpers.ApplicationJobs;
import com.high.baegopa.common.helpers.GooglePlaceHelper;
import com.high.baegopa.common.helpers.HttpClientProxy;
import com.high.baegopa.common.helpers.JsonHelper;
import com.high.baegopa.common.helpers.LocationHelper;
import com.high.baegopa.mapper.master.LocationMapper;
import com.high.baegopa.models.GeoMetry;
import com.high.baegopa.models.LocationCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.List;
import java.util.Optional;

/**
 * Created by high on 2017. 7. 8..
 */
@Slf4j
@Service
public class LocationApiJob implements ApplicationJobs {

    @Autowired private LocationMapper locationMapper;

    @Override
    public void OnStart() throws Exception {

    }

    @Transactional
    public void setLocation(){
        JsonNode locationJson = HttpClientProxy.httpRequestToJsonNode(LocationHelper.areaCodeUrl(20, 1))
                .get("response").get("body").get("items").get("item");

        log.info("jsonNode : " +locationJson );
        JsonHelper.jsonNodeToList(locationJson , LocationCode.class)
                .forEach( location -> {
                   Map<String, Object> map = Maps.newHashMap();
                   map.put("id", location.getCode());
                   map.put("parent_id", location.getCode());
                   map.put("type", "D");
                   map.put("city_name", location.getName());
                   String geoUrl = String.format(GooglePlaceHelper.GEO_METRY, location.getName());
                   log.info("geoUrl : " + geoUrl);

                   JsonNode geoJson = HttpClientProxy.httpRequestToJsonNode(geoUrl).get("results");
                   log.info("geoJson : " + geoJson);

                   Optional.ofNullable(getLatLong(geoJson.get(0).get("geometry")))
                            .ifPresent( geo -> {
                                map.put("latitude", geo.get("lat"));
                                map.put("longtitude", geo.get("lng"));
                            });

                   log.info("data : " + map.toString());
                   locationMapper.setLocation(map);
               });
    }

    @Transactional
    public void setLocationArea(){
        for(int j=1; j<5; j++){
            for(int i =1; i<40; i++ ){
                if( i == 8) i = 31;
                final int y = i;

                Optional.ofNullable(HttpClientProxy.httpRequestToJsonNode(LocationHelper.areaLocalCodeUrl(20, j, i))
                        .get("response").get("body").get("items").get("item"))
                        .ifPresent( locationJson -> {
                            JsonHelper.jsonNodeToList(locationJson , LocationCode.class)
                                    .forEach( location -> {
                                        Map<String, Object> map = Maps.newHashMap();
                                        map.put("id", y + "0" + location.getCode());
                                        map.put("parent_id", y);
                                        map.put("type", "C");
                                        map.put("city_name", location.getName());
                                        String geoUrl = String.format(GooglePlaceHelper.GEO_METRY, location.getName());
                                        log.info("geoUrl : " + geoUrl);

                                        JsonNode geoJson = HttpClientProxy.httpRequestToJsonNode(geoUrl).get("results");
                                        log.info("geoJson : " + geoJson);

                                        Optional.ofNullable(getLatLong(geoJson.get(0).get("geometry")))
                                                .ifPresent( geo -> {
                                                    map.put("latitude", geo.get("lat"));
                                                    map.put("longtitude", geo.get("lng"));
                                                });

                                        log.info("data : " + map.toString());
                                        locationMapper.setLocation(map);
                                    });
                        });

            }
        }
    }

    private Map<String, Object> getLatLong(JsonNode geo) {
        log.info("geoMetry : " + geo);
        return JsonHelper.jsonToMap(geo.get("location").toString());
    }
}
