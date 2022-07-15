package com.green.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.dao.MemberDao2;
import com.green.vo.MemberVo;

@WebServlet("/UM")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/updateMemberForm.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		MemberVo mVo = new MemberVo();
		
		mVo.setName(request.getParameter("name"));
		mVo.setEmail(request.getParameter("email"));
		mVo.setPassword(request.getParameter("password"));
		
		MemberDao2 dao = MemberDao2.getInstance();
		dao.updateMember(mVo);

		response.sendRedirect("http://localhost:8085/mybatis/FS");
	}
}