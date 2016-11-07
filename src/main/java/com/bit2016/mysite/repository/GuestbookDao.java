package com.bit2016.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void delete(GuestbookVo vo) {
		System.out.println("GuestbookDao delete 입장");
		sqlSession.delete("guestbook.delete", vo);
	}
	
	public void insert(GuestbookVo vo ) {
		System.out.println("GuestbookDao insert 입장");
		sqlSession.insert("guestbook.insert", vo);
	}
	
	public List<GuestbookVo> getList() {
		System.out.println("GuestbookDao getList 입장");
		return sqlSession.selectList("guestbook.getList");
	}
}
