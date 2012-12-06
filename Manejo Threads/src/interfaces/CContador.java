package interfaces;
import java.util.Date;
import java.sql.*;

public class CContador extends Thread {

	//String qs;
	//public Statement stmt;
	//public ResultSet rs;
	public Connection connection;
	long ValorMilis;
	long Lapso;
	public CBaseDatos BD;
	String HoraInicioClock;
	boolean Conexion;
	//String Reloj;
	
	public CContador(CBaseDatos BaseDatos) {
//		Lapso = 60000;
		BD = BaseDatos;
		Conexion = true;
	}

	public long ObtenerContador() { 
		long Valor;
		Date Hora;
		Hora = new Date();		
		Valor = Hora.getTime(); 		// Minutos Del Contador En Milisegundos
		return Valor;
	}
	
	public String ObtenerHora() {
		Date Hora;
		Hora = new Date();
		String Tiempo;
		Tiempo = Hora.toString();
		Tiempo = Tiempo.substring(11, 19);
		return Tiempo;
	}
	
	public void FijarLapso(int Tiempo) {
		Lapso = Tiempo*60000;
	}
	
	public void Desconectar() {
		Conexion = false;
		BD.ModificoFlagContador(1);
	}

	public void run() {
		HoraInicioClock = ObtenerHora();
		System.out.println("Inicio Contador. Lapso: " + Lapso);
		while (Conexion) {
			try { sleep(Lapso); } catch (Exception e) {}
			if (Conexion) {
				System.out.println("HORA INICIO CLOCK: " + HoraInicioClock);
				ValorMilis = ObtenerContador();
				System.out.println("HORA ACTUAL VALOR: " + ValorMilis);
				ValorMilis = ValorMilis - Lapso;
				//Borro Todos los registros de 1 hora atras (en el ejemplo 60 seg)
				System.out.println("AHORA BORRO REGISTROS DE " + Lapso/60000 + " Minutos ANTES: VALORMILIS: " + ValorMilis);
				BD.BorrarPedidoPorTimeOut(ValorMilis);
			}
		}
	}
}
