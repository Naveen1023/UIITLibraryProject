package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller implements Initializable{
	Connection connn;
	//title bar code
	double x,y;
	 @FXML
	 void dragged(MouseEvent evt){
		 Stage stage = (Stage) ((Node)evt.getSource()).getScene().getWindow();
		 stage.setX(evt.getScreenX()-x);
		 stage.setY(evt.getScreenY()-y);
	 }
	 @FXML
	 void pressed(MouseEvent evt){
		x= evt.getSceneX();
		y=evt.getSceneY();
	 }
	 
	 //close button
	 @FXML
	 void closeButton(ActionEvent event){
		 ((Node)(event.getSource())).getScene().getWindow().hide();
	 }
	 //minimize button
	 @FXML
	 void minimizeButton(ActionEvent e){
		 ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
		 
	 }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
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
			connn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",obusername,obpassword);	
		}
		catch(Exception e){
			System.out.println();
		}
		
		
		issued.setVisible(false);
		returned.setVisible(false);
		printerr.setVisible(false);
		//search section in home.fxml
		acrno4.setCellValueFactory(new PropertyValueFactory<>("accNo"));
		bookname3.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		author2.setCellValueFactory(new PropertyValueFactory<>("author"));
		avail.setCellValueFactory(new PropertyValueFactory<>("availability"));		
		
		brno3.setEditable(false);
		bookname2.setEditable(false);
		acrno3.setEditable(false);
		brname2.setEditable(false);
		dateofissue.setEditable(false);
		fine.setEditable(false);	
	}
	
	
	
//TilteBar Label
	@FXML
	Label title;
	
