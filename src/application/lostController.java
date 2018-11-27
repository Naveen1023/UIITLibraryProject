package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class lostController implements Initializable{

	
	@FXML
	JFXTextField acno;
	@FXML
	JFXTextField bookName;
	@FXML
	JFXTextField author;
	@FXML
	Label addToLost;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		addToLost.setVisible(false);
	}
	
	
	@FXML
	void confirmLost() {
		String test3="";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String s = "jdbc:oracle:thin:@localhost:1521:XE";
			String obusername,obpassword;
			File f = new File("username.txt");
			 BufferedReader br = new BufferedReader(new FileReader(f));
			obusername = br.readLine();
			File f1 = new File("password.txt");
			 BufferedReader br1 = new BufferedReader(new FileReader(f1));
				obpassword = br1.readLine();
				br.close();
				br1.close();
			 Connection conn = DriverManager.getConnection(s,obusername,obpassword);
			 test3="connection done";
			 String sql="insert into lost values("+acno.getText()+",'"+bookName.getText()+"','"+author.getText()+"')";                     
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.executeQuery();
			 sql = "commit";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.executeQuery();
			 sql="update books SET Availability ='L' where  acc_no = "+acno.getText();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.executeQuery();
			 sql = "commit";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.executeQuery();
			 addToLost.setVisible(true);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if(test3.equals("")) {
			JOptionPane.showMessageDialog(null, "Wrong Combination Of Username And Password", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}
