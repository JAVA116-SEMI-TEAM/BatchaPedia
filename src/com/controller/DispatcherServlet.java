package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 /tips/book.do
 /tips/travel.do
 
 ��� ��û�� DispatcherServlet���� ���´�
 ���������� �����ؼ� �����(��ɾ� ó�� Ŭ����)�� ã�� ���� ��Ų��(�޼ҵ� ȣ��)
*/
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Properties props;
	
	//init(): �ش� ������ ȣ��� �� �ѹ��� ȣ��Ǵ� �޼ҵ�
	//���������� �о Properties �÷��ǿ� �����Ѵ�
	@Override
	public void init(ServletConfig config) throws ServletException {
		//web.xml���� init-param�� �� �о����
		
		String configFile=config.getInitParameter("configFile");
		System.out.println("configFile="+configFile);
		//=> /config/CommandProproperties //�̰� �������� ��ΰ� �ƴϾ�
		
		//load()�޼ҵ忡�� fis�� �������� ���������� �������� ��θ� �˾ƿ;� �� 
		String realConfigFile = config.getServletContext().getRealPath(configFile);
		System.out.println("realConfigFile="+realConfigFile+"\n");
		props = new Properties();
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(realConfigFile);
			props.load(fis); //CommandPro.properties ���� ������ Properties �÷��ǿ� ����
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	//doGet, doPost�� ����� ��û�� ���� �� ���� ȣ���
	//��û�� ���ö����� ���� Properties�� �����ؼ� ����ڸ� ���� �� ��Ŵ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*
		 ���� properties �÷��ǿ��� ������� URI(/tips/book.do)�� 
		 �ش��ϴ� ����ڸ� ���ؼ�(��ɾ� ó�� Ŭ����, BookController) �Ͻ�Ų��
		 (�ش� ��Ʈ�ѷ��� �޼ҵ� ȣ��)
		 �׸��� ����� ���Ϲ޾� �ش� ���������� ��������Ų�� 
		 */
		
		//�ѱ� ó�� �Ѵ� ����� �ϳ��� �����°͵� �����ؼ� �׷���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//������� URI �о����
		//=> /mymvc/tips/book.do, /mymvc/tips/travel.do
		String uri=request.getRequestURI();
		System.out.println("uri="+uri);
		
		//uri�� ContextPath �����ϱ� (��ɾ� ���ϱ� ����)
		String contextPath=request.getContextPath(); //=> /mymvc
		System.out.println("contextPath="+contextPath);
		
		if(uri.indexOf(contextPath)!=-1) { //contextPath�� �ִ���. ������ ù��°�ε���, ������ -1 ��ȯ
			uri=uri.substring(contextPath.length()); //=> /tips/travel.do ��ɾ �����س�!
		}
		
		System.out.println("���ؽ�Ʈ�н� ���� �� uri="+uri);
		
		//��ɾ �ش��ϴ� ��ɾ� ó�� Ŭ���� ���ϱ� (����Controller2)
		String command=props.getProperty(uri);
		System.out.println("��ɾ� ó�� Ŭ���� command="+command);
		try {
			//�ش� ���ڿ��� Ŭ������ �����
			Class commandClass=Class.forName(command);
			
			//Ŭ������ ��ü ����
			Controller controller=(Controller)commandClass.newInstance();
			
			//�ش� Ŭ������ �޼ҵ� ȣ��(�Ͻ�Ű��).. �� �ϼ��� ������ �䰡 �ƴҼ��� �ִ�. �׷��� ����������� �̸� �ٲ�
			String resultPage=controller.requestProcess(request, response);
			System.out.println("ȣ��� �������� resultPage="+resultPage);
			
			if(controller.isRedirect()) {
				//�ش� �������� redirect
				System.out.println("redirect ������\n");
				response.sendRedirect(contextPath+resultPage); 
				//�����̷�Ʈ�� ���ؽ�Ʈ�н� �ڵ����� �� �����ϱ� �Ѿ�� ���� �̸� �ٿ��ֱ�
			}else {
				//�ش� ���������� ������
				System.out.println("forward ������\n");
				RequestDispatcher dispatcher=request.getRequestDispatcher(resultPage);
				dispatcher.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}