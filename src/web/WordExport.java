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
 * 此Servlet文件为生成报告及其下载的后端部分：
 * 1.获得前端wordexport.js传来的参数；
 * 2.将参数及其他需要的信息，写入报告模板里；
 * 3.给前端一个响应，具体内容为一段生成下载链接的HTML代码。
 */
@WebServlet("/WordExport")
public class WordExport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//前端参数
		String Type = request.getParameter("type");
		String Range = request.getParameter("range");
		//String Staff = request.getParameter("staff");
		String Staff = Arrays.toString(request.getParameterValues("staff[]"));
		String floder = request.getParameter("floder");
		System.out.println(Staff);        

		//系统时间
		NowString nowString = new NowString();  
		String Time = nowString.ShowTime();		//调用NowString类的ShowTime()方法
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

		//上传文件夹所在路径
		String temPath = "HX01\\upload\\"+floder;
		String temDocPath = "HX01\\report";
		String wordPath = request.getSession().getServletContext().getRealPath("/")+temPath;
		
		System.out.println(wordPath); 
		
		String DocPath = request.getSession().getServletContext().getRealPath("/")+temDocPath;
		//文件夹路径
		File fileDirectory = new File(wordPath);      
		//存放当前目录中所有文件
		@SuppressWarnings("unused")
		List<String> filelist = new ArrayList<String>();   
		//遍历该文件夹
		File[] files = fileDirectory.listFiles();       
		if(null!=files){
			for(int i=0; i<files.length; i++){
				File temfile = files[i];
				//获得文件名称
				String name = temfile.getName();    
				//截取文件名
				String beforePointLetter = name.substring(0,name.indexOf("."));  
				//判断文件类型
				      if(beforePointLetter.contains("询问笔录")){
					num1++;
				}else if(beforePointLetter.contains("现场照片")){
					num2++;
				}else if(beforePointLetter.contains("执法记录仪影像")){
					num3++;
				}else if(beforePointLetter.contains("雷达截屏照片")){
					num4++;
				}else if(beforePointLetter.contains("船舶基本信息截屏")){
					num5++;
				}else if(beforePointLetter.contains("船舶证书")){
					num6++;
				}else if(beforePointLetter.contains("船员证书")){
					num7++;
				}
			}
		}else{
			return;
		}
		//填写报告
		try {

			Map<String, Object> dataMap = new HashMap<String, Object>();

			dataMap.put("shipname", "xx轮");
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
			
		    DocCreater dc = new DocCreater();      //调用DocCreter类的createDoc()方法
			dc.createDoc(dataMap, "/com/law/template", "template.ftl", DocPath+"/20171124.doc");	
			
		//响应部分
			response.setContentType("Content-Disposition;charset=utf-8");
			String result="<a href=\"/LawEnforcementSystem/HX01/report/"+ "20171124.doc\" download=\""+"WordExport"+"\">Download</a>"; 			
			response.setContentType("text/html");		
			response.getWriter().print(result);
		 			
		} catch (Exception e) {	
			e.printStackTrace();
		}     		
	}

}