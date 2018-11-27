package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AddNewBookController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		added.setVisible(false);
		
	}
	
	//ADDNEWBOOKS
		@FXML
		JFXTextField accno;
		@FXML
		JFXTextField bookname;
		@FXML
		JFXTextField author;
		
		
		//label
		@FXML
		Label added;
		
		// functions
		@FXML
		void addnewbook(){
			try{
				String obusername,obpassword;
				File f = new File("username.txt");
				 BufferedReader br = new BufferedReader(new FileReader(f));
				obusername = br.readLine();
				File f1 = new File("password.txt");
				 BufferedReader br1 = new BufferedReader(new FileReader(f1));
					obpassword = br1.readLine();
					br.close();
					br1.close();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String s = "jdbc:oracle:thin:@localhost:1521:XE";
			Connection conn = DriverManager.getConnection(s,obusername,obpassword);

			String s1 = accno.getText();
			String s2 = bookname.getText();
			s2=s2.toUpperCase();
			String s3=author.getText();
			s3=s3.toUpperCase();
			String s4="Y";
			String sql="Insert into books values("+s1+",'"+s2+"',"+"'"+s3+"',"+"'"+s4+"')";
			PreparedStatement p=conn.prepareStatement(sql);
			p.execute();
			added.setVisible(true);		
			}
			catch(Exception q)
			{
				System.out.println(q.getMessage());
			}
		}
		@FXML
		void reset()
		{
			accno.setText("");
			bookname.setText("");
			author.setText("");
			added.setVisible(false);
		}
		
}
