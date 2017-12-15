package yxzz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import yxzz.dto.PropertyDto;
import yxzz.mapper.PropertyMapper;
import yxzz.service.IPropertyService;
import yxzz.util.StringUtil;

@Service("propertyService")
public class PropertyServiceImpl implements IPropertyService{
	
	
	
	@Resource
	private PropertyMapper propertyMapper;
	
	
	

	
	
	private static Logger logger = Logger.getLogger(PropertyServiceImpl.class);

	
	public Map<Object, Object> findPropertyList(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PageHelper.startPage(0, 2,true);

			List<PropertyDto> resultList  = propertyMapper.findPropertyList(condition);
			logger.info("=================================================="+resultList);
			result.put("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	//新增物业
	public Map<Object, Object> addProperty(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			
			//新增物业
			long row1 = propertyMapper.addProperty(condition);
			condition.put("propertyId",condition.get("propertyId"));

			//新增物业关联地铁
			long row2 = propertyMapper.addPropertySubway(condition);
			if(row1 == row2){
				result.put("status", 0);
			}else{
				result.put("status", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<Object, Object> findPropertyById(String PropertyId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PropertyDto PropertyDto = propertyMapper.findPropertyById(PropertyId);
			result.put("status", PropertyDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<Object, Object> editPropertyById(Map<Object, Object> condition) {
				Map<Object, Object> result = new HashMap<Object, Object>();
				try {
					long row1 = propertyMapper.editPropertyById(condition);
					
					
					long row2 = propertyMapper.editSubwayPropertyById(condition);
					
					if(row1 == row2){
						result.put("status", 0);
					}else{
						result.put("status", 1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
	}

	public Map<Object, Object> deleteProperty(Map<Object, Object> condition) {
				Map<Object, Object> result = new HashMap<Object, Object>();
				try {
					long row1 = propertyMapper.deleteProperty(StringUtil.trim(condition.get("propertyId")));
					long row2 = propertyMapper.deleteSubwayProperty(StringUtil.trim(condition.get("id")));
					if(row1 == row2){
						result.put("status", 0);
					}else{
						result.put("status", 1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
	
	}
	
	
	
	
	
	

}
