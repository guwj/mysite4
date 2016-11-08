package com.bit2016.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("")
	public String index(){
		return "main/index";		// spring-servlet에서 viewResolver가 prefix, suffix로 해서 url 앞뒤 설정을 해놨기 때문에 이렇게 된 것이다
	}
	
	
	// requestMapping만 하면 viewResolver에게 가서 처리하게 하는 것이고 responsebody를 해주면 viewResolver를 거치지 않고 바로 브라우저로 간다
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("MainController hello 입장");
		return "제이레벨";
	}
}
