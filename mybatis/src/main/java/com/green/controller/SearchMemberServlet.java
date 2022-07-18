package com.green.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDao2;
import com.green.vo.MemberVo;

@WebServlet("/SM")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/searchMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		MemberVo mVo = new MemberVo();
		
		mVo.setEmail(email);
		mVo.setName(name);
		
		MemberDao2 dao = MemberDao2.getInstance();
		List<MemberVo> list = dao.searchMember(mVo);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("member/searchMemberInfo.jsp").forward(request, response);
	}
}