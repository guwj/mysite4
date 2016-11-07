package com.bit2016.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("")
	public String index(){
		return "main/index";		// spring-servlet에서 viewResolver가 prefix, suffix로 해서 url 앞뒤 설정을 해놨기 때문에 이렇게 된 것이다
	}
}
