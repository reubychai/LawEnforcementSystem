package com.law.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.law.web.util.*;

/* Reuby
 * ��Servlet�ļ�Ϊ���ɱ��漰�����صĺ�˲��֣�
 * 1.���ǰ��wordexport.js�����Ĳ�����
 * 2.��������������Ҫ����Ϣ��д�뱨��ģ���
 * 3.��ǰ��һ����Ӧ����������Ϊһ�������������ӵ�HTML���롣
 */
@WebServlet("/WordExport")
public class WordExport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//ǰ�˲���
		String Type = request.getParameter("type");
		String Range = request.getParameter("range");
		//String Staff = request.getParameter("staff");
		String Staff = Arrays.toString(request.getParameterValues("staff[]"));
		String floder = request.getParameter("floder");
		System.out.println(Staff);        

		//ϵͳʱ��
		NowString nowString = new NowString();  
		String Time = nowString.ShowTime();		//����NowString���ShowTime()����
		String Year = Time.substring(0, 4);	
		String Month = Time.substring(5, 7);
		String Day = Time.substring(8, 10);
		
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		int num5 = 0;
		int num6 = 0;
		int num7 = 0;

		//�ϴ��ļ�������·��
		String temPath = "HX01\\upload\\"+floder;
		String temDocPath = "HX01\\report";
		String wordPath = request.getSession().getServletContext().getRealPath("/")+temPath;
		
		System.out.println(wordPath); 
		
		String DocPath = request.getSession().getServletContext().getRealPath("/")+temDocPath;
		//�ļ���·��
		File fileDirectory = new File(wordPath);      
		//��ŵ�ǰĿ¼�������ļ�
		@SuppressWarnings("unused")
		List<String> filelist = new ArrayList<String>();   
		//�������ļ���
		File[] files = fileDirectory.listFiles();       
		if(null!=files){
			for(int i=0; i<files.length; i++){
				File temfile = files[i];
				//����ļ�����
				String name = temfile.getName();    
				//��ȡ�ļ���
				String beforePointLetter = name.substring(0,name.indexOf("."));  
				//�ж��ļ�����
				      if(beforePointLetter.contains("ѯ�ʱ�¼")){
					num1++;
				}else if(beforePointLetter.contains("�ֳ���Ƭ")){
					num2++;
				}else if(beforePointLetter.contains("ִ����¼��Ӱ��")){
					num3++;
				}else if(beforePointLetter.contains("�״������Ƭ")){
					num4++;
				}else if(beforePointLetter.contains("����������Ϣ����")){
					num5++;
				}else if(beforePointLetter.contains("����֤��")){
					num6++;
				}else if(beforePointLetter.contains("��Ա֤��")){
					num7++;
				}
			}
		}else{
			return;
		}
		//��д����
		try {

			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("shipname", "xx��");
			dataMap.put("time", Time);
			dataMap.put("range", Range);
			dataMap.put("type", Type);
			dataMap.put("n1", num1);
			dataMap.put("n2", num2);
			dataMap.put("n3", num3);
			dataMap.put("n4", num4);
			dataMap.put("n5", num5);
			dataMap.put("n6", num6);
			dataMap.put("n7", num7);
			dataMap.put("staff", Staff);
			dataMap.put("year", Year);
			dataMap.put("month", Month);
			dataMap.put("day", Day);
			
		    DocCreater dc = new DocCreater();      //����DocCreter���createDoc()����
			dc.createDoc(dataMap, "/com/law/template", "template.ftl", DocPath+"/20171124.doc");	
			
		//��Ӧ����
			response.setContentType("Content-Disposition;charset=utf-8");
			String result="<a href=\"/LawEnforcementSystem/HX01/report/"+ "20171124.doc\" download=\""+"WordExport"+"\">Download</a>"; 			
			response.setContentType("text/html");		
			response.getWriter().print(result);
		 			
		} catch (Exception e) {	
			e.printStackTrace();
		}     		
	}

}