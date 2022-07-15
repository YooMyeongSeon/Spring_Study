package com.green.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.green.db.DBManager;
import com.green.vo.MemberVo;

public class MemberDao1 {
	private MemberDao1() {}
	private static MemberDao1 dao = new MemberDao1();
	public static MemberDao1 getInstance() {
		return dao;
	}
	
	public List<MemberVo> selectAll() {
		List<MemberVo> list = new ArrayList<>();
		
		String sql = "select * from members";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				MemberVo mVo = new MemberVo();
				
				mVo.setId(rs.getLong("id"));
				mVo.setEmail(rs.getNString("email"));
				mVo.setName(rs.getNString("name"));
				mVo.setPassword(rs.getNString("password"));
				mVo.setRegisterDate(rs.getDate("regdate"));
				
				list.add(mVo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}