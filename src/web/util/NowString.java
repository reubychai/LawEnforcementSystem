package com.law.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

	public class NowString {
		public String ShowTime(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String NowTime = df.format(new Date());// new Date()为获取当前系统时间
			return NowTime;
		}
}