package com.bit2016.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.mysite.repository.GuestbookDao;
import com.bit2016.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> getGuestbookList() {
		System.out.println("GuestbookService list 입장");

		return guestbookDao.getList();
	}

	public List<GuestbookVo> getGuestbookList(int page) {
		System.out.println("GuestbookService list(int page) 입장");
		
		return guestbookDao.getList(page);
	}

	public GuestbookVo listAdd(GuestbookVo vo, boolean fetch) {
		System.out.println("GuestbookService listAdd 입장");
		GuestbookVo guestbookVo = null;
		
		Long no = guestbookDao.insert(vo);
		if(fetch){
			guestbookVo = guestbookDao.get(no);
		}
		
		return guestbookVo;
	}
	
	public boolean delete(GuestbookVo vo) {
		System.out.println("GuestbookService delete 입장");
		int count = guestbookDao.delete(vo);
		
		return count == 1;
	}
}
