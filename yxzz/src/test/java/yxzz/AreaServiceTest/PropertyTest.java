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
import yxzz.service.IPropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class PropertyTest {

	private static Logger logger = Logger.getLogger(PropertyTest.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Resource
	private IPropertyService propertyService = null;

	@Test
	public void addProperty() {
		Map<Object, Object> condition = new HashMap<Object, Object>();
		
		
				condition.put("propertyName",          "1");
				condition.put("propertyLng",           "1");
				condition.put("propertyLat",           "1");
				condition.put("propertyMinFloor",     "1");
				condition.put("propertyMaxFloor",     "1");
				condition.put("propertyPrice",         "1");
				condition.put("propertyDescription",   "1");
				condition.put("propertyMinYear",      "1");
				condition.put("propertyMaxYear",      "1");
				condition.put("propertySquare",        "1");
				condition.put("propertyIsFengzu",     "1");
				condition.put("propertyAttribute",     "1");
				condition.put("propertyStanderHeight","1");
				condition.put("propertyPhone",         "1");
				condition.put("propertyTelephone",     "1");
				condition.put("propertyCreateUser",   "1");
				condition.put("propertyUpdateUser",   "1");
				condition.put("propertyCreateTime",   "1");
				condition.put("propertyImgsId",       "1");
				condition.put("propertyIsZcorzd",     "1");
				condition.put("propertyIsDelete",     "1");
				condition.put("condition1",             "1");
				condition.put("condition2",             "1");
				
				
				
				condition.put("subwayId",             "1");
				condition.put("spDistance",             "1");
				
				
		
		
		Map<Object, Object> result = propertyService.addProperty(condition);
		System.out.println(condition.toString());
		
		
	}

	/*
	 * @Test public void addArea(){ Map<Object,Object> condition = new
	 * HashMap<Object,Object>(); condition.put("areaCode", "123456");
	 * condition.put("areaName", "test"); condition.put("condition1","test" );
	 * condition.put("condition2", "test"); condition.put("areaLng", "123456");
	 * condition.put("areaLat", "test"); condition.put("areaCityId",1);
	 * condition.put("areaSubwayNum", 10); condition.put("areaPropertyNum",
	 * 100); logger.info("=====================================" +
	 * areaService.addArea(condition));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test public void editAreaById(){ Map<Object,Object> condition = new
	 * HashMap<Object,Object>(); condition.put("areaId", "1");
	 * condition.put("areaCode", "2000"); condition.put("areaName", "update");
	 * condition.put("condition1","update" ); condition.put("condition2",
	 * "update"); condition.put("areaLng", "654321"); condition.put("areaLat",
	 * "update"); condition.put("areaCityId",1000);
	 * condition.put("areaSubwayNum", 1000); condition.put("areaPropertyNum",
	 * 1000); logger.info("=====================================" +
	 * areaService.editAreaById(condition));
	 * 
	 * }
	 * 
	 * 
	 * @Test public void deleteProperty(){
	 * 
	 * logger.info("=====================================" +
	 * areaService.deleteArea("2"));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test public void findAreaById(){
	 * 
	 * logger.info("=====================================" +
	 * areaService.findAreaById("1"));
	 * 
	 * }
	 */

}
