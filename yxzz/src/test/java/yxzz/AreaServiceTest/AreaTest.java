package yxzz.AreaServiceTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import yxzz.dto.AreaDto;
import yxzz.service.IAreaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class AreaTest {

	private static Logger logger = Logger.getLogger(AreaTest.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Resource
	private IAreaService areaService = null;

	@Test
	public void findAreaList() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		PageHelper.startPage(0, 1, true);
		condition = areaService.findAreaList(condition);
		System.out.println(condition.toString());
		List<AreaDto> resultList = (List<AreaDto>) condition.get("resultList");
		Page<AreaDto> PageList = (Page<AreaDto>) resultList;
		/*
		 * List<AreaDto> resultList = (List<AreaDto>)
		 * condition.get("resultList");
		 */
		logger.info("=====================================" + PageList.getTotal());
	}

	@Test
	public void addArea(){
		Map<Object,Object> condition = new HashMap<Object,Object>();		
		condition.put("areaCode", "123456");
		condition.put("areaName", "test");
		condition.put("condition1","test" );
		condition.put("condition2", "test");		
		condition.put("areaLng", "123456");
		condition.put("areaLat", "test");
		condition.put("areaCityId",1);
		condition.put("areaSubwayNum", 10);
		condition.put("areaPropertyNum", 100);
		logger.info("=====================================" + areaService.addArea(condition));

	}
	
	
	
	@Test
	public void editAreaById(){
		Map<Object,Object> condition = new HashMap<Object,Object>();	
		condition.put("areaId", "1");
		condition.put("areaCode", "2000");
		condition.put("areaName", "update");
		condition.put("condition1","update" );
		condition.put("condition2", "update");		
		condition.put("areaLng", "654321");
		condition.put("areaLat", "update");
		condition.put("areaCityId",1000);
		condition.put("areaSubwayNum", 1000);
		condition.put("areaPropertyNum", 1000);
		logger.info("=====================================" + areaService.editAreaById(condition));

	}
	
	
	@Test
	public void deleteProperty(){

		logger.info("=====================================" + areaService.deleteArea("2"));

	}
	
	
	
	@Test
	public void findAreaById(){

		logger.info("=====================================" + areaService.findAreaById("1"));

	}
	
	
	


}
