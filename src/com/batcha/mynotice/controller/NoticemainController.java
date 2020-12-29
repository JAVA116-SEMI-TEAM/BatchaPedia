package com.batcha.mynotice.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.common.PagingVO;
import com.batcha.mynotice.model.NoticeService;
import com.batcha.mynotice.model.NoticeVO;
import com.controller.Controller;

public class NoticemainController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1. �ĸ����� �ޱ�
		String condition = request.getParameter("searchCondition");
		String keyword = request.getParameter("searchKeyword");

		//2. db�۾�
		NoticeService ntservice = new NoticeService();
		List<NoticeVO> list = null;

		try {
			list = ntservice.selectAllNotice(condition, keyword);
			System.out.println("������������="+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//����¡
		int currentPage=1;
		if(request.getParameter("currentPage")!=null 
				&&!request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("����������"+currentPage);
		}
		int totalRecord=0;

		if (list!=null) {
			totalRecord=list.size();
		}

		int pageSize=10;
		int blockSize=5;

		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);

		//3. �������
		request.setAttribute("ntlist", list);
		request.setAttribute("pageVo", pageVo);

		//4. ����������

		return "/notice/noticemain.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}


}
