package com.bit2016.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/joinform")
	public String joinForm() {
		System.out.println("UserController joinform 입장");
		return "user/joinform";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {

		System.out.println("UserController join 입장");
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		System.out.println("UserController joinsuccess 입장");
		return "user/joinsuccess";
	}

	@RequestMapping("/loginform")
	public String loginForm() {
		System.out.println("UserController loginform 입장");
		return "user/loginform";
	}

	@Auth
	@RequestMapping("/modifyform")
	public String modifyForm(@AuthUser UserVo authUser, Model model) {
		System.out.println("UserController modifyform 입장");

		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}

	@Auth
	@RequestMapping("/modify")
	public String modify(@AuthUser UserVo authUser, @ModelAttribute UserVo vo) {
		System.out.println("UserController modify 입장");

		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());

		return "redirect:/user/modifyform?update=success";
	}

	// // 비추하는 Exception 받는 방식
	// @ExceptionHandler(UserDaoException.class)
	// public String handlerUserDaoException(){
	// // 1. logging ( 파일에 내용 저장 )
	// //2. 사용자에게 사과하는 안내 페이지로 이동
	//
	// return "error/500";
	// }
}
