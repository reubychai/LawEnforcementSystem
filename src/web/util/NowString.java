package com.law.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

	public class NowString {
		public String ShowTime(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String NowTime = df.format(new Date());// new Date()Ϊ��ȡ��ǰϵͳʱ��
			return NowTime;
		}
}