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
 * 此方法实现的功能为将数据库的数据全部发送到前端以供查询(有待改进)
 */
public class RequestContentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

       response.setContentType("text/html;charset=UTF-8");   //网页中有中文字符
        
       List<FieldInit> fieldList = new ArrayList<FieldInit>();
         GetContentInfo.getContent(fieldList);
         //设置JavaBean转化为JSON字符串格式
		JsonConfig jsonConfig = new JsonConfig();
		JSONArray jsonArray=JSONArray.fromObject(fieldList,jsonConfig);
		String jsonString =jsonArray.toString();
		
         response.getWriter().write(jsonString); //向前端输出
	}

}
