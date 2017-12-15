package yxzz.mapper;

import java.util.List;
import java.util.Map;

import yxzz.dto.PropertyImgDto;

public interface PropertyImgMapper {
	
	
	List<PropertyImgDto> findPropertyImgList(Map<Object, Object> condition);

	long addPropertyImg(Map<Object, Object> condition);

	PropertyImgDto findPropertyImgById(String propertyImgId);

	long editPropertyImgById(Map<Object, Object> condition);

	long deletePropertyImg(String propertyImgId); 
	
	

}
