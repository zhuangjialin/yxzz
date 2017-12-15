package yxzz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import yxzz.service.IPropertyService;


@Controller
@RequestMapping("/propertyController")
public class PropertyController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Resource
	private IPropertyService propertyService;
	
	

	
	
	
	
	//获取一个城市内的所有区域
	@RequestMapping("/addProperty")
	@ResponseBody
	public ModelAndView findAreaList(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		//1默认就是广州
		condition.put("areaCityId", "1");
		propertyService.addProperty(condition);				
		return new ModelAndView("/admin/index", "content", model);
	}
	
	
	
	
	

}
