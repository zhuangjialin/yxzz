package yxzz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import yxzz.dto.AreaDto;
import yxzz.dto.SubwaylineDto;
import yxzz.mapper.SubwaylineMapper;
import yxzz.service.ISubwaylineService;
import yxzz.util.StringUtil;

@Service("subwaylineService")
public class SubwaylineServiceImpl implements ISubwaylineService{
	
	
	
	@Resource
	private SubwaylineMapper subwaylineMapper;

	
	
	private static Logger logger = Logger.getLogger(SubwaylineServiceImpl.class);



	public Map<Object, Object> findSubwaylineList(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PageHelper.startPage(0, 2,true);

			List<SubwaylineDto> resultList  = subwaylineMapper.findSubwaylineList(condition);
			logger.info("=================================================="+resultList);
			result.put("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> addSubwayline(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = subwaylineMapper.addSubwayline(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> findSubwaylineById(String areaId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			AreaDto areaDto = subwaylineMapper.findSubwaylineById(areaId);
			result.put("status", areaDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> editSubwaylineById(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = subwaylineMapper.editSubwaylineById(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> deleteSubwayline(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = subwaylineMapper.deleteSubwayline(StringUtil.trim(condition.get("subwaylineId")));
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	
	
	
	

}
