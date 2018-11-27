package application;

public class BorrowerUsers {
	private int brno_users;
	private int accno_users;
	private String brname_users;
	private int brr_no;
	private String doi_users;
	
	public BorrowerUsers(int brno_users,int accno_users,String brname_users,int brr_no,String doi_users){
		this.accno_users=accno_users;
		this.brno_users=brno_users;
		this.brname_users=brname_users;
		this.brr_no=brr_no;
		this.doi_users=doi_users;
	}
	
	public int getBrno_users() {
		return brno_users;
	}
	public void setBrno_users(int brno_users) {
		this.brno_users = brno_users;
	}
	public int getAccno_users() {
		return accno_users;
	}
	public void setAccno_users(int accno_users) {
		this.accno_users = accno_users;
	}
	public String getBrname_users() {
		return brname_users;
	}
	public void setBrname_users(String brname_users) {
		this.brname_users = brname_users;
	}
	public int getBrr_no() {
		return brr_no;
	}
	public void setBrr_no(int brr_no) {
		this.brr_no = brr_no;
	}
	public String getDoi_users() {
		return doi_users;
	}
	public void setDoi_users(String doi_users) {
		this.doi_users = doi_users;
	}
}
