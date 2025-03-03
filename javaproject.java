package javafinalproject;
import java.util.Scanner;
import java.sql.*;
public class javaproject {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int accnum,pwd,choice=1;
		int y=1;
		int x;
		int balance = 10000,withdraw,deposit;
		Scanner obj = new Scanner(System.in);
		System.out.println("Enter the account Number");
		accnum = obj.nextInt();
		System.out.println("Enter the Password:");
		pwd = obj.nextInt();
		String url  = "jdbc:mysql://localhost:3306/bank";
		String user = "root";
		String pass = "darsh123@";
		Connection con  = DriverManager.getConnection(url,user,pass);
		if(accnum == 5678 && pwd == 1234) {
			while(y==1) {
				System.out.println("\n1.Withdraw\n2.Deposit\n3.Add New Customer\n4.Display Records");
				System.out.println("Enter your choice");
				choice = obj.nextInt();
				if(choice==1) {
					System.out.println("Enter the Withdrawal Amount");
					withdraw = obj.nextInt();
					String q1 = "SELECT * from customers WHERE accnum=?";
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					
					PreparedStatement stmt = con.prepareStatement(q1);
					stmt.setInt(1, accnum);
					 
					ResultSet rst=stmt.executeQuery();
					int b=0;
					while(rst.next()) {
					b=rst.getInt(4);
					}
					System.out.println("Current Balance:"+b);
					
					if(withdraw > b) {
						System.out.println("Insufficient fund");
						System.out.println("Available balance:"+b);
						
					}
					else {
						b = b - withdraw;
						System.out.println("Available balance:"+b);
						
						String q2="UPDATE customers SET bal=? WHERE accnum=?";
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						
						PreparedStatement stmt1 = con.prepareStatement(q2);
						stmt1.setInt(1, b);
						stmt1.setInt(2, accnum);
						 
						stmt1.executeUpdate();
						
					}
				}
				else if(choice == 2) {
					String q1 = "SELECT * from customers WHERE accnum=?";
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					
					PreparedStatement stmt = con.prepareStatement(q1);
					stmt.setInt(1, accnum);
					 
					ResultSet rst=stmt.executeQuery();
					int b=0;
					while(rst.next()) {
						b=rst.getInt(4);
						System.out.println("Enter the deposit:");
						deposit = obj.nextInt();
						b = b + deposit;
						System.out.println("Total Amount:"+b);
						String q3="UPDATE customers SET bal=? WHERE accnum=?";
						Class.forName("com.mysql.cj.jdbc.Driver");
						PreparedStatement stmt2 = con.prepareStatement(q3);
						stmt2.setInt(1, b);
						stmt2.setInt(2, accnum);
						stmt2.executeUpdate();
						}
				}
				else if(choice ==3) {
					int acc_num = 5678;
					String name = "Raj";
					int  Balance = 10000;
					
					String qry = "INSERT INTO customers(accnum,name,bal) Values(?,?,?)";
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					
					PreparedStatement stmt = con.prepareStatement(qry);
					
					stmt.setInt(1, acc_num);
					stmt.setString(2,name);
					stmt.setInt(3, Balance);
					stmt.executeUpdate();
					
					System.out.println("Records Inserted");
					
					
					stmt.close();
					con.close();
				}
				else if(choice == 4) {
					String q1 = "SELECT * from customers WHERE accnum=?";
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					
					PreparedStatement stmt = con.prepareStatement(q1);
					stmt.setInt(1, accnum);
					 
					ResultSet rst=stmt.executeQuery();
					int b=0;
					System.out.println("Displaying Customer Information");
					System.out.println("Id\t\tName\t\tAccountNumber\t\tBalance\n");
					while(rst.next()) {
						b=rst.getInt(4);
						System.out.print(rst.getInt(1));
						System.out.print("\t\t"+rst.getString(2));
						System.out.print("\t\t"+rst.getInt(3));
						System.out.print("\t\t\t"+rst.getInt(4));
						System.out.println();
						}
				}
				else {
					System.out.println("Invalid choice");
				}
				System.out.println("\nDo you want to continue:(0 or 1)");
				y = obj.nextInt();
				
			}
		}
		else {
			System.out.println("Unauthorised Customer");
		}	

	}

}