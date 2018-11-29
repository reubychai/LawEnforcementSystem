package com.law.mongo;

import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/* Reuby
 * 此.java文件实现的功能为：
 * 1.连接MongoDB并进行查询；
 * 2，将查询结果放在list集合中
 */
public class GetContentInfo {
	public static void getContent(List<FieldInit> fieldList)
	{
	    try{
	
	    	//连接到mongodb
	    	@SuppressWarnings("resource")
			MongoClient mongoClient = new MongoClient("localhost",27017);
	    	MongoDatabase db = mongoClient.getDatabase("smu"); 
	    	MongoCollection<Document> coll = db.getCollection("test");
	    	if(coll != null){
	    	System.out.println("ok");
	    }else{
	    	System.out.println("qaq");
	    }
	    	    	
	         FindIterable<Document> findIterable = coll.find();  //获取迭代器FindIterable<Document> 
	         MongoCursor<Document> mongoCursor = findIterable.iterator();  //获取游标MongoCursor<Document> 
	         while(mongoCursor.hasNext()){  
	        	      
	        	    Document show = mongoCursor.next();
	        	    
	        	    String Name = (String)show.get("name");
	        	    String Object = (String)show.get("object");
	        	    String Type = (String)show.get("type");
	        	    String Range1 = (String)show.get("range1");
	        	    String Range2 = (String)show.get("range2");
	        	    String Range3 = (String)show.get("range3");
	        	    String Authority = (String)show.get("authority");
	        	    String Law = (String)show.get("law");
	        	    String Responsibility = (String)show.get("responsibility");
	        	    String Mean = (String)show.get("mean");
	        	    String Regulation = (String)show.get("regulation");
	        	    String Staff = (String)show.get("staff");
	        	    String Way = (String)show.get("way");
	        	    
		    		FieldInit FieldBuf = new FieldInit();
		    		FieldBuf.setName(Name);
		    		FieldBuf.setObject(Object);
		    		FieldBuf.setType(Type);
		    		FieldBuf.setRange1(Range1);
		    		FieldBuf.setRange2(Range2);
		    		FieldBuf.setRange3(Range3);
		    		FieldBuf.setAuthority(Authority);
		    		FieldBuf.setLaw(Law);
		    		FieldBuf.setResponsibility(Responsibility);
		    		FieldBuf.setMean(Mean);
		    		FieldBuf.setRegulation(Regulation);
		    		FieldBuf.setStaff(Staff);
		    		FieldBuf.setWay(Way);
		    		
		    		fieldList.add(FieldBuf); 
	         }	    	
	    	//查询关闭
	         mongoCursor.close();	
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e.toString());
	    }
     }
}
