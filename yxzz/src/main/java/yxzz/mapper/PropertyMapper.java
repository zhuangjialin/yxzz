package yxzz.mapper;

import java.util.List;
import java.util.Map;

import yxzz.dto.PropertyDto;

public interface PropertyMapper {

	List<PropertyDto> findPropertyList(Map<Object, Object> condition);

	long addProperty(Map<Object, Object> condition);

	PropertyDto findPropertyById(String propertyId);

	long editPropertyById(Map<Object, Object> condition);

	long deleteProperty(String propertyId);

	long addPropertySubway(Map<Object, Object> condition);

	long editSubwayPropertyById(Map<Object, Object> condition);

	long deleteSubwayProperty(String id);

	long addSubwaylineSubway(Map<Object, Object> condition);

	long addAreaSubway(Map<Object, Object> condition);

}
