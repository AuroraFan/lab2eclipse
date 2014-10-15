package lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Function {
	String driver = "com.mysql.jdbc.Driver";
	//String url = "jdbc:mysql://localhost:3306/bookdb";
	String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_selab2";
	Connection conn = null;
	
	//connect to mysql
	public Connection linksql(){
		try{
			Class.forName(driver);
			//conn = DriverManager.getConnection(url,"root","");
			//System.out.println("===++++");
			conn = DriverManager.getConnection(url,"j1121jkzyw","ljw3ljz3zjzyxwh4i5m31345zziz0jmlkh5kjj0l");
			//System.out.println("======="+conn);
		}
		catch(Exception e){
			e.printStackTrace();
		}return conn;
	}
	
	//view list of books according to authorid
	//输入：index.jsp,通过AuthorID，列出符合id的书单
	//输出：booklist.jsp
	public List<String> viewlist(String Name){
		List<String> booklist = new ArrayList<String>();
		Function book = new Function();
		System.out.println(Name);
		try{
			conn = book.linksql();
			String sql = "select Title from book natural join author where Name = ?";
			System.out.println(conn);
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setString(1, Name);//占位符下标从1开始
			ResultSet res = pre.executeQuery();
			while(res.next()){
				booklist.add(res.getString("Title"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return booklist;
	}
	
	//view book informationo
	//输入：booklist.jsp,显示信息
	//输出：bookinf.jsp
	public Book view_book_inf(String Title){
		Function book = new Function();
		conn = book.linksql();
		Book inf = new Book();
		//通过title输出图书具体信息
		try{
			String sql = "select Publisher, AuthorID, PublishDate, Price from book where Title = ?";
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setString(1, Title);
			ResultSet res = pre.executeQuery();
			inf.Title = Title;
			while(res.next()){
				inf.AuthorID = res.getInt("AuthorID");
				inf.Publisher = res.getString("Publisher");
				inf.PublishDate = res.getString("PublishDate");
				inf.Price = res.getDouble("Price");
			}
			String auth = "select Name,Age,Country from author where AuthorID = ?";
			PreparedStatement sta = conn.prepareStatement(auth);
			sta.setInt(1, inf.AuthorID);
			res = sta.executeQuery();
			while(res.next()){
				inf.Name = res.getString("Name");
				inf.Age = res.getInt("Age");
				inf.Country = res.getString("Country");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return inf;
	}
	
	//delete book
	public void deletebook(String Title){
		Function book = new Function();
		conn = book.linksql();
		try{
			String del = "delete from book where Title = ?";
			PreparedStatement pre = conn.prepareStatement(del);
			pre.setString(1, Title);
			pre.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//update book information
	//输入：bookinf.jsp,通过ISBN更新数据
	//输出：bookinf.jsp
	public void updateinf(Book newinf){
		Function book = new Function();
		conn = book.linksql();
		try{
			String upd = "select AuthorID,Age,Country from author where Name = ?";
			PreparedStatement pre = conn.prepareStatement(upd);
			pre.setString(1, newinf.Name);
			ResultSet res = pre.executeQuery();
			while(res.next()){
				newinf.AuthorID = res.getInt("AuthorID");
				newinf.Age = res.getInt("Age");
				newinf.Country = res.getString("Country");
			}
			upd = "update book set Publisher = ?, PublishDate = ?, Price = ?, AuthorID = ? where Title = ?";
			pre = conn.prepareStatement(upd);
			pre.setString(1, newinf.Publisher);
			pre.setString(2, newinf.PublishDate);
			pre.setDouble(3, newinf.Price);
			pre.setInt(4, newinf.AuthorID);
			pre.setString(5, newinf.Title);
			pre.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function test = new Function();
		//System.out.println("memeda");
		Book book = new Book();
		Author author = new Author();
		//conn = test.linksql();
		
		List<String> booklist=  new ArrayList<String>();
		booklist = test.viewlist("Author1");
		System.out.println("====="+booklist);
		
		Book inf_book = new Book();
		//Author inf_author = new Author();
		inf_book = test.view_book_inf("Title1");
		System.out.println(inf_book.ISBN);
		System.out.println(inf_book.Title);
		System.out.println(inf_book.AuthorID);
		System.out.println(inf_book.Price);
		System.out.println(inf_book.Age);
		System.out.println(inf_book.Country);
		
		book.ISBN = 1;book.Title = "aaaaa";book.AuthorID = 102;book.Publisher = "pub8";
		book.PublishDate = "1.1.1";book.Price = 0.01;
		//test.updateinf(book);
		//test.deletebook(book.Title);
	}*/
}
