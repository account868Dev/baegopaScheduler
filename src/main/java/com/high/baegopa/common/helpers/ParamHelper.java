package com.high.baegopa.common.helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

public class ParamHelper {

	private Map<String, Object> param = Maps.newHashMap();

	public static ParamHelper getInstance() {
		return new ParamHelper();
	}

	public ParamHelper put(String key, Object value) {
		this.param.put(key, value);
		return this;
	}

	public ParamHelper putAll(Map<? extends String, ? extends Object> resultMap) {
		this.param.putAll(resultMap);
		return this;
	}

	public Map<String, Object> getMap() {
		return param;
	}

	public HashMap<String, Object> getHashMap() {
		return (HashMap<String, Object>) param;
	}

	public MultiValueMap<String, Object> getMultiValueMap() {
		MultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();
		param.entrySet().forEach(entry -> result.add(entry.getKey(), entry.getValue()));
		return result;
	}

	public JsonNode toJson() {
		return JsonHelper.objectToJsonNode(param);
	}

	@Override
	public String toString() {
		return param.toString();
	}
}
