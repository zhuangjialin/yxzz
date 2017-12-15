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

import yxzz.service.IAreaService;
import yxzz.service.ISubwayService;
import yxzz.service.ISubwaylineService;


@Controller
@RequestMapping("/subwaylineController")
public class SubwaylineController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Resource
	private ISubwaylineService subwaylineService;
	
	
	
	
	//新增地铁线
	@RequestMapping("/addSubwayList")
	@ResponseBody
	public ModelAndView findAreaList(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		model = subwaylineService.addSubwayline(condition);				
		return new ModelAndView("/admin/index", "content", model);
	}
	
	
	//更新地铁线
	@RequestMapping("/editSubwaylineById")
	@ResponseBody
	public ModelAndView editSubwaylineById(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		model = subwaylineService.editSubwaylineById(condition);				
		return new ModelAndView("/admin/index", "content", model);
	}
	
	
	//根据条件查询地铁线
	@RequestMapping("/findSubwaylineList")
	@ResponseBody
	public ModelAndView findSubwaylineList(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		model = subwaylineService.findSubwaylineList(condition);				
		return new ModelAndView("/admin/index", "content", model);
	}
	
	
	
	//更新地铁线
	@RequestMapping("/deleteSubwayline")
	@ResponseBody
	public ModelAndView deleteSubwayline(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		model = subwaylineService.deleteSubwayline(condition);				
		return new ModelAndView("/admin/index", "content", model);
	}
	

}
