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

@WebServlet("/FS")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/lists.jsp";
		
//		MemberDao1 dao = MemberDao1.getInstance(); //마이바티스를 사용하지 않은 예제
		
		MemberDao2 dao = MemberDao2.getInstance();
		List<MemberVo> list = dao.selectAll();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}