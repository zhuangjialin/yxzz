package yxzz.service;

import java.util.Map;



public interface IPropertyService {
	
	
	Map<Object, Object> findPropertyList(Map<Object, Object> condition);

	Map<Object, Object> addProperty(Map<Object, Object> condition);

	Map<Object, Object> findPropertyById(String propertyId);

	Map<Object, Object> editPropertyById(Map<Object, Object> condition);

	Map<Object, Object> deleteProperty(Map<Object, Object> condition);
	
	
}
