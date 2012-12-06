package netbin;
//import interfaces.CContador;

//import java.io.FileOutputStream;
//import java.io.IOException;
import java.util.Date;

public class ProbarCosas3 {

	/**
	 * @param args
	 */
	
	ProbarCosas3() {}
	
/*	public static void EscriboLog(String S) throws IOException {
		FileOutputStream MyLog;
		MyLog = new FileOutputStream("C:\\Log.txt");
		System.out.println("Creamos Archivo");
        byte linea[] = new byte[100];
		int b = 56256254;
		linea = (byte) S;
		char s = (byte) s;
        int i = 0;
        while ((i < (S.length()-1)))
        	linea[i++] = (byte) b;
        linea[i] = (byte) 0;
//		MyLog.write(b);
        for( int A=0; linea[A] != 0; A++ )
            MyLog.write(linea[A]);
        MyLog.close();
		System.out.println("Cerramos Archivo");
	}*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    /*try { EscriboLog("ESCRITURA ARCHIVO TEXTO"); }
	    catch (IOException E) {System.out.println("Error Creacion Archivo");}*/
		Date Fecha;
//		CContador Contador = new CContador();
//		Contador.start();
		Fecha = new Date();
		System.out.println("FECHA: " + Fecha.getTime());
		System.out.println("FECHA: " + Fecha.toString());
		String Hora;
		Hora = Fecha.toString();
		Hora = Hora.substring(11, 19);
		System.out.println("FECHA: " + Hora);
//      Prueba de Paso a Minusculas
		String Ciudad = "BUENOS AIRES REP ARGENTINA LA REMIL";
		int x=0,y=1;
		String Caracter = "";
		String MiniCiudad = Ciudad;
		MiniCiudad = MiniCiudad.toLowerCase();
		Ciudad = "";
		while (y < MiniCiudad.length()){
			Caracter = MiniCiudad.substring(x,y);
			Caracter = Caracter.toUpperCase();
			Ciudad = Ciudad + Caracter;
			x = y;
			y = MiniCiudad.indexOf(" ");
			if (y<0) {
				y = MiniCiudad.length();
				Ciudad = Ciudad + MiniCiudad.substring(x);
			}
			else {
				Ciudad = Ciudad + MiniCiudad.substring(x,y) + " ";
				MiniCiudad = MiniCiudad.substring(y+1);
				x = 0;
				y = 1;
			}
		}
	System.out.println("CIUDAD: " + Ciudad);
	// Pruebo las horas
	/*java.sql.Time Reloj;
	Reloj = new java.sql.Time(h,m,s);
	java.sql.Timestamp RelojStamp;
	RelojStamp = new java.sql.Timestamp(h,m,s);*/
	}	
}

