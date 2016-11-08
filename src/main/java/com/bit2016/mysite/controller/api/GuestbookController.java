package com.bit2016.mysite.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.service.GuestbookService;
import com.bit2016.mysite.vo.GuestbookVo;

@Controller(" guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Object list(@RequestParam(value = "p", required = true, defaultValue = "1") Integer page) {
		System.out.println("GuestbookAPIController list 입장");
		List<GuestbookVo> list = guestbookService.getGuestbookList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Map<String, Object> add(@ModelAttribute GuestbookVo vo){
		
		System.out.println("GuestbookAPIController add 입장");
		GuestbookVo guestbookVo = guestbookService.listAdd(vo, true);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", guestbookVo);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map<String, Object> delete(@ModelAttribute GuestbookVo vo){
		System.out.println("GuestbookAPIController delete 입장");
		
		boolean result = guestbookService.delete(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", result ? vo.getNo() : -1 );
		
		return map;
	}
}

