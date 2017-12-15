package yxzz.service;

import java.util.Map;



public interface ISubwaylineService {
	
	
	Map<Object, Object> findSubwaylineList(Map<Object, Object> condition);

	Map<Object, Object> addSubwayline(Map<Object, Object> condition);

	Map<Object, Object> findSubwaylineById(String areaId);

	Map<Object, Object> editSubwaylineById(Map<Object, Object> condition);

	Map<Object, Object> deleteSubwayline(Map<Object, Object> condition);


}
