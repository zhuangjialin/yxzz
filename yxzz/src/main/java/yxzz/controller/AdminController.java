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
@RequestMapping("/admin")
public class AdminController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	
	
	@RequestMapping("/index")
	@ResponseBody
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		Map<Object,Object> model = new HashMap<Object,Object>();
		return new ModelAndView("/admin/index", "content", model);
	}
	
	
	

	

}
