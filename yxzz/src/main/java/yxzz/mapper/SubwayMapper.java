package yxzz.mapper;

import java.util.List;
import java.util.Map;

import yxzz.dto.SubwayDto;

public interface SubwayMapper {
	
	
	List<SubwayDto> findSubwayList(Map<Object, Object> condition);

	long addSubway(Map<Object, Object> condition);

	SubwayDto findSubwayById(String subwayId);

	long editSubwayById(Map<Object, Object> condition);

	long deleteSubway(String subwayId);
	
	

}
