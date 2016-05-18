package com.zm.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;

/**
 * dom4j xml解析
 * @author Administrator
 */
public class Dom4j {
	
	public static void main(String[] args) {
		try {
			/*List list = readXML("books.xml");
			for (int i = 0; i < list.size(); i++) {
				Map m = (Map) list.get(i);
				System.out.println(m.get("title"));
				System.out.println(m.get("author"));
				System.out.println("------------------");
				//writeXML(m);
			}*/
			updateRoot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据父节点属性更新当前节点
	 * @throws Exception
	 */
	public static void updateRoot() throws Exception{
		 //读取文件
        File inputXML=new File("books.xml");
        //使用SAXReader解析xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputXML);
        Element orders = document.getRootElement();
        //根据父节点属性值  查出对应节点
        Element element = parse(orders, "category", "children");
       //获取当前节点下的所有子节点，判断其值，以进行修改
        String title = element.element("title").getText();
        String author = element.element("author").getText();
        String year = element.element("year").getText();
        String price = element.element("price").getText();
        //修改节点名称
        // element.element("title").setName("SAFDASFD");
        //修改属性
        element.element("title").setText("AAAAAAAAAA");
        //可以给xml 节点添加属性
        //orders.setAttributeValue("title", "safdasfda");
       // System.out.println(document.asXML());
        Writer writer = new FileWriter(inputXML);
		OutputFormat format= OutputFormat.createPrettyPrint();//格式化
        XMLWriter xmlWriter = new XMLWriter(writer,format);
        xmlWriter.write(document);
        xmlWriter.close();
	}
	/**
	 * 根据父节点属性取出当前父节点所有信息
	 * @throws Exception
	 */
	public static void readRoot() throws Exception{
		 //读取文件
        File inputXML=new File("books.xml");
        //使用SAXReader解析xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputXML);
        Element orders = document.getRootElement();
        Element element = parse(orders, "category", "children");
        Element title = element.element("title");
        Element author = element.element("author");
        Element year = element.element("year");
        Element price = element.element("price");
        System.out.println(title.getText());
        System.out.println(author.getText());
        System.out.println(year.getText());
        System.out.println(price.getText());
  
	}
	
	/*
	 * 获得X属性结果是X值的整个标签
	 */
	public static Element parse(Element node , String type , String val) {
	    for (Iterator iter = node.elementIterator(); iter.hasNext();) {
	        Element element = (Element) iter.next();
	        Attribute name = element.attribute(type);
	        if (name != null) {
	            String value = name.getValue();
	            if (value != null && val.equals(value))
	                return element;
	            else
	                parse(element , type , val);
	        }
	    }
	    return null;
	}
	 /**
	  * xml 写入
	 * @param map
	 * @throws DocumentException
	 * @throws IOException
	 */
	public  static void writeXML(Map map) throws Exception{
	        
	        if(map != null){//判断map是否为空
	            //将订单信息写入文件
	            File inputXML=new File("e:/orderList.xml");
	            //使用 SAXReader 解析 XML 文档 orderList.xml
	            SAXReader saxReader=new SAXReader();
	            Document document=saxReader.read(inputXML);
	            System.out.println("ddd"+document.getRootElement());
	            Element orders=document.getRootElement();//根节点
	            
	            Element order = orders.addElement("book");//书名
	            //给父节点添加属性
	            order.addAttribute("category",map.get("category").toString()); 
	            
	            Element merchantId = order.addElement("title");//标题
	            merchantId.setText(map.get("title").toString());
	            
	            Element transType = order.addElement("author");//作者
	            transType.setText(map.get("author").toString());
	            //判断数据
	            //transType.setText(map.get("author") == null ? "00":map.get("author").toString());
	            
	            Element merchantOrderId = order.addElement("year");//出书年份
	            merchantOrderId.setText(map.get("year").toString());
	            
	            Element merchantOrderTime = order.addElement("price");//价格
	            merchantOrderTime.setText(map.get("price").toString());
	            	
	            
	            Writer writer = new FileWriter(inputXML);
				OutputFormat format= OutputFormat.createPrettyPrint();//格式化
	            XMLWriter xmlWriter = new XMLWriter(writer,format);
	            xmlWriter.write(document);
	            xmlWriter.close();
	        }
	    }
	
	/**
     * 读取xml文件 放入map，多个返回list
     * @param fileName
     * @return
     * @throws DocumentException
     */
    private static List readXML(String fileName) throws DocumentException{
        List orderList = new ArrayList();
        //读取文件
        File inputXML=new File(fileName);
        //使用SAXReader解析xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputXML);
        //document.getXMLEncoding()   获取xml编码格式
      //  System.out.println(document.getXMLEncoding());
        System.out.println("xml转字符串"+document.asXML());
        System.out.println("xml的绝对路径"+document.getName());
        Element orders = document.getRootElement();
        for(Iterator i = orders.elementIterator();i.hasNext();){
            Element order = (Element) i.next();
            //获取每个节点的数据
            List list = order.attributes();//获取每个父节点的属性
            DefaultAttribute e = (DefaultAttribute) list.get(0);
            System.out.println("父节点属性"+e.getName()+":"+e.getText());
            Element title = order.element("title");
            Element author = order.element("author");
            Element year = order.element("year");
            Element price = order.element("price");
            Map map = new HashMap();
            map.put(e.getName(), e.getText());
            map.put("title", title.getText());
            map.put("author", author.getText());
            map.put("year", year.getText());
            map.put("price", price.getText());
            orderList.add(map);
        }
        return orderList;
    }
}
