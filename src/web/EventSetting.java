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
		
	
	    String strType=request.getParameter("Type").toString();//���ǰ�˴�����������
	    String strFilePahtName=request.getParameter("Value");//���ǰ�˴�����������(�ļ�������)��
	   
	    String temPath = "HX01\\upload";
	    String strFilePath = request.getSession().getServletContext().getRealPath("/")+temPath; //���·����Ŀ¼
	    

		if(strType.contains("radioclick")) //��ѯ���е��ļ����� �����㷨���޷�ʹ��?ֵ�������������͵����⣿��.net��̫һ�¡�
		{
			File file = new File(strFilePath);   
	        // ��ø��ļ����ڵ������ļ�   
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
		else//�½��ļ���
		{
			String strFilePathNew="";//�½����������
			if(strType.contains("Insert"))
			{
				 strFilePathNew=strFilePath+"\\"+strFilePahtName; //��Ϊ�Ǵ�����������������ļ�������Ҫ���ϸ�Ŀ¼
			}
			else if(strType.contains("Exist"))
			{
				 strFilePathNew=strFilePahtName;
			}
			
			
			File file =new File(strFilePathNew);    
			if  (!file.exists()&& !file.isDirectory())      
			{       
			    System.out.println("//������");  
			    file.mkdir();    
			} else   
			{  
			    System.out.println("//Ŀ¼����");  
			}  
			
			String name = strFilePathNew.substring(strFilePathNew.lastIndexOf("\\")+1);
			String result = "<span id=\"floder\" >"+name+"</span>";
			response.setContentType("text/html;charset=utf-8");
			//System.out.println(result);
		    response.getWriter().print("�ϴ����ļ��У�" + result);  //���ļ������ַ���ǰ��
		    
			HttpSession session = request.getSession();
		    session.setAttribute("realpath", name);
				
		}		
				
		

		
	}

}