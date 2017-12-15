package yxzz.service;

import java.util.Map;


public interface IPropertyImgService {
	
	
	Map<Object, Object> findPropertyImgList(Map<Object, Object> condition);

	Map<Object, Object> addPropertyImg(Map<Object, Object> condition);

	Map<Object, Object> findPropertyImgById(String propertyImgId);

	Map<Object, Object> editPropertyImgById(Map<Object, Object> condition);

	Map<Object, Object> deletePropertyImg(String propertyImgId);
	

}
