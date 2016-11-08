package com.bit2016.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

// 패키지명 controller.api에서는 json만 받아준다

@Controller("userApiController") // controller에 ID를 주는 것
@RequestMapping("/user/api")
public class UserController {

	// url은 가급적 소문자로 써줄 것

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/checkemail")
	public Map<String, Object> checkEmail(
			@RequestParam(value = "email", required = true, defaultValue = "") String email) {

		boolean result = userService.emailExists(email);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");

		if (result) {
			map.put("data", "exist");
		} else {
			map.put("data", "not exist");
		}

		return map;
	}
}
