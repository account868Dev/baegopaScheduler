package com.high.baegopa.jobs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.google.common.collect.Maps;
import com.high.baegopa.common.helpers.ApplicationJobs;
import com.high.baegopa.common.helpers.GooglePlaceHelper;
import com.high.baegopa.common.helpers.HttpClientProxy;
import com.high.baegopa.common.helpers.JsonHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * Created by high on 2017. 7. 8..
 */
@Slf4j
public class PlaceApiJob implements ApplicationJobs{

    @Override
    public void OnStart() throws Exception {

    }

    public void setRestauranttNearsearch(){
        String url = GooglePlaceHelper.getNearsearch(37.566535, 126.9779692);

        JsonNode placeJson = HttpClientProxy.httpRequestToJsonNode(url).get("results");
        placeJson.forEach( place -> {
            Map<String, Object> map = Maps.newHashMap();
            place.get("place_id");
            place.get("name");
            place.get("photos").get(0).get("photo_reference");
            place.get("price_level");
            place.get("rating");
            Optional.ofNullable(getLatLong(place.get("geometry")))
                    .ifPresent( geo -> {
                        map.put("latitude", geo.get("lat"));
                        map.put("longtitude", geo.get("lng"));
                    });
        });
    }

    private Map<String, Object> getLatLong(JsonNode geo) {
        log.info("geoMetry : " + geo);
        return JsonHelper.jsonToMap(geo.get("location").toString());
    }
}
