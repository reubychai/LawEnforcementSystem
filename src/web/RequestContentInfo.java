package com.law.web;

import com.law.mongo.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/* Reuby
 * �˷���ʵ�ֵĹ���Ϊ�����ݿ������ȫ�����͵�ǰ���Թ���ѯ(�д��Ľ�)
 */
public class RequestContentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

       response.setContentType("text/html;charset=UTF-8");   //��ҳ���������ַ�
        
       List<FieldInit> fieldList = new ArrayList<FieldInit>();
         GetContentInfo.getContent(fieldList);
         //����JavaBeanת��ΪJSON�ַ�����ʽ
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray=JSONArray.fromObject(fieldList,jsonConfig);
		String jsonString =jsonArray.toString();
		
         response.getWriter().write(jsonString); //��ǰ�����
	}

}
