package com.bit2016.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.mysite.exception.UserDaoException;
import com.bit2016.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private DataSource datasource;

	// 인증(로그인)
	public UserVo get(String email, String password) throws UserDaoException {

		System.out.println("UserDao get(email, password) 입장");
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();

			String sql = " select no, name" + "   fromusers" + "  where email=?" + "    and password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}
		} catch (SQLException e) {
			throw new UserDaoException();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return vo;
	}

	// email 체크할 때 사용
	public UserVo get(String email) {
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();

			String sql = "select no, email, name from users	where email = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				vo = new UserVo();

				vo.setNo(rs.getLong(1));
				vo.setEmail(rs.getString(2));
				vo.setName(rs.getString(3));

			}

		} catch (SQLException ex) {
			System.out.println("error : " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("error : " + ex);
			}
		}

		return vo;
	}

	// 사용자 가져오기
	public UserVo get(Long no) {
		System.out.println("UserDao get(no) 입장");
		UserVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();

			String sql = " select no, name, email, gender" + "   from users" + "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setGender(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		return vo;
	}

	public void insert(UserVo vo) {
		System.out.println("UserDao insert 입장");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();

			String sql = " insert" + "   into users" + " values( user_seq.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public void update(UserVo vo) {
		System.out.println("UserDao update 입장");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();

			String sql = "update users set name = ?, gender = ?";
			if ("".equals(vo.getPassword()) == false) {
				sql += ", password=?";
			}
			sql += " where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			if ("".equals(vo.getPassword()) == false) {
				pstmt.setString(3, vo.getPassword());
				pstmt.setLong(4, vo.getNo());
			} else {
				pstmt.setLong(3, vo.getNo());
			}

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}
}
