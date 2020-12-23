package com.batcha.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;

import com.batcha.keepData.model.keepDataDAO;
import com.batcha.keepData.model.keepDataService;
import com.batcha.keepData.model.keepDataVO;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.batcha.starsData.model.starsDataService;
import com.batcha.starsData.model.starsDataVO;
import com.controller.Controller;

public class MovieDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//파라미터 임시로 mvNo 1번 넣어둠, 목록화면에서 mvNo 넘겨줘야 함
	//	String mvNo=request.getParameter("mvNo");
	//	int iMvNo=Integer.parseInt(mvNo);
		int iMvNo=3;

		HttpSession session = request.getSession();
		int memNo=0;
		if(session.getAttribute("memno")!=null) {
			String t_memno=String.valueOf(session.getAttribute("memno"));
			memNo=Integer.parseInt(t_memno);
		}
		
		MvInfoService mvService=new MvInfoService();
		starsDataService starsService=new starsDataService();
		keepDataService keepService=new keepDataService();
		keepDataVO keepVo=new keepDataVO();
		
		//===================여기부터는 공통된 사항
		//영화정보조회
		MvInfoVO mvVo=new MvInfoVO();
		try {
			mvVo=mvService.selectMv(iMvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		//===================영화평점조회
		//평점조회
		float avgStars=0.0f;
		int memCntOfMv=0;
		List<starsDataVO> list=null;
		try {
			avgStars=starsService.getAvgStars(iMvNo);
			//list=starsService.selectAllStarsByNo(iMvNo, false);
			if(starsService.selectAllStarsByNo(iMvNo, false)!=null 
					|| !starsService.selectAllStarsByNo(iMvNo, false).isEmpty()) {
				list=starsService.selectAllStarsByNo(iMvNo, false);
				memCntOfMv=list.size();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//평점분포조회 todo.. 그래프 뿌릴 수 있으면
		int[] graphData = {0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<list.size(); i++) {
			float star=list.get(i).getStars();
			switch((int)star) {
			case 1: graphData[0]++; break;
			case 2: graphData[1]++; break;
			case 3: graphData[2]++; break;
			case 4: graphData[3]++; break;
			case 5: graphData[4]++; break;
			case 6: graphData[5]++; break;
			case 7: graphData[6]++; break;
			case 8: graphData[7]++; break;
			case 9: graphData[8]++; break;
			case 10: graphData[9]++; break;
			default: break;
			}
		}
		
		//===================keep관련 
		//파라미터값 받기
		//	String mvNo=request.getParameter("mvNo");
		//	String memNo=request.getParameter("memNo");
		//논유저면 당연히 false, 유저인 경우 isKept함수 탐
		//------- 최초진입시
		if(request.getAttribute("keptCheck")==null) {
			
		}
		boolean keptCheck=false;
		
		if(memNo>0) {
			try {
				System.out.println("keep서비스 결과"+keepService.isKept(memNo, iMvNo));
				int cnt=keepService.isKept(memNo, iMvNo);
				if(cnt>0) {
					keptCheck=true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//본건지 안본건지 체크 완료
		}
		
		String keepBtn="";
		if(request.getParameter("keepBtn")!=null) {
			System.out.println("keepBtn의 값="+request.getParameter("keepBtn"));
			keepBtn=request.getParameter("keepBtn");
		}
		//결과 저장
		
		//-----킵버튼으로 재진입시
		System.out.println("keepBtn="+keepBtn);
		
		if(keepBtn.equals("t")) {
			keepService.deleteKeep(memNo, iMvNo);
			System.out.println("keep목록에서 삭제");
		}else if (keepBtn.equals("f")){
			keepService.insertKeep(memNo, iMvNo);
			System.out.println("keep목록에 추가");
		}
		
		//===================개인정보 관련 
		int isKept=0;
		int didStars=0;
		float memStars=0;
		
		if(memNo>0) { //로그인 되어 있으면
			try {
				//찜여부조회
				isKept=keepService.isKept(memNo, iMvNo);
				
				//평점여부조회
				didStars=starsService.didStars(memNo, iMvNo);
				if(didStars==starsDataService.YES_YOU_DID) { //영화평점 가져오기
					memStars=starsService.getStarsByMemNo(memNo, iMvNo);
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
		request.setAttribute("keptCheck", keptCheck);
		request.setAttribute("avgStars", avgStars);
		request.setAttribute("memCntOfMv", memCntOfMv);
		request.setAttribute("graphData", graphData);
		
		return "/movie/movieDetail.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
