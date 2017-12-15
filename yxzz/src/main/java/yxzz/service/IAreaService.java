package yxzz.service;

import java.util.Map;


public interface IAreaService {
	
	
	Map<Object, Object> findAreaList(Map<Object, Object> condition);

	Map<Object, Object> addArea(Map<Object, Object> condition);

	Map<Object, Object> findAreaById(String areaId);

	Map<Object, Object> editAreaById(Map<Object, Object> condition);

	Map<Object, Object> deleteArea(String areaId);
	

}
