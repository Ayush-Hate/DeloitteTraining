package def;
import java.util.*;
import oracle.jdbc.*;
import java.sql.*;
import java.sql.Date;

class DBMgr {
	
	private String username = "hr";
	private String password = "hr";
	private String url = "jdbc:oracle:thin:@192.168.1.33:1522:xe";
	Driver driver = null;
	Connection conn = null;
	
	public DBMgr() {
		try {
			driver = new OracleDriver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e) {}		
	}
	
	void addPatient(int pid, String n, String e, Date d) {
		String q = "INSERT INTO PATIENTS_2 VALUES(?,?,?,?)";
		try {
			PreparedStatement p = conn.prepareStatement(q);
			p.setInt(1, pid);
			p.setString(2,n);
			p.setString(3,e);
			p.setDate(4,d);//CHECK
			int res = p.executeUpdate();
			if(res > 0)
				System.out.println("Inserted!");
			else
				System.out.println("Not inserted");
		}
		catch(Exception ex) {System.out.println("Not inserted");}
	}

	void addPrescription(int pID, Date d, int patID, String medName) {
		String q = "INSERT INTO PRESCRIPTION_2 VALUES(?,?,?,?)";
		try {
			PreparedStatement p = conn.prepareStatement(q);
			p.setInt(1, pID);
			p.setDate(2, d);
			p.setInt(3, patID);
			p.setString(4, medName);//CHECK
			int res = p.executeUpdate();
			if(res > 0)
				System.out.println("Inserted!");
			else
				System.out.println("Not inserted");
		}
		catch(Exception ex) {System.out.println("Not inserted");}
	}
	
	void deletePatient(int i) {
		String q = "DELETE FROM PATIENTS_2 WHERE PATIENT_ID = ?";
		try {
			PreparedStatement p = conn.prepareStatement(q);
			p.setInt(1, i);
			int res = p.executeUpdate();
			if(res > 0)
				System.out.println("Deleted!");
			else
				System.out.println("Not deleted");
		}
		catch(Exception e) {System.out.println("Not deleted");}
	}

	void deletePrescription(int i) {
		String q = "DELETE FROM PRESCRIPTION_2 WHERE PRESCRIPTION_ID = ?";
		try {
			PreparedStatement p = conn.prepareStatement(q);
			p.setInt(1, i);
			int res = p.executeUpdate();
			if(res > 0)
				System.out.println("Deleted!");
			else
				System.out.println("Not deleted");
		}
		catch(Exception e) {System.out.println("Not deleted");}
	}

	void displayPatientRecords() {
		String q = "SELECT * FROM PATIENTS_2";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(q);
			System.out.println("Patient ID\t\tName\t\tEmail\t\tRegistration Date");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"  "+rs.getString(3)+" \t "+rs.getDate(4));
			}
		}
		catch(Exception e) {}
	}

	void displayPrescriptionRecords() {
		String q = "SELECT * FROM PRESCRIPTION_2";
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(q);
			System.out.println("Prescription ID\t\tPrescribed Date\t\tPatient ID\t\tMedicine Name");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t\t"+rs.getDate(2)+"  "+rs.getInt(3)+" \t "+rs.getString(4));
			}
		}
		catch(Exception e) {}
	}

	void updatePatient(int patID, String n, String e, Date d) {
		String q = "UPDATE PATIENTS_2 SET PATIENT_ID = ?, PATIENT_NAME = ?, PATIENT_EMAIL = ?, PAT_REG_DATE = ? WHERE PATIENT_ID = ?";
		try {
			PreparedStatement p = conn.prepareStatement(q);
			p.setInt(1, patID);
			p.setString(2, n);
			p.setString(3, e);
			p.setDate(4, d);
			p.setInt(5, patID);
			int res = p.executeUpdate();
			if(res > 0)
				System.out.println("Updated!");
			else
				System.out.println("Not Updated");
		}
		catch(Exception ex) {System.out.println("Not Updated");}
	}

	void updatePrescription(int presID, Date d, int patID, String n) {
		String q = "UPDATE PRESCRIPTION_2 SET PRESCRIPTION_ID = ?, PRESCRIBED_DATE = ?, PATIENT_ID = ?, MEDICINE_NAME = ? WHERE PRESCRIPTION_ID = ?";
		try {
			PreparedStatement p = conn.prepareStatement(q);
			p.setInt(1, presID);
			p.setDate(2, d);
			p.setInt(3, patID);
			p.setString(4, n);
			p.setInt(5, presID);
			int res = p.executeUpdate();
			if(res > 0)
				System.out.println("Updated!");
			else
				System.out.println("Not Updated");
		}
		catch(Exception e) {System.out.println("Not Updated");}
	}
	
}


