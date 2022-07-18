package com.green.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDao2;
import com.green.vo.MemberVo;

@WebServlet("/FSM")
public class ForEachSearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> name = new ArrayList<>();
		
		name.add("김길동");
		name.add("이길동");
		name.add("박길동");
		
		MemberDao2 dao = MemberDao2.getInstance();
		List<MemberVo> list = dao.forEachSearchMember(name);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("member/lists.jsp").forward(request, response);
	}
}