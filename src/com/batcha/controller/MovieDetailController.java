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
		//�Ķ���� �ӽ÷� mvNo 1�� �־��, ���ȭ�鿡�� mvNo �Ѱ���� ��
	//	String mvNo=request.getParameter("mvNo");
	//	int iMvNo=Integer.parseInt(mvNo);
		int iMvNo=3;
		
		MvInfoService service=new MvInfoService();
		starsDataService starsSrv=new starsDataService();
		keepDataService keepSrv=new keepDataService();
		
		//������ʹ� ����� ����
		//��ȭ������ȸ
		MvInfoVO mvVo=new MvInfoVO();
		try {
			mvVo=service.selectMvByMvNo(iMvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//����������ȸ todo.. �׷��� �Ѹ� �� ������
		
		//������ȸ
		float avgStars=0.0f;
		try {
			avgStars=starsSrv.getAvgStars(iMvNo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//������ʹ� �������� ���õ� ����
//		int memNo=MemInfoService.getMemNo(); //memNo�� �ӽ÷� 12�� �����ص�, ������񽺿��� �����;� �� ���� �ȵ�������?
		int memNo=12;
		int isKept=0;
		int didStars=0;
		float memStars=0;
		
		if(memNo>0) { //�α��� �Ǿ� ������
			try {
				//�򿩺���ȸ
				isKept=keepSrv.isKept(memNo, iMvNo);
				
				//����������ȸ
				didStars=starsSrv.didStars(memNo, iMvNo);
				if(didStars==starsDataService.YES_YOU_DID) { //��ȭ���� ��������
					memStars=starsSrv.getStarsByMemNo(memNo, iMvNo);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//�ڸ�Ʈ ����Ʈ
		
		//���� 		
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
