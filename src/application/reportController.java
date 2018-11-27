package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class reportController {
	
	@FXML
	public void allBooks() throws IOException
	{
		try{
			Connection connn;
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
			
		
			 FileChooser chooser = new FileChooser();
		     Stage stage = null;
				File file = chooser.showOpenDialog(stage);

				String report = file.toString();
//		    String report = "C:\\Users\\Mohit\\Desktop\\UIITLibraryProjectDemo\\src\\application\\AllBooks.jrxml";
			JasperReport jr = JasperCompileManager.compileReport(report);
		    JasperPrint jp = JasperFillManager.fillReport(jr, null,connn);
		  
		    JRViewer viewer=new JRViewer(jp);
//		    JasperExportManager.exportReportToPdfFile(jp, "D://All_Books.pdf");
		    viewer.setOpaque(true);
		    viewer.setVisible(true);
		    JasperViewer.viewReport(jp,false); 
		}
			catch(Exception e)
			{}
		}
	@FXML
	public void currentBorrowers()
	{
		try{
			Connection connn;
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
			
			 FileChooser chooser = new FileChooser();
		     Stage stage = null;
				File file = chooser.showOpenDialog(stage);

				String report = file.toString();
//		    String report = "C:\\Users\\Mohit\\Desktop\\UIITLibraryProjectDemo\\src\\application\\borrowers.jrxml";
			JasperReport jr = JasperCompileManager.compileReport(report);
		    JasperPrint jp = JasperFillManager.fillReport(jr, null,connn);
		  
		    JRViewer viewer=new JRViewer(jp);
		    JasperExportManager.exportReportToPdfFile(jp, "D://Current_Borrowers.pdf");
		    viewer.setOpaque(true);
		    viewer.setVisible(true);
		    JasperViewer.viewReport(jp,false); 
		}
			catch(Exception e)
			{}
	}
	@FXML
	public void booksReturned()
	{
		try{
			Connection connn;
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
			
			 FileChooser chooser = new FileChooser();
		     Stage stage = null;
				File file = chooser.showOpenDialog(stage);

				String report = file.toString();
//		    String report = "C:\\Users\\Mohit\\Desktop\\UIITLibraryProjectDemo\\src\\application\\return.jrxml";
			JasperReport jr = JasperCompileManager.compileReport(report);
		    JasperPrint jp = JasperFillManager.fillReport(jr, null,connn);
		  
		    JRViewer viewer=new JRViewer(jp);
		    JasperExportManager.exportReportToPdfFile(jp, "D:\\Books_Returned.pdf");
		    viewer.setOpaque(true);
		    viewer.setVisible(true);
		    JasperViewer.viewReport(jp,false); 
		}
			catch(Exception e)
			{}
	}
	@FXML
	public void booksLost()
	{
		try{
			Connection connn;
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
			
			 FileChooser chooser = new FileChooser();
		     Stage stage = null;
				File file = chooser.showOpenDialog(stage);

				String report = file.toString();
//		    String report = "C:\\Users\\Mohit\\Desktop\\UIITLibraryProjectDemo\\src\\application\\lost.jrxml";
			JasperReport jr = JasperCompileManager.compileReport(report);
		    JasperPrint jp = JasperFillManager.fillReport(jr, null,connn);
		  
		    JRViewer viewer=new JRViewer(jp);
		    JasperExportManager.exportReportToPdfFile(jp, "D:\\Books_Lost.pdf");
		    viewer.setOpaque(true);
		    viewer.setVisible(true);
		    JasperViewer.viewReport(jp,false); 
		    }
			catch(Exception e)
			{}
		
	}
	@FXML
	public void booksByAuthor()
	{
		
	}
	@FXML
	public void totalFine()
	{
		
	}
	@FXML
	public void companyTender()
	{
		
	}
	
	

}
