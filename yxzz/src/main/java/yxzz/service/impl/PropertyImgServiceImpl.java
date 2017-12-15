package yxzz.service.impl;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import yxzz.dto.AreaDto;
import yxzz.dto.PropertyImgDto;
import yxzz.mapper.PropertyImgMapper;
import yxzz.service.IPropertyImgService;

@Service("propertyImgService")
public class PropertyImgServiceImpl implements IPropertyImgService{
	
	
	
	@Resource
	private PropertyImgMapper propertyImgMapper;

	
	
	private static Logger logger = Logger.getLogger(PropertyImgServiceImpl.class);



	public Map<Object, Object> findPropertyImgList(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PageHelper.startPage(0, 2,true);

			List<PropertyImgDto> resultList  = propertyImgMapper.findPropertyImgList(condition);
			logger.info("=================================================="+resultList);
			result.put("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> addPropertyImg(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			
			String pathurl = "D://home//yxzz//property";
			String path = "";
			File filePath = new File(pathurl);
			File file = new File(path);
			if(!filePath.exists()){
				filePath.mkdirs();
			}else if(file.exists()){
				file.delete();
			}
			
			long row = propertyImgMapper.addPropertyImg(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> findPropertyImgById(String propertyImgId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PropertyImgDto propertyImgDto = propertyImgMapper.findPropertyImgById(propertyImgId);
			result.put("status","1");
			result.put("propertyImgDto", propertyImgDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> editPropertyImgById(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = propertyImgMapper.editPropertyImgById(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> deletePropertyImg(String propertyImgId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = propertyImgMapper.deletePropertyImg(propertyImgId);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	

	
	
	

}
