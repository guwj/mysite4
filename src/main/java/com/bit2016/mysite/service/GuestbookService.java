package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	public List getGuestbookList(){
		System.out.println("GuestbookService list 입장");
		List<GuestbookVo> list = guestbookDao.getList();
		
		return list;
	}
	
	
	public void listAdd(GuestbookVo vo){
		System.out.println("GuestbookService listAdd 입장");
		guestbookDao.insert(vo);
	}
	
	public void delete(GuestbookVo vo){
		System.out.println("GuestbookService delete 입장");
		guestbookDao.delete(vo);
	}
}
