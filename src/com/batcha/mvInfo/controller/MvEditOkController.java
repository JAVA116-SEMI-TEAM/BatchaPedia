package com.batcha.mvInfo.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;

public class MvEditOkController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String mvNo=request.getParameter("mvNo");
		String mvTitleEn=request.getParameter("mvTitleEn");
		String mvCode=request.getParameter("mvCode");
		String thumbnail=request.getParameter("thumbnail");
		String mvTitle=request.getParameter("mvTitle");
		String director=request.getParameter("director");
		String actors=request.getParameter("actors");
		String genre=request.getParameter("genre");
		String nation=request.getParameter("nation");
		String makeYear=request.getParameter("makeYear");
		String story=request.getParameter("story");
		
		//2
		MvInfoService service=new MvInfoService();
		String msg="영화 정보 수정 실패", url="/mvInfo/mvEdit.do?mvNo="+mvNo;
		try {
			MvInfoVO mVo=new MvInfoVO();
			mVo.setMvNo(Integer.parseInt(mvNo));
			mVo.setMvTitle(mvTitle);
			mVo.setDirector(director);
			mVo.setActors(actors);
			mVo.setGenre(genre);
			mVo.setNation(nation);
			mVo.setMakeYear(makeYear);
			mVo.setStory(story);
			mVo.setMvTitleEn(mvTitleEn);
			mVo.setMvCode(mvCode);
			mVo.setThumbnail(thumbnail);
			
			int cnt=service.updateMvInfo(mVo);
			if(cnt>0) {
				msg="영화 정보 수정 성공";
				url="/mvInfo/mvEdit.do?mvNo="+mvNo;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
