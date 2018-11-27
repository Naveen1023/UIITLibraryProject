package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.tools.ant.util.FileUtils;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginController{
	
	String databaseUsername="";
	String databasePassword="";
	
	@FXML
	TextField userName;
	@FXML
	PasswordField passWord;
	
	@FXML
	JFXButton login;

	
	Connection conn=null;
		
	@FXML
	void logIn() throws Exception{
		
		if(userName.getText().equals("") || passWord.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Complete Valid Information", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String test="";
		
			databaseUsername = userName.getText();
			databasePassword = passWord.getText();
			
			
			
		   //Saving credentials to the files
			
			//creating files
			//checking if files are empty or they already have credentials
			File f = new File("username.txt");
			if(!f.exists()){

			PrintWriter writer = new PrintWriter("username.txt", "UTF-8");	
			PrintWriter writer1 = new PrintWriter("password.txt", "UTF-8");
			
			
			writer.println(databaseUsername);
			writer1.println(databasePassword);
			writer.close();
			writer1.close();
			
//			DatabaseMetaData dbm = conn.getMetaData();	
//			ResultSet tables = dbm.getTables(null, null, "BOOKS", null);
//					if (tables.next()) {
//					table exists, no need to do anything....
//		}
//				else {
			try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String s = "jdbc:oracle:thin:@localhost:1521:XE";
			 conn = DriverManager.getConnection(s,databaseUsername,databasePassword);
			test="connection done";
			
			
//					   Table does not exist
						String sql = "create table books( acc_no number(10) not null unique, book_name varchar(50) not null, author varchar(100) not null, availability char(1) not null  ) ";
						String sql1 = "create table borrowers( BORROWER_NO NUMBER(15) not null ,  ACC_NO NUMBER(10) not null, BORROWER_NAME CHAR(50) not null, ROLL_NO NUMBER(10),DOI DATE not null)";
						String sql2 = "create table fine( BORROWER_NO NUMBER(5) not null, finef number(5))";
						String sql3 = "create table return( BORROWER_NO NUMBER(5) not null,  ACC_NO NUMBER(10) not null, BORROWER_NAME CHAR(50) not null, ROLL_NO NUMBER(10) not null,DOI DATE not null, dor date not null)";
						String sql5 = "create table lost(  ACC_NO NUMBER(10) not null, book_name char(50) not null, author char(100) not null)";
						String sql4 = "commit";
					    
						PreparedStatement p = conn.prepareStatement(sql);
						PreparedStatement p1 = conn.prepareStatement(sql1);
						PreparedStatement p2 = conn.prepareStatement(sql2);
						PreparedStatement p3 = conn.prepareStatement(sql3);
						PreparedStatement p5 = conn.prepareStatement(sql5);
						PreparedStatement p4 = conn.prepareStatement(sql4);
						
						
						p.executeQuery();
						p1.executeQuery();
						p2.executeQuery();
						p3.executeQuery();
						p5.executeQuery();
					    p4.executeUpdate();
				
				}

		catch(Exception n)
		{
			System.out.println(n);
		}
	}
		else
		{
			String obusername,obpassword;
			//File f = new File("username.txt");
			 BufferedReader br = new BufferedReader(new FileReader(f));
			obusername = br.readLine();
			File f1 = new File("password.txt");
			 BufferedReader br1 = new BufferedReader(new FileReader(f1));
				obpassword = br1.readLine();
				br.close();
				br1.close();
			if(databaseUsername.equals(obusername)&&databasePassword.equals(obpassword))
			{
				test = "connection done";
			}
			else{
				JOptionPane.showMessageDialog(null, "Sorry!! Wrong Combination Of Username And Password. Please check and enter again", "Warning", JOptionPane.WARNING_MESSAGE);
			
			}
		}
		
		
		try {
			if(test.equals("connection done")) {

//				Show the Main Library Scene
				Parent borrowerroot = FXMLLoader.load(getClass().getResource("/application/home.fxml"));
				Stage borrowerstage=new Stage();
				Scene borrowerscene = new Scene(borrowerroot,1100,693);
				borrowerscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				borrowerstage.setTitle("Library System Demo");
				borrowerstage.setResizable(false);
				borrowerstage.setScene(borrowerscene); 
				borrowerstage.setResizable(false);
				borrowerstage.initStyle(StageStyle.TRANSPARENT);
				borrowerstage.show();
				
				userName.setText("");
				passWord.setText("");
				Stage stage = (Stage) login.getScene().getWindow();
			    stage.close();
			}
//			else {
//				JOptionPane.showMessageDialog(null, "Wrong Combination Of Username And Password", "Warning", JOptionPane.WARNING_MESSAGE);
//			}
//			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	String name="";
	String pass="";
	@FXML
	void goToFineScene() throws Exception {
		
				
		Parent borrowerroot = FXMLLoader.load(getClass().getResource("/application/fineCalculation.fxml"));
		Stage borrowerstage=new Stage();
		Scene borrowerscene = new Scene(borrowerroot,435,269);
		borrowerscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		borrowerstage.setTitle("Library System Demo");
		borrowerstage.setResizable(false);
		borrowerstage.setScene(borrowerscene); 
		borrowerstage.setResizable(false);
		borrowerstage.show();
		
	}
	
	@FXML
	void pressingEnterInLoginForm(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			try {
				logIn();
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
}

	