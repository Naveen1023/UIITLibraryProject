package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class seeallborrowers1 implements Initializable{
	Connection connn;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","cosmos");
		}
		catch(Exception e) {
			System.out.println();
		}
		bookidinborrowertable.setCellValueFactory(new PropertyValueFactory<>("brno_users"));
		accidinborrowertable.setCellValueFactory(new PropertyValueFactory<>("accno_users"));
		borrowernameinborrowertable.setCellValueFactory(new PropertyValueFactory<>("brname_users"));
		rollnoinborrowertable.setCellValueFactory(new PropertyValueFactory<>("brr_no"));
		doiinborrowertable.setCellValueFactory(new PropertyValueFactory<>("doi_users"));
		
		
	}
	
	@FXML
		private TableView<BorrowerUsers> borrowerTable;
		@FXML
		private TableColumn<BorrowerUsers, Integer> bookidinborrowertable;
		@FXML
		private TableColumn<BorrowerUsers, Integer> accidinborrowertable;
		@FXML
		private TableColumn<BorrowerUsers, Integer> borrowernameinborrowertable;
		@FXML
		private TableColumn<BorrowerUsers, Integer> rollnoinborrowertable;
		@FXML
		private TableColumn<BorrowerUsers, Integer> doiinborrowertable;
		@FXML
		private ObservableList<BorrowerUsers> borrowerData =  FXCollections.observableArrayList();


		@FXML
		public void seeAllBorrowers() throws Exception{
			
			Connection conn=null;
			 try{
//				 Class.forName("oracle.jdbc.driver.OracleDriver");
//				 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","cosmos"); 
				
				 String sql = "select * from borrowers";
				 PreparedStatement pstmt = connn.prepareStatement(sql);
				 ResultSet rs = pstmt.executeQuery();
				 borrowerData.clear();
				while(rs.next()){
					 borrowerData.add(new BorrowerUsers(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
				 }
				 borrowerTable.setItems(borrowerData);
				 pstmt.close();
				 rs.close();
			 }
			 catch(Exception ex){
				System.out.println(ex.getMessage());
			 }
		}
		
		@FXML
		JFXTextField specifiedBorrower;
		@FXML
		public void seeSpecifiedBorrower()
		{
			try {
//			 Class.forName("oracle.jdbc.driver.OracleDriver"); 
//			 String s4 = "jdbc:oracle:thin:@localhost:1521:XE"; 
//			 Connection conn = DriverManager.getConnection(s4,"system","cosmos"); 
			 String sql="Select * from borrowers where borrower_no ="+Integer.parseInt(specifiedBorrower.getText());
			 PreparedStatement p = connn.prepareStatement(sql);
			 ResultSet r = p.executeQuery();
			 borrowerData.clear();
				while(r.next()){
					 borrowerData.add(new BorrowerUsers(r.getInt(1),r.getInt(2),r.getString(3),r.getInt(4),r.getString(5)));
				 }
				 borrowerTable.setItems(borrowerData);
				 p.close();
				 r.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	@FXML
	void pressingEnterInSeeBorrowersSection(KeyEvent event){
			
		if(event.getCode() == KeyCode.ENTER){
			try {
//			 Class.forName("oracle.jdbc.driver.OracleDriver"); 
//			 String s4 = "jdbc:oracle:thin:@localhost:1521:XE"; 
//			 Connection conn = DriverManager.getConnection(s4,"system","cosmos"); 
			 String sql="Select * from borrowers where borrower_no ="+Integer.parseInt(specifiedBorrower.getText());
			 PreparedStatement p = connn.prepareStatement(sql);
			 ResultSet r = p.executeQuery();
			 borrowerData.clear();
				while(r.next()){
					 borrowerData.add(new BorrowerUsers(r.getInt(1),r.getInt(2),r.getString(3),r.getInt(4),r.getString(5)));
				 }
				 borrowerTable.setItems(borrowerData);
				 p.close();
				 r.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}	
			
		}		
	
	}	
}