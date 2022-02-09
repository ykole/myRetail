package com.target.redsky.myRetail.service;

import java.util.HashMap;
import java.util.Map;

public interface APIService {

	public boolean checkHealth(long id);
	public String getProductName(long id);
	public String parseProductName(Map<String, Map> resultMap);
	
}