public class PatientCRUDApplication {

	public static void main(String[] args) {
		DBMgr dm = new DBMgr();
		Scanner s = new Scanner(System.in);
		int choice;
		while(true)
		{
			System.out.println("\nChoose\n1: REGISTER PATIENT\n2: REGISTER PRESCRIPTION\n3: UPDATE PATIENT\n4: UPDATE PRESCRIPTION\n5: DELETE PATIENT\n6: DELETE PRESCRIPTION\n7: SHOW PATIENTS\n8: SHOW PRESCRIPTIONS \n9: Exit\nEnter the choice: ");
			choice = s.nextInt();
			switch(choice)
			{
				case 1: System.out.print("Register Patient:\nEnter the Patient ID:");
						int p = s.nextInt();
						System.out.print("Enter Patient Name: ");
						String n = s.next();
						System.out.print("Enter Patient Email: ");
						String e = s.next();
						dm.addPatient(p, n, e, new java.sql.Date(new java.util.Date().getTime()));
						break;
				
				case 2: System.out.print("Register Prescription:\nEnter the Prescription ID:");
						int pres = s.nextInt();
						System.out.print("Enter Patient ID: ");
						int pat = s.nextInt();
						System.out.print("Enter Medicine Name: ");
						String mName = s.next();
						dm.addPrescription(pres, new java.sql.Date(new java.util.Date().getTime()), pat, mName);
						break;
		
				case 3: System.out.print("Update Patient:\nEnter the Patient ID:");
						int p1 = s.nextInt();
						System.out.print("Enter Patient Name: ");
						String n1 = s.next();
						System.out.print("Enter Patient Email: ");
						String e1 = s.next();
						dm.updatePatient(p1, n1, e1, new java.sql.Date(new java.util.Date().getTime()));
						break;
		
				case 4: System.out.print("Update Prescription:\nEnter the Prescription ID:");
						int pres1 = s.nextInt();
						System.out.print("Enter Patient ID: ");
						int pat1 = s.nextInt();
						System.out.print("Enter Medicine Name: ");
						String mName1 = s.next();
						dm.addPrescription(pres1, new java.sql.Date(new java.util.Date().getTime()), pat1, mName1);
						break;
				
				case 5: System.out.print("Delete Patient:\nEnter the Patient ID: ");
						int p2 = s.nextInt();
						dm.deletePatient(p2);
						break;

				case 6: System.out.print("Delete Prescription:\nEnter the Prescription ID: ");
						int pres2 = s.nextInt();
						dm.deletePatient(pres2);
						break;

				case 7: System.out.print("Patients:\n");
						dm.displayPatientRecords();
						break;	

				case 8: System.out.print("Prescriptions:\n");
						dm.displayPrescriptionRecords();
						break;
												
				case 9: System.out.println("Exiting!");
						s.close();
						try { dm.conn.close(); }
						catch(Exception ex) {}
						System.exit(0);
				
				default: System.out.println("Invalid choice! Enter 1, 2, 3, 4, 5, 6, 7, 8 or 9!");						
			}
		}

	}

}
