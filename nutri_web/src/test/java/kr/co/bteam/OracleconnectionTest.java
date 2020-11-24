package kr.co.bteam;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class OracleconnectionTest {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@112.164.58.7:1521:xe";
	private static final String USER = "Bteam";
	private static final String PW = "bteam";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}








