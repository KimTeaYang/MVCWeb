package common.db;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class DAOBase {
	
	protected Context ctx;
	protected DataSource ds;
	

	public DAOBase() {
		try {
			ctx = new InitialContext();
			
			ds = (DataSource)ctx.lookup(
					  "java:comp/env/oracle/myshop");
			//System.out.println("DataSource lookup seccess");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
