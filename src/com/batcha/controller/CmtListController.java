package com.batcha.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.cmtData.model.CmtDataService;
import com.batcha.cmtData.model.CmtDataVO;
import com.batcha.common.PagingVO;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class CmtListController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		int memNo=0;
		if(session.getAttribute("memno")!=null && session.getAttribute("t_userid")!=null) {
			String t_memno=String.valueOf(session.getAttribute("memno"));
			memNo=Integer.parseInt(t_memno);
		}
		
		String strMvNo=request.getParameter("mvNo");
		int mvNo=Integer.parseInt(strMvNo);

		//코멘트리스트, 유저아이디 불러오기
		CmtDataService cmtService=new CmtDataService();
		List<CmtDataVO> cmtList=new ArrayList<CmtDataVO>();
		
		try {
			cmtList=cmtService.selectCmtByNo(mvNo, false);
			System.out.println(mvNo+"번 영화 코멘트 리스트 조회결과 "+cmtList.size()+"건");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//페이징 처리
		int currentPage=1;
		if(request.getParameter("currentPage")!=null 
				&& !request.getParameter("currentPage").isEmpty()) {
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int totalCmt=0;
		if(cmtList!=null) totalCmt=cmtList.size();
		
		int pageSize=5;
		int blockSize=5;
		
		PagingVO pageVo=new PagingVO(currentPage, totalCmt, pageSize, blockSize);
		
		//3
		request.setAttribute("pageVo", pageVo);
		request.setAttribute("cmtList", cmtList);
		request.setAttribute("mvNo", mvNo);
		
		return "movie/cmtList";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
