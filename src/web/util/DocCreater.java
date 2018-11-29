package com.law.web.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class DocCreater {

	private Configuration configuration = null;

	@SuppressWarnings("deprecation")
	public DocCreater() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");				
	}

    public void createDoc(Map<String, Object> dataMap, String templatePackagePath, String templateName,
            String docPath) {
        configuration.setClassForTemplateLoading(this.getClass(), templatePackagePath);
        Template t = null;
        try {
            t = configuration.getTemplate(templateName, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File(docPath);
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
            out.flush();      //test
            out.close();      //test
            
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
