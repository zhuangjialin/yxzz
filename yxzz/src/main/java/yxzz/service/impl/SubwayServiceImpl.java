package yxzz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import yxzz.dto.SubwayDto;
import yxzz.mapper.SubwayMapper;
import yxzz.service.ISubwayService;

@Service("subwayService")
public class SubwayServiceImpl implements ISubwayService{
	
	
	
	@Resource
	private SubwayMapper subwayMapper;

	
	
	private static Logger logger = Logger.getLogger(SubwayServiceImpl.class);



	public Map<Object, Object> findSubwayList(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			PageHelper.startPage(0, 2,true);
			List<SubwayDto> resultList  = subwayMapper.findSubwayList(condition);
			logger.info("=================================================="+resultList);
			result.put("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> addSubway(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = subwayMapper.addSubway(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> findSubwayById(String subwayId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			SubwayDto subwayDto = subwayMapper.findSubwayById(subwayId);
			result.put("status", subwayDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> editSubwayById(Map<Object, Object> condition) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = subwayMapper.editSubwayById(condition);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



	public Map<Object, Object> deleteSubway(String areaId) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			long row = subwayMapper.deleteSubway(areaId);
			result.put("status", row);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	
	
	
	
	

}
