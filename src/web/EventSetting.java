package com.law.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EventSetting
 */
public class EventSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	    String strType=request.getParameter("Type").toString();//获得前端传递来的数据
	    String strFilePahtName=request.getParameter("Value");//获得前端传递来的数据(文件夹名称)；
	   
	    String temPath = "HX01\\upload";
	    String strFilePath = request.getSession().getServletContext().getRealPath("/")+temPath; //相对路径的目录
	    

		if(strType.contains("radioclick")) //查询所有的文件夹名 等于算法符无法使用?值类型与引用类型的问题？与.net不太一致。
		{
			File file = new File(strFilePath);   
	        // 获得该文件夹内的所有文件   
	        File[] array = file.listFiles();   
	        StringBuilder strOutputText=new StringBuilder();
	        strOutputText.append("<select name=\"yiyou\" id=\"selectYY\" style=\"width:210px\"> ");
	        
	        for(int i=0;i<array.length;i++)
	        {
	        	
	          if(array[i].isDirectory())
	          {
	        	  
	        	strOutputText.append("<option value=\"ex\" style=\"background-color: #6462e2\">");
	        	strOutputText.append(array[i].toString());
	        	strOutputText.append("</option>");
	        	}
	        }
	        
	        strOutputText.append("  </select>" );
	        response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(strOutputText.toString());
		}
		else//新建文件夹
		{
			String strFilePathNew="";//新建任务的名称
			if(strType.contains("Insert"))
			{
				 strFilePathNew=strFilePath+"\\"+strFilePahtName; //因为是传入的任务名，所以文件夹名需要加上根目录
			}
			else if(strType.contains("Exist"))
			{
				 strFilePathNew=strFilePahtName;
			}
			
			
			File file =new File(strFilePathNew);    
			if  (!file.exists()&& !file.isDirectory())      
			{       
			    System.out.println("//不存在");  
			    file.mkdir();    
			} else   
			{  
			    System.out.println("//目录存在");  
			}  
			
			String name = strFilePathNew.substring(strFilePathNew.lastIndexOf("\\")+1);
			String result = "<span id=\"floder\" >"+name+"</span>";
			response.setContentType("text/html;charset=utf-8");
			//System.out.println(result);
		    response.getWriter().print("上传至文件夹：" + result);  //将文件夹名字发到前端
		    
			HttpSession session = request.getSession();
		    session.setAttribute("realpath", name);
				
		}		
				
		

		
	}

}