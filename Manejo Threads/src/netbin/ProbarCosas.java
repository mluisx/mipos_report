package netbin;

//import java.util.Calendar;
//import java.util.Date;
import java.sql.*;
import java.io.*;

public class ProbarCosas {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws ClassNotFoundException,
    IOException {
		
	/*	Date Fecha = new Date();
		//Calendar Ort = null;
		//Ort.setTime(Fecha);
        Date now = Calendar.getInstance().getTime();
        System.out.println("Calendar.getInstance().getTime() : " + now);
        System.out.println();
		System.out.println("DATE() = " + Fecha);
		//System.out.println("CALENDAR() = " + Ort.getTime());
		// TODO Auto-generated method stub  */
	
	try {
	        /** 
	         *  Load jdbc driver for MySQL 
	         */

		Class.forName("com.mysql.jdbc.Driver");
			
	       //  Loads database name of a mysql database into url
	       //  //localhost says that it is on the current machine and test says that the 
	       //  name of the database is test. 

        String url = "jdbc:mysql://localhost/maurixdb?user=root&password=glendora";
        
	       //  We have to establish a jdbc connection to the database

	               
        Connection connection = DriverManager.getConnection(url);

        /* students is a mysql table in the database test on
	                the same machine.  It has the columns
	                     int         studentid;
	                     String    name;
	                     String    course;
	                     String    grade;

	                the query "select * from students" selects all of the columns
	                and all of the rows (since there is no where clause)
         */   
//      String qs = "select * from usuarios";
        String qs = "select * from vuelos where CodigoAeropuertoOrigen = ( " +
        "select Codigo from aeropuertos where Ciudad = \"" + "Posadas" + "\" ) and CodigoAeropuertoDestino = ( " + 
        "select Codigo from aeropuertos where Ciudad = \"" + "Capital Federal" + "\" )";

        Statement stmt = connection.createStatement();

        /* ResultSet is a class that defines a storage place in your program 
	                for the rows that the query selects.  Here we are saying that rs
	                is a variable of type ResultSet.  It gets the results of the query
	                stored in the String qs being executed.
         */

        ResultSet  rs = stmt.executeQuery(qs);

        // Print out headings

        System.out.println("nombre"+"\t   "+"edad");

        // The while loop allows us to walk through the ResultSet rs
        //  rs.next() sets us to the next row until all of the rows have 
        //  used.  It must be used BEFORE you can use any of the
        //  rs.getInt("name of column in the database that is of type int");
        //  or rs.getString("name of column in the database that is of type 
        //  String");

        /*	           while (rs.next()){

	         	    System.out.println(rs.getString( "nombre" ) +"\t\t"+
	         	                           rs.getInt( "edad" ));

	           }*/

        rs.next();
        int VueloNro = rs.getInt("NroVuelo");
        System.out.println("Nro Vuelo = " + VueloNro);
	            
        rs.close();
        stmt.close();
        connection.close();

	} catch (SQLException sqle){ 
		sqle.printStackTrace();

		while (sqle != null) {
			String logMessage = "\n SQL Error: "+
			sqle.getMessage() + "\n\t\t"+
			"Error code: "+sqle.getErrorCode() + "\n\t\t"+
			"SQLState: "+sqle.getSQLState()+"\n";
			System.out.println(logMessage);
			sqle = sqle.getNextException();
		}
	}  
	}
}
