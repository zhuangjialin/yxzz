package yxzz.mapper;

import java.util.List;
import java.util.Map;

import yxzz.dto.AreaDto;

public interface AreaMapper {
	
	
	List<AreaDto> findAreaList(Map<Object, Object> condition);

	long addArea(Map<Object, Object> condition);

	AreaDto findAreaById(String areaId);

	long editAreaById(Map<Object, Object> condition);

	long deleteArea(String areaId);
	
	

}