//HOME
	//Search Section
	@FXML
	JFXTextField src;
	@FXML
	TableView<Users> table;
	@FXML
	private TableColumn<Users, Integer> acrno4;
	@FXML
	private TableColumn<Users, String> bookname3;
	@FXML
	private TableColumn<Users, String> author2;
	@FXML
	private TableColumn<Users, String> avail;
	
	private ObservableList<Users> data =  FXCollections.observableArrayList();
	@FXML
	JFXTextField authorNameSearch;
	
	@FXML
	void showallBooks() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			 Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","cosmos");
			 String sql = "select * from books";
			 PreparedStatement pstmt = connn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();
			 data.clear();
			 while(rs.next()){
				 data.add(new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			 }
			 table.setItems(data);
			 pstmt.close();
			 rs.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@FXML
	void searchFunction(){
		Connection conn=null;
		 try{
			 table.setItems(null);
			 
//			 Class.forName("oracle.jdbc.driver.OracleDriver");
//			 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","cosmos"); 
			 String k="";
			 String l = "";
			 if(src.getText().equals("") && authorNameSearch.getText().equals("")||src.getText().equals(" ")|| authorNameSearch.getText().equals(" "))
			 {
				 return;
			 }
			 else
			 {
				k=src.getText();
			 	k=k.toUpperCase();
			 	l = authorNameSearch.getText();
			 	l = l.toUpperCase();
			 	String sql = "";
			 	if(k.equals(""))
			 	{
			 		sql =  "select * from books where author like '%"+l+"%'";
			 	}
			 	else if(l.equals(""))
		        	{
		        	sql = "select * from books where book_name like '%"+k+"%'";
		        	}
			 	else
			 	{
			 		sql = "select * from books where book_name like '%"+k+"%' and author like '%"+l+"%'";
			 	}
			 PreparedStatement pstmt = connn.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();
			 data.clear();
			 while(rs.next()){
				 data.add(new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			 }
			 table.setItems(data);
			 pstmt.close();
			 rs.close();
			 }
			 	
		 }
		 catch(Exception ex){
			System.out.println(ex);
		 }
	}
	@FXML
	void resetInSearch(){
		src.setText("");
		table.setItems(null);
		data.clear();
		authorNameSearch.setText("");
	}
	
	
	
	//Issue Section
	@FXML
	JFXTextField brno;
	@FXML
	JFXTextField acrno;
	@FXML
	JFXTextField brname;
	@FXML
	JFXTextField roll;
	@FXML 
	JFXDatePicker doi;
	@FXML
	Label issued;
	@FXML
	Label printerr;
	@FXML
	void confirmissue(){
		issued.setVisible(false);
		printerr.setVisible(false);
		String k1 = brno.getText();
	    String k2 = acrno.getText();
		String k3 = brname.getText();
			k3=k3.toUpperCase();
		String k4 = roll.getText();
		String k5 = doi.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		String sql = "insert into borrowers values("+k1+","+k2+",'"+k3+"',"+k4+",'"+k5+"')";
		String check="Select availability from books where acc_no="+k2;
		
		try{
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		String s = "jdbc:oracle:thin:@localhost:1521:XE";
//		Connection conn = DriverManager.getConnection(s,"system","cosmos");
		PreparedStatement pq = connn.prepareStatement(sql);
		PreparedStatement py=connn.prepareStatement(check);
		ResultSet rq=py.executeQuery();
		rq.next();
		if(rq.getString("availability").equals("N"))
		{
			printerr.setVisible(true);
			return;
		}
		pq.execute();
		sql ="update books set availability = 'N' where acc_no= "+k2;
		
		PreparedStatement pstmt = connn.prepareStatement(sql);
		pstmt.executeUpdate();
		issued.setVisible(true);
		}
		catch(Exception w)
		{
			System.out.println(w);
		}
	}
	@FXML
	void resetInIssue(){
		brno.setText("");
		acrno.setText("");
		brname.setText("");
		roll.setText("");
		issued.setVisible(false);
		printerr.setVisible(false);
		doi.setValue(null);
	}
	
//Return Section
	@FXML
	JFXTextField brno2;
	@FXML
	JFXTextField acrno2;
		
	//after conforming the return 
	@FXML
	JFXTextField brno3;
	@FXML
	JFXTextField bookname2;
	@FXML
	JFXTextField acrno3;
	@FXML
	JFXTextField brname2;
	@FXML
	JFXTextField dateofissue;
	
	@FXML
	Label returned;
	
	@FXML
	JFXTextField fine;
	
	int totalfine;
	
	@FXML
	void letsGo(){		
		 try{ 
			 String g =brno2.getText(); 
			 String h =acrno2.getText(); 
			 String sql = "select * from borrowers where borrower_no = "+g+" and acc_no ="+h; 
			 String s="Select * from books where ACC_NO ="+h; 
//			 Class.forName("oracle.jdbc.driver.OracleDriver"); 
//			 String s4 = "jdbc:oracle:thin:@localhost:1521:XE"; 
//			 Connection conn = DriverManager.getConnection(s4,"system","cosmos"); 
			 PreparedStatement p = connn.prepareStatement(sql); 
			 PreparedStatement p1=connn.prepareStatement(s); 
			 ResultSet r = p.executeQuery(); 
			 ResultSet r1=p1.executeQuery(); 
			 while(r.next()) 
			 { 
				 brno3.setText(r.getString("borrower_no")); 
				 brname2.setText(r.getString("borrower_name")); 
				 acrno3.setText(r.getString("acc_no")); 
				 dateofissue.setText(r.getString("doi").substring(0,11));
			 } 
			 while(r1.next()) 
			 { 
				 bookname2.setText(r1.getString("book_name")); 
			 } 
			 
	//fine
		 String s1 = null,s2,s3;
			
			s3="select doi from borrowers where borrower_no="+g+" and acc_no="+h;
			PreparedStatement p6=connn.prepareStatement(s3);
			ResultSet ry=p6.executeQuery();
			while(ry.next())
			{
			s1=ry.getString("doi");
			}
			ry.close();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date now = new Date();
			java.sql.Date  date= new java.sql.Date(now.getTime());
			s2=df.format(date);
			int data1=Integer.parseInt(s1.substring(8,10));
			int month1=Integer.parseInt(s1.substring(5,7));
			int data2=Integer.parseInt(s2.substring(0,2));
			int month2=Integer.parseInt(s2.substring(3,5));
			
			int diff=month2-month1;
			int monthday=diff*30;
			int days=data2-data1;
			if(days<0)
			{
				days=-days;
			}
			int totaldays=monthday+days;
			
			int DaysAfterFine=0,Amout=0;
			String sqldf = "select * from howmuchfine";
			PreparedStatement ps1 = connn.prepareStatement(sqldf);
			ResultSet res1 = ps1.executeQuery();
			
			while(res1.next())
			{
				DaysAfterFine = res1.getInt("fineafterdays");
				Amout = res1.getInt("perdayfine");
			}
			
			// fine is calculated here...
			if(totaldays>DaysAfterFine)
			{
				totaldays=totaldays-DaysAfterFine;
				totalfine=totaldays*Amout;
			}
			else
			{
				totalfine=0;
			}
			fine.setText(Integer.toString(totalfine));
		 } catch(Exception e)
		 { 
			 System.out.println(e); 
		 }
	}
	@FXML
	void confirmReturn(){
		try {
			String g = acrno2.getText();
			String h = brno2.getText();
			String br_name="" , dor="";
			int roll_no=0;
			
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			String s = "jdbc:oracle:thin:@localhost:1521:XE";
//			Connection conn = DriverManager.getConnection(s,"system","cosmos");

			String deleteq = "delete from borrowers where acc_no = "+g+" and borrower_no ="+h;	
			String selectq = "select * from borrowers where acc_no = "+g+" and borrower_no ="+h;
			String insertq = "insert into return values(?,?,?,?,?,?)";
			
			PreparedStatement p1 = connn.prepareStatement(selectq);
			PreparedStatement p2 = connn.prepareStatement(insertq);
			PreparedStatement p3 = connn.prepareStatement(deleteq);
		
			ResultSet rs = p1.executeQuery();
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date now = new Date();
			java.sql.Date  date= new java.sql.Date(now.getTime());
			String dateofissue =df.format(date);
			rs.next();
				br_name = rs.getString(3);
				roll_no = rs.getInt(4);
				dor = df.format(date).toString();	
				p2.setInt(1,Integer.parseInt(h));
				p2.setInt(2,Integer.parseInt(g));
				p2.setString(3,br_name);
				p2.setInt(4,roll_no);		
				p2.setString(5,dateofissue);
				p2.setString(6,dor);
			p2.executeQuery();
			p3.executeQuery();
			String sql ="update books set availability = 'Y' where acc_no= "+g;
			PreparedStatement pstmt = connn.prepareStatement(sql);
			pstmt.executeUpdate();
			returned.setVisible(true);
			
			//N CONFIRM RETURN IS CLICKED THE FINE IS UPDATED 
			String fineq="insert into fine values("+h+","+totalfine+")"; 
			PreparedStatement f1 = connn.prepareStatement(fineq); 
			f1.executeQuery(); 
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	@FXML
	public void resetInConfirm()
	{
		brno3.setText("");
		bookname2.setText("");
		acrno3.setText("");
		brname2.setText("");
		dateofissue.setText("");
		brno2.setText("");
		acrno2.setText("");
		returned.setVisible(false);
		fine.setText("");
	}
	@FXML
	public void addingbooks() throws Exception{

		Parent root = FXMLLoader.load(getClass().getResource("/application/addBooks.fxml"));
		Stage aboutusstage = new Stage();
		Scene scene = new Scene(root,734,380);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		aboutusstage.setTitle("UIIT Library System Demo");
		aboutusstage.setResizable(false);
		aboutusstage.setScene(scene);
		aboutusstage.show();
	}
	@FXML
	void aboutUs() throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/application/About_Us.fxml"));
		Stage aboutusstage = new Stage();
		Scene scene = new Scene(root,710,540);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		aboutusstage.setTitle("UIIT Library System Demo");
		aboutusstage.setResizable(false);
		aboutusstage.setScene(scene);
		aboutusstage.show();
	}

	
	@FXML
	void seeBorrowers() throws Exception{
		Parent borrowerroot = FXMLLoader.load(getClass().getResource("/application/BorrowersTable.fxml"));
		Stage borrowerstage=new Stage();
		Scene borrowerscene = new Scene(borrowerroot,735,493);
		borrowerscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		borrowerstage.setTitle("UIIT Library System Demo");
		borrowerstage.setResizable(false);
		borrowerstage.setScene(borrowerscene);
		borrowerstage.show();
		
	}
	
	
	@FXML
	void report() throws Exception{
		Parent borrowerroot = FXMLLoader.load(getClass().getResource("/application/reports.fxml"));
		Stage borrowerstage=new Stage();
		Scene borrowerscene = new Scene(borrowerroot,735,493);
		borrowerscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		borrowerstage.setTitle("Library System Demo");
		borrowerstage.setResizable(false);
		borrowerstage.setScene(borrowerscene); 
		borrowerstage.show();
	}
	@FXML
	void pressingKeyInTheSearchSection(KeyEvent event) {
//		if ENTER is pressed
		if(event.getCode()== KeyCode.ENTER) {
	//		Connection conn=null;
			 try{
				 table.setItems(null);
				 
	//			 Class.forName("oracle.jdbc.driver.OracleDriver");
	//			 conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","cosmos"); 
				 String k="";
				 String l = "";
				 if(src.getText().equals("") && authorNameSearch.getText().equals("")||src.getText().equals(" ")|| authorNameSearch.getText().equals(" "))
				 {
					 return;
				 }
				 else
				 {
					k=src.getText();
				 	k=k.toUpperCase();
				 	l = authorNameSearch.getText();
				 	l = l.toUpperCase();
				 	String sql = "";
				 	if(k.equals(""))
				 	{
				 		sql =  "select * from books where author like '%"+l+"%'";
				 	}
				 	else if(l.equals(""))
			        	{
			        	sql = "select * from books where book_name like '%"+k+"%'";
			        	}
				 	else
				 	{
				 		sql = "select * from books where book_name like '%"+k+"%' and author like '%"+l+"%'";
				 	}
				 PreparedStatement pstmt = connn.prepareStatement(sql);
				 ResultSet rs = pstmt.executeQuery();
				 data.clear();
				 while(rs.next()){
					 
					 data.add(new Users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				 }
				 table.setItems(data);
				 pstmt.close();
				 rs.close();
				 }
				 	
			 }
			 catch(Exception ex){
				System.out.println(ex);
			 }
		}
		
	}
	@FXML
	void pressingEnterInReturnSection(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER) {
			 try{ 
				 String g =brno2.getText(); 
				 String h =acrno2.getText(); 
				 String sql = "select * from borrowers where borrower_no = "+g+" and acc_no ="+h; 
				 String s="Select * from books where ACC_NO ="+h; 
				// Class.forName("oracle.jdbc.driver.OracleDriver"); 
				// String s4 = "jdbc:oracle:thin:@localhost:1521:XE"; 
				// Connection conn = DriverManager.getConnection(s4,"system","cosmos"); 
				 PreparedStatement p = connn.prepareStatement(sql); 
				 PreparedStatement p1=connn.prepareStatement(s); 
				 ResultSet r = p.executeQuery(); 
				 ResultSet r1=p1.executeQuery(); 
				 while(r.next()) 
				 { 
					 brno3.setText(r.getString("borrower_no")); 
					 brname2.setText(r.getString("borrower_name")); 
					 acrno3.setText(r.getString("acc_no")); 
					 dateofissue.setText(r.getString("doi").substring(0,11));
				 } 
				 while(r1.next()) 
				 { 
					 bookname2.setText(r1.getString("book_name")); 
				 } 
				 
		//fine
			 String s1 = null,s2,s3;
				int totalfine;
				s3="select doi from borrowers where borrower_no="+g+" and acc_no="+h;
				PreparedStatement p6=connn.prepareStatement(s3);
				ResultSet ry=p6.executeQuery();
				while(ry.next())
				{
				s1=ry.getString("doi");
				
				}
				ry.close();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Date now = new Date();
				java.sql.Date  date= new java.sql.Date(now.getTime());
				s2=df.format(date);
				System.out.println(s2);
				int data1=Integer.parseInt(s1.substring(8,10));
				int month1=Integer.parseInt(s1.substring(5,7));
				int data2=Integer.parseInt(s2.substring(0,2));
				int month2=Integer.parseInt(s2.substring(3,5));
				
				int diff=month2-month1;
				int monthday=diff*30;
				int days=data2-data1;
				if(days<0)
				{
					days=-days;
				}
				int totaldays=monthday+days;
				
				//fine is created specific to the data entered by the library.
				
				int DaysAfterFine=0,Amout=0;
				String sqldf = "select * from howmuchfine";
				PreparedStatement ps1 = connn.prepareStatement(sqldf);
				ResultSet res1 = ps1.executeQuery();
				
				while(res1.next())
				{
					DaysAfterFine = res1.getInt("fineafterdays");
					Amout = res1.getInt("perdayfine");
				}
				
				// fine is calculated here...
				if(totaldays>DaysAfterFine)
				{
					totaldays=totaldays-DaysAfterFine;
					totalfine=totaldays*Amout;
				}
				else
				{
					totalfine=0;
				}
				fine.setText(Integer.toString(totalfine));
			 } catch(Exception e)
			 { 
				 System.out.println(e); 
			 }
		}
	}
	
	@FXML
	void addToLostBooks() throws Exception {
		Parent borrowerroot = FXMLLoader.load(getClass().getResource("/application/lostbooks.fxml"));
		Stage borrowerstage=new Stage();
		Scene borrowerscene = new Scene(borrowerroot,508,340);
		borrowerscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		borrowerstage.setTitle("Library System Demo");
		borrowerstage.setResizable(false);
		borrowerstage.setScene(borrowerscene); 
		borrowerstage.show();
	}
	
}