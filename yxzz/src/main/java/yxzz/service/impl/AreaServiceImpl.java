package yxzz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import yxzz.dto.AreaDto;
import yxzz.mapper.AreaMapper;
import yxzz.service.IAreaService;

@Service("areaService")
public class AreaServiceImpl implements IAreaService{
	
	
	
	@Resource
	private AreaMapper areaMapper;

	
	
	private static Logger logger = Logger.getLogger(AreaServiceImpl.class);

	
	public Map<Object, Object> findAreaList(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PageHelper.startPage(0, 2,true);

			List<AreaDto> resultList  = areaMapper.findAreaList(condition);
			logger.info("=================================================="+resultList);
			result.put("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<Object, Object> addArea(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = areaMapper.addArea(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<Object, Object> findAreaById(String areaId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			AreaDto areaDto = areaMapper.findAreaById(areaId);
			result.put("status", areaDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<Object, Object> editAreaById(Map<Object, Object> condition) {
				Map<Object, Object> result = new HashMap<Object, Object>();
				try {
					long row = areaMapper.editAreaById(condition);
					result.put("status", row);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
	}

	public Map<Object, Object> deleteArea(String areaId) {
				Map<Object, Object> result = new HashMap<Object, Object>();
				try {
					long row = areaMapper.deleteArea(areaId);
					result.put("status", row);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
	
	}
	
	
	
	
	
	

}
