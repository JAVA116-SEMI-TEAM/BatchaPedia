package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.keepData.model.keepDataService;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.batcha.starsData.model.starsDataService;
import com.controller.Controller;

public class MovieDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//파라미터 임시로 mvNo 1번 넣어둠, 목록화면에서 mvNo 넘겨줘야 함
	//	String mvNo=request.getParameter("mvNo");
	//	int iMvNo=Integer.parseInt(mvNo);
		int iMvNo=3;
		
		MvInfoService service=new MvInfoService();
		starsDataService starsSrv=new starsDataService();
		keepDataService keepSrv=new keepDataService();
		
		//여기부터는 공통된 사항
		//영화정보조회
		MvInfoVO mvVo=new MvInfoVO();
		try {
			mvVo=service.selectMvByMvNo(iMvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//평점분포조회 todo.. 그래프 뿌릴 수 있으면
		
		//평점조회
		float avgStars=0.0f;
		try {
			avgStars=starsSrv.getAvgStars(iMvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//여기부터는 개인정보 관련된 사항
//		int memNo=MemInfoService.getMemNo(); //memNo도 임시로 12번 세팅해둠, 멤버서비스에서 가져와야 함 세팅 안돼있으면?
		int memNo=12;
		int isKept=0;
		int didStars=0;
		float memStars=0;
		
		if(memNo>0) { //로그인 되어 있으면
			try {
				//찜여부조회
				isKept=keepSrv.isKept(memNo, iMvNo);
				
				//평점여부조회
				didStars=starsSrv.didStars(memNo, iMvNo);
				if(didStars==starsDataService.YES_YOU_DID) { //영화평점 가져오기
					memStars=starsSrv.getStarsByMemNo(memNo, iMvNo);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//코멘트 리스트
		
		//셋팅 		
		request.setAttribute("mvVo", mvVo);
		request.setAttribute("memStars", memStars);
		request.setAttribute("didStars", didStars);
		request.setAttribute("isKept", isKept);
		request.setAttribute("avgStars", avgStars);
		
		return "/movie/movieDetail.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
