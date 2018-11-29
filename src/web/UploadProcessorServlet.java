package com.law.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 
 * �ļ��ϴ� ���岽�裺 1����ô����ļ���Ŀ���� DiskFileItemFactory Ҫ���� 2�� ���� request ��ȡ ��ʵ·��
 * ������ʱ�ļ��洢���� �����ļ��洢 ���������洢λ�ÿɲ�ͬ��Ҳ����ͬ 3���� DiskFileItemFactory ��������һЩ ����
 * 4����ˮƽ��API�ļ��ϴ����� ServletFileUpload upload = new ServletFileUpload(factory);
 * Ŀ���ǵ��� parseRequest��request������ ��� FileItem ����list ��
 * 
 * 5���� FileItem ������ ��ȡ��Ϣ�� ������ �ж� ���ύ��������Ϣ �Ƿ��� ��ͨ�ı���Ϣ �������� 6�� ��һ��. �õ����� �ṩ��
 * item.write( new File(path,filename) ); ֱ��д�������� �ڶ���. �ֶ�����
 * 
 */
public class UploadProcessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = request.getSession();
		String realPath = (String) hs.getAttribute("realpath");
		
		System.out.println(realPath);

		String upfloder = "HX01\\upload\\"+realPath;
		String temfloder = "HX01\\uploadTemp";
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+upfloder;
		
		request.setCharacterEncoding("utf-8"); // ���ñ���
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// ��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// ���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		// ������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem
		 * ��ʽ�� Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(temfloder));
		// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024 * 1024);

		// ��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			// �ύ��������Ϣ�������list����
			// ����ζ�ſ����ϴ�����ļ�
			// ��������֯����
			List<FileItem> list = upload.parseRequest(request);
			// ��ȡ�ϴ����ļ�
			FileItem item = getUploadFileItem(list);
			
			// ��ȡ�ļ���
			String filename = getUploadFileName(item);
			
			
			
			System.out.println("���Ŀ¼:" + upfloder);
			System.out.println("�ļ���:" + filename);

			// ����д��������
			item.write(new File(uploadPath, filename)); // �������ṩ��
			
			PrintWriter writer = response.getWriter();
			
			writer.print("{");
			writer.print("msg:\"�ļ���С:"+item.getSize()+",�ļ���:"+filename+"\"");
			writer.print("}");
			
			writer.close();
		
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if(!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}
	
	private String getUploadFileName(FileItem item) {
		// ��ȡ·����
		String value = item.getName();
		// ���������һ����б��
		int start = value.lastIndexOf("\\");
		// ��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
		String filename = value.substring(start + 1);
		
		return filename;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
