package yxzz.mapper;

import java.util.List;
import java.util.Map;

import yxzz.dto.AreaDto;
import yxzz.dto.SubwaylineDto;

public interface SubwaylineMapper {
	
	
	List<SubwaylineDto> findSubwaylineList(Map<Object, Object> condition);

	long addSubwayline(Map<Object, Object> condition);

	AreaDto findSubwaylineById(String subwaylineId);

	long editSubwaylineById(Map<Object, Object> condition);

	long deleteSubwayline(String subwaylineId);
	
	

}
