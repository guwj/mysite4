package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.mysite.service.GuestbookService;
import com.bit2016.mysite.vo.GuestbookVo;



@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	

	@RequestMapping("")
	public String list(Model model) {
		System.out.println("GuestbookController list 입장");
		List<GuestbookVo> list = guestbookService.getGuestbookList();
		model.addAttribute("list", list);
		
		return "guestbook/list";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestbookVo vo) {
		System.out.println("GuestbookController add 입장");
		guestbookService.listAdd(vo);

		return "redirect:/guestbook";
	}
	
	@RequestMapping("/deleteform/{no}")
	public String deleteForm(@PathVariable ("no") int no, Model model){
		System.out.println("GuestbookController deleteForm 입장");
		model.addAttribute("no", no);
		
		return "guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo){
		System.out.println("GuestbookController delete 입장");
		guestbookService.delete(vo);
		
		return "redirect:/guestbook";
	}
}
