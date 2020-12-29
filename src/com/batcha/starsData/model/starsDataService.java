package com.batcha.starsData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class starsDataService {

   starsDataDAO starsDao;

   //mvNo�� �ش��ϴ� ��ȭ�� memNoȸ���� ������ �Ű���� ���� �Ǻ�
   public final static int YES_YOU_DID=1;
   public final static int NO_YOU_DIDNT=0;
   
   public starsDataService() {
      starsDao=new starsDataDAO();
   }

   //��ȭ�� ��� ���� ��ȸ
   public float getAvgStars(int mvNo) throws SQLException {
      return starsDao.getAvgStars(mvNo);
   }
   
   //ȸ��/��ȭ��ȣ�� ��������Ʈ ��ȸ�ϱ�. list.size�� ȸ��/��ȭ�� ���� ������ Ȯ�� ����
   public List<starsDataVO> selectAllStarsByNo(int no, boolean isMemNo) throws SQLException{
      return starsDao.selectAllStarsByNo(no, isMemNo);
   }
   
   //����Ʈ�ε�.. ���� �Ѱ��� �������� ��
   //�� �޼ҵ� ������� 0�̸� �ᱹ ������ �Է����� �ʾҴٴ� ���ε�
   public int getStarsByMemNo(int memNo, int mvNo) throws SQLException {//������ �ο��� ��� �������� ã��
      return starsDao.getStarsByMemNo(memNo, mvNo);
   }
   
   //���� �Է��ϱ�
   public int insertStars(starsDataVO starsVo) throws SQLException {
      return starsDao.insertStars(starsVo);
   }
   
   //Ư�� ȸ���� Ư�� ��ȭ ���� �����ϱ�
   public int deleteStars(int memNo, int mvNo) throws SQLException {
      return starsDao.deleteStars(memNo, mvNo);
   }
   
   public int updateStars(starsDataVO starsVo) throws SQLException {
      return starsDao.updateStars(starsVo);
   }
   
   //Ư�� ȸ���� ��ȭ�� ���� �ο��ߴ��� ���� ��ȸ ������ȸ���� Ȯ�ΰ����ҰŰ����� �־�� �ϳ� ���־� �ϳ� ���
   public int didStars(int memNo, int mvNo) throws SQLException {
      return starsDao.didStars(memNo, mvNo);
   }

   public int[] makeStarGraph(List<starsDataVO> starsList) {
      return starsDao.makeStarGraph(starsList);
   }
}