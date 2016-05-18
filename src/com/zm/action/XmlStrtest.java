package com.zm.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;



public class XmlStrtest {
	
	
	public static void main(String[] args) {
		String xmlStr ="<?xml version='1.0' encoding='utf-8'?><bookstore><book category='children'><title lang='en'>Harry Potter</title><author>J K. Rowling</author><year>2005</year><price>29.99</price></book><book category='cooking'><title lang='en'>Everyday Italian</title><author>Giada De Laurentiis</author><year>2005</year><price>30.00</price></book><book category='web'><title lang='en'>Learning XML</title><author>Erik T. Ray</author><year>2003</year><price>39.95</price></book><book category='web'><title lang='en'>XQuery Kick Start</title><author>James McGovern</author><author>Per Bothner</author><author>Kurt Cagle</author><author>James Linn</author><author>Vaidyanathan Nagarajan</author><year>2003</year><price>49.99</price></book></bookstore>";
		try {
			//解析xml字符串
			readStringXml(xmlStr);
			//将xml转换成map集合
			readStringXmlMap(xmlStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析xml字符串
	 * @param args
	 * @throws Exception 
	 */
	public static void readStringXml(String xmlStr) {
		
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element rootElt = doc.getRootElement(); // 获取根节点

		    System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
		    // normal解析    
	        Element normal = rootElt.element("bookstore");  
	        
	        Iterator iter = rootElt.elementIterator("book"); // 获取根节点下的子节点book

	        while (iter.hasNext()) {

                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到book节点下的子节点title值
                String year = recordEle.elementTextTrim("year");
               
                System.out.println("解析xml字符串");
                System.out.println("title:" + title);
                System.out.println("year:" + year);
                System.out.println("------------------------");
	        }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/**将xml字符串转换成map
	 * @param xmlStr
	 */
	public static void readStringXmlMap(String xmlStr){
		Map<String,Map> maplst= new HashMap<String,Map>(); 
		Map map = new HashMap();
		Document doc;
		try {
			doc = DocumentHelper.parseText(xmlStr);
			Element rootElt = doc.getRootElement(); // 获取根节点
		    System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
		    // normal解析    
	        Element normal = rootElt.element("bookstore");  
	        Iterator iter = rootElt.elementIterator("book"); // 获取根节点下的子节点book
	        while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String title = recordEle.elementTextTrim("title"); // 拿到book节点下的子节点title值
                String year = recordEle.elementTextTrim("year");
                String author = recordEle.elementTextTrim("author");
                String price = recordEle.elementTextTrim("price");
                //赋值到Map
                map.put("title", title);
                map.put("year", year);
                map.put("author", author);
                map.put("price", price);
                maplst.put(title, map);  //赋值到Map集合
	        }
	        //将xml字符串转换成Map
	        System.out.println("将xml字符串转换成Map");
	        System.out.println(maplst.toString());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
