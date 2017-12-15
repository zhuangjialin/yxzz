package yxzz.service;

import java.util.Map;



public interface ISubwayService {
	
	
	Map<Object, Object> findSubwayList(Map<Object, Object> condition);

	Map<Object, Object> addSubway(Map<Object, Object> condition);

	Map<Object, Object> findSubwayById(String areaId);

	Map<Object, Object> editSubwayById(Map<Object, Object> condition);

	Map<Object, Object> deleteSubway(String areaId);

	
	
	
	
	

}
