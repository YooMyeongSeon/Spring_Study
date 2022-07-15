package com.green.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.green.vo.MemberVo;

public class MemberDao2 { //마이바티스 설정을 읽어서 DB에 접속하는 기능을 담당
	private MemberDao2() {}
	private static MemberDao2 dao = new MemberDao2();
	public static MemberDao2 getInstance() {
		return dao;
	}
	
	private static SqlSessionFactory sqlMapper = null;
	
	private static SqlSessionFactory getFactory() {
		if (sqlMapper == null) {
			try {
				String resource = "com/green/mybatis/mybatisConfig.xml";
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlMapper = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return sqlMapper;
	}
	
	public List<MemberVo> selectAll() {
		List<MemberVo> lists = null;
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		lists = session.selectList("com.green.mapper.member.selectAll");
		
		return lists;
	}
	
	public int selectCnt() {
		int cnt = 0;
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		cnt = session.selectOne("com.green.mapper.member.selectCnt");
		
		return cnt;
	}
	
	public MemberVo selectByEmail(String email) {
		MemberVo mVo = null;
		
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		mVo = session.selectOne("com.green.mapper.member.selectByEmail", email);
		
		return mVo;
	}
	
	public void insertMember(MemberVo mVo) {
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		session.insert("com.green.mapper.member.insertMember", mVo);
		session.commit();
	}
	
	public void updateMember(MemberVo mVo) {
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		session.update("com.green.mapper.member.updateMember", mVo);
		session.commit();
	}
	
	public void deleteMember(String email) {
		sqlMapper = getFactory();
		SqlSession session = sqlMapper.openSession();
		
		session.delete("com.green.mapper.member.deleteMember", email);
		session.commit();
	}
}