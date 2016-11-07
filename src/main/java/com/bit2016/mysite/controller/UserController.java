package com.bit2016.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.exception.UserDaoException;
import com.bit2016.mysite.service.UserService;
import com.bit2016.mysite.vo.UserVo;

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

	@RequestMapping("/login")
	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "password", required = true, defaultValue = "") String password,
			HttpSession session) {

		System.out.println("UserController login 입장");
		UserVo userVo = userService.login(email, password);

		// 인증 실패
		if (userVo == null) {
			return "redirect:/user/loginform?result=fail";
		}
		// 인증 성공
		session.setAttribute("authUser", userVo);

		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		System.out.println("UserController logout 입장");
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/";
	}

	@RequestMapping("/modifyform")
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController modifyform 입장");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 접근제한
		if (authUser == null) {
			return "redirect:/user/loginform?redirect-url=/user/modifyform";
		}

		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		return "user/modifyform";
	}

	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo) {
		System.out.println("UserController modify 입장");
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		// 접근 제한
		if (authUser == null) {
			return "redirect:/user/loginform";
		}

		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());

		return "redirect:/user/modifyform?update=success";
	}
	
	
//	// 비추하는 Exception 받는 방식
//	@ExceptionHandler(UserDaoException.class)
//	public String handlerUserDaoException(){
//		// 1. logging ( 파일에 내용 저장 )
//		//2. 사용자에게 사과하는 안내 페이지로 이동
//		
//		return "error/500";
//	}
}
