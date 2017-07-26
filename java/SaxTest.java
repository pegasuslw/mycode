import java.util.ArrayList;  
import java.util.List;  
import java.io.*;
  
import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler; 

import javax.xml.parsers.SAXParser; 
import javax.xml.parsers.SAXParserFactory; 

public class SaxTest extends DefaultHandler{

	private List<Student> list = new ArrayList<Student>();
	private Student student;
	private String tagName;


	public  List<Student> getList(){
		return list;
	}
	public void setList(List<Student> list){
		this.list = list;
	}

	@Override
	public void startDocument() throws SAXException{
		//list.clear();
	}
	@Override
	public void endDocument() throws SAXException{
	}

	@Override 
	public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException{
		if(qName.equals("student")){
			student = new Student();
			student.setId(Integer.parseInt(attributes.getValue(0)));
			student.setGroup(Integer.parseInt(attributes.getValue(1)));
		}
		tagName = qName;
		//System.out.println("startElement tagName="+tagName);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equals("student")){
			list.add(student);
		}
		tagName = null;
	}

	@Override
	public void characters(char[] chs, int start, int length) throws SAXException{
		if(null == tagName){
			return;
		}
		//System.out.println("characters tagName="+tagName);
		String data = new String(chs, start, length);
		//System.out.println("characters value="+data);
		if(tagName.equals("name")){
			student.setName(data);
		}else if(tagName.equals("sex")){
			student.setSex(data);
		}else if(tagName.equals("age")){
			student.setAge(Integer.parseInt(data));
		}else if(tagName.equals("email")){
			student.setEmail(data);
		}else if(tagName.equals("birghday")){
			student.setBirthday(data);
		}else if(tagName.equals("memo")){
			student.setMemo(data);
		}
	}

	public static void main(String[] args){
		//System.out.println("test");
		SAXParser parse = null;
		try{
			parse = SAXParserFactory.newInstance().newSAXParser();
			SaxTest handler = new SaxTest();
			InputStream stream = SaxTest.class.getClassLoader().getResourceAsStream("students.xml");
			parse.parse(stream, handler);
			List<Student> list = handler.getList();
			for(Student stu : list){
				System.out.println(stu);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class Student{
	private int id;
	private int group;
	private String name;
	private String sex;
	private int age;
	private String email;
	private String memo;
	private String birthday;

	public int getId(){ 
		return id;
	}
	public void setId(int id){
		this.id = id;
	}

	public int getGroup(){
		return group;
	}
	public void setGroup(int group){
		this.group = group;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getSex(){
		return sex;
	}
	public void setSex(String sex){
		this.sex = sex;
	}

	public int getAge(){
		return age;
	} 
	public void setAge(int age){
		this.age = age;
	}

	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	public String getMemo(){
		return memo;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}

	public String getBirthday(){
		return birthday;
	}
	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public String toString(){
		return "id:"+id
		      +"    group:"+group
		      +"    name:"+name
		      +"    sex:"+sex
		      +"    email:"+email
		      +"    birthday:"+birthday
		      +"    memo:"+memo;
	}

}