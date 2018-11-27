package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class fineCalculationController  implements Initializable{
	@FXML
	TextField finePerDay;
	@FXML
	TextField fineDays;
	@FXML
	TextField name;
	@FXML
	PasswordField pass;
	@FXML
	Label show;
	@FXML 
	Label label1;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		show.setVisible(false);
		label1.setVisible(false);
	}
	
	
	@FXML
	void addOrUpdateFineCalculation() {
		String test2="";
		/*if(name.getText().equals("") || pass.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "First fill the all the deatils. ANY FIELD CAN'T BE EMPTY!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}*/
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String s = "jdbc:oracle:thin:@localhost:1521:XE";
			
			 Connection conn = DriverManager.getConnection(s,name.getText(),pass.getText());
			 
			 test2="connection done";
		if(name.getText().equals("")||pass.getText().equals(""))
		{
			label1.setVisible(true);
			test2 = "";
		}
		else if(finePerDay.getText().equals("")||fineDays.getText().equals(""))
		{
			test2 = "connection done";
			label1.setVisible(true);
		}
		
		else
			{
		
			 String sql="";
			 DatabaseMetaData dbm = conn.getMetaData();	
				ResultSet tables = dbm.getTables(null, null, "HOWMUCHFINE", null);
						if (tables.next()) {
//						table exists, just update the existing table....
							sql="truncate table howmuchfine";
							PreparedStatement pstmt = conn.prepareStatement(sql);
							pstmt.executeQuery();
							
							sql = "insert into howmuchfine values("+finePerDay.getText()+","+fineDays.getText()+")";
							pstmt = conn.prepareStatement(sql);
							pstmt.executeQuery();
							sql ="commit";
							pstmt = conn.prepareStatement(sql);
							pstmt.executeQuery();
							show.setVisible(true);
							label1.setVisible(false);
							
			}
					else {
						sql="create table howmuchfine (perdayfine number(3), fineafterdays number(4))";
						PreparedStatement pstmt = conn.prepareStatement(sql);
						pstmt.executeQuery();
						sql = "insert into howmuchfine values("+finePerDay.getText()+","+fineDays.getText()+")";
						pstmt = conn.prepareStatement(sql);
						pstmt.executeQuery();
						sql ="commit";
						pstmt = conn.prepareStatement(sql);
						pstmt.executeQuery();
						show.setVisible(true);
						label1.setVisible(false);
					}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		if(test2.equals("")) {
			JOptionPane.showMessageDialog(null, "You have entered wrong combination of Username and Password. First, correct it and TRY AGAIN !", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	
		
	
		
	}


}