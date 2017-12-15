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
import yxzz.util.StringUtil;


@Controller
@RequestMapping("/areaController")
public class AreaController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Resource
	private IAreaService areaService;
	
	
	@RequestMapping("/addArea")
	@ResponseBody
	public ModelAndView addArea(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		condition.put("areaCityId", "1");
		areaService.addArea(condition);				
		return new ModelAndView("/admin/addArea", "content", model);
	}
	
	
	
	
	//�������
	@RequestMapping("/editAreaById")
	@ResponseBody
	public ModelAndView editAreaById(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		//1Ĭ�Ͼ��ǹ���
		areaService.editAreaById(condition);				
		return new ModelAndView("/admin/index", "content", model);
	}
	
	
	//�������
		@RequestMapping("/deleteArea")
		@ResponseBody
		public ModelAndView deleteArea(HttpServletRequest request,HttpServletResponse response){
			Map<Object,Object> model = new HashMap<Object,Object>();
			Map<Object,Object> condition = new HashMap<Object,Object>();
			//1Ĭ�Ͼ��ǹ���
			areaService.deleteArea(StringUtil.trim(condition.get("areaId")));				
			return new ModelAndView("/admin/index", "content", model);
		}
	
		
		//�������
	@RequestMapping("/findAreaList")
	@ResponseBody
	public ModelAndView findAreaList(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		Map<Object,Object> condition = new HashMap<Object,Object>();
		//1Ĭ�Ͼ��ǹ���
		condition.put("areaCityId", "1");
		areaService.findAreaList(condition);				
		return new ModelAndView("/admin/areaList", "content", model);
	}
		

}
