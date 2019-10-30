package iesserpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PruebaMySql {
	private static Connection connection;
	public static void main(String[] args) throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba?serverTimezone=UTC", "root", "sistemas");
		
		showAll();
		
		insert();
		update();
		showAll();
	    
		connection.close();
	}
	
	public static void showAll() throws SQLException {
		Statement statement= connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select * from categoria order by id");
		
		while (resultSet.next()) 
			System.out.printf("id=%s nombre=%s %n",resultSet.getLong("id"),resultSet.getString(2));
		
		statement.close();
	}
	
	public static void insert() throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("insert into categoria (nombre) values (?)");
		preparedStatement.setObject(1, "cat"+ LocalDateTime.now());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public static void update() throws SQLException{
		Scanner sc= new Scanner(System.in);
		System.out.println("Introce id a modificar:");
		String id= sc.nextLine();
		System.out.println("Introce nombre nuevo:");
		String nombre= sc.nextLine();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria set nombre=? "
				+ "where id=?");
		preparedStatement.setString(1, nombre);
		preparedStatement.setString(2, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
}
