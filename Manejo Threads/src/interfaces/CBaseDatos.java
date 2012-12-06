package interfaces;

import java.sql.*;
import java.util.LinkedList;
import java.io.*; 

public class CBaseDatos {
	
	public String url, CiudadOrigen, CiudadDestino, FechaIda, FechaRetorno, CiudadOrigenRet, CiudadDestinoRet; 
	public String qs, HorarioSalida, HorarioLlegada, Envio, HorarioSalidaRet, HorarioLlegadaRet;
	public Statement stmt;
	public ResultSet rs;
	public Connection connection;
	public int NroVuelo, Precio, NroVueloRet, PrecioRet;
	public int PedidoNro, Flag1, SMSSalida, TiempoContador;
	public CContador Contador;
	public CInicio Inicio;
	public CReserva Reserva;
	
	public CBaseDatos(CInicio Inicio) {
		CiudadOrigen = CiudadDestino = FechaIda = FechaRetorno = CiudadOrigenRet = CiudadDestinoRet = "";
		HorarioSalida = HorarioLlegada = Envio = HorarioSalidaRet = HorarioLlegadaRet = "";
		NroVuelo = Precio = NroVueloRet = PrecioRet = TiempoContador = 0;
		this.Inicio = Inicio;
		Contador = new CContador(this);
		Reserva  = new CReserva();
		PedidoNro =  Flag1 = SMSSalida = 1;
	};
	
	public void SetearDatos() {
		CiudadOrigen = CiudadDestino = FechaIda = FechaRetorno = CiudadOrigenRet = CiudadDestinoRet = "";
		HorarioSalida = HorarioLlegada = Envio = HorarioSalidaRet = HorarioLlegadaRet = "";
		NroVuelo = Precio = NroVueloRet = PrecioRet = 0;
	}
	
	public int activarbd(String User, String Pass) throws ClassNotFoundException,IOException  {
		int Resultado = 0;	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = "jdbc:mysql://localhost/maurixdb?user=" + User + "&password=" + Pass;        
			connection = DriverManager.getConnection(url);
		} catch (SQLException sqle) { 
			sqle.printStackTrace();
			while (sqle != null) {
				String logMessage = "\n SQL Error: "+
				sqle.getMessage() + "\n\t\t"+
				"Error code: "+sqle.getErrorCode() + "\n\t\t"+
				"SQLState: "+sqle.getSQLState()+"\n";
				System.out.println(logMessage);
				sqle = sqle.getNextException();
			}
			return 1;
		}	
		// Limpio las tablas "pedidos" - "smsinbox" - "smsoutbox" - "reservas"
		try {
			qs = "delete from smsinbox";
			stmt = connection.createStatement();
			Resultado+= stmt.executeUpdate(qs);
			qs = "delete from smsoutbox";
			stmt = connection.createStatement();
			Resultado+= stmt.executeUpdate(qs);		
//			qs = "delete from reservas";
//			stmt = connection.createStatement();
//			Resultado+= stmt.executeUpdate(qs);
//			qs = "delete from ventas";
//			stmt = connection.createStatement();
//			Resultado+= stmt.executeUpdate(qs);
			System.out.println("SMS Borrados de la BD: " + Resultado);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
			qs = "select NroPedido from pedidos order by NroPedido desc";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				PedidoNro = rs.getInt(1) + 1;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
			qs = "select NroPedido from reservas order by NroPedido desc";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				if (rs.getInt(1) >= PedidoNro) { PedidoNro = rs.getInt(1) + 1; }
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
			qs = "select NroPedido from ventas order by NroPedido desc";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				if (rs.getInt(1) >= PedidoNro) { PedidoNro = rs.getInt(1) + 1; }
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		Resultado = CargarReservasSinFinalizar();
		return Resultado;
	}
	
	public int desactivarbd() {
		try {
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public String PasoMinusculas(String Ciudad) {
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
		return Ciudad;
	}
	
	public void ModificoContador(int Tiempo) {
		TiempoContador = Tiempo;
	}
	
	public void ModificoFlagContador(int Valor) {
		Flag1 = Valor;
	}
	
//  Cuando Busco Vuelos Me Devuelve Los 3 Vuelos Más Baratos
	
	public String BuscoVuelo(CDatos Datos) {
		int filas = 0;
		int Resultado;
		SetearDatos();
		CiudadOrigen = Datos.ObtenerCiudadOrigen();
		CiudadDestino = Datos.ObtenerCiudadDestino();
		FechaIda = Datos.ObtenerFechaIda();
		try {
        	qs = "select * from vuelos where CodigoAeropuertoOrigen = ( " +
        				"select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadOrigen + "\" ) ) and CodigoAeropuertoDestino = ( " +        				
        				"select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadDestino + "\" ) ) and FechaSalida = \"" + FechaIda + "\" and AsientosLibres > 0 Order by Precio";
        	stmt = connection.createStatement();
            rs = stmt.executeQuery(qs);
            Envio = "AeroIguazu ";
            while (rs.next()) {
            	filas++;
            	System.out.println("BD : Busco Vuelo IDA - Numero de Fila En Curso: " + filas);
            	if (filas>0 && filas<4) {
            		if (filas == 1) {
                        CiudadOrigen = PasoMinusculas(CiudadOrigen);
                        CiudadDestino = PasoMinusculas(CiudadDestino);
            			Envio = Envio + "- " + CiudadOrigen + " a " + CiudadDestino + " - " + FechaIda;
            		}
            		NroVuelo = rs.getInt("NroVuelo");
            		HorarioSalida = rs.getString("HorarioSalida");
            		Precio = rs.getInt("Precio");
					qs = "update vuelos set AsientosLibres = AsientosLibres - 1 where NroVuelo = " + NroVuelo; 
					stmt = connection.createStatement();
					Resultado = stmt.executeUpdate(qs);
					if (Resultado == 1) { System.out.println("BD : Se Actualizo Tabla Vuelos - Saque 1 Asiento Al Vuelo " + NroVuelo); }
					Envio = Envio + " - Vuelo " + NroVuelo + ", " + HorarioSalida + ", $" + Precio;
            		if (filas == 1) {Datos.FijoVuelo1(NroVuelo);}
            		else if (filas == 2) {Datos.FijoVuelo2(NroVuelo);}
            		else {Datos.FijoVuelo3(NroVuelo);}
//            		HorarioSalida = HorarioSalida.substring(0,5) + " Hs";
            	}
            }
        	if (filas == 0) {
        		Envio = Envio + "Le Informa Que No Hay Vuelos Disponibles Para El Destino y La Fecha Deseados";       		
        	}
        } catch (SQLException sqle) {
			sqle.printStackTrace();
		}
        return Envio;	
	}
	
//	Muestra 1 Solo Vuelo De Ida y De Retorno, Siendo Los Dos Vuelos Los Más Baratos Disponibles
	
	public String BuscoVueloIdaRetorno(CDatos Datos) {
		int Resultado;
		SetearDatos();
		CiudadOrigen = Datos.ObtenerCiudadOrigen();
		CiudadDestino = Datos.ObtenerCiudadDestino();
		FechaIda = Datos.ObtenerFechaIda();
		try {
        	qs = "select * from vuelos where CodigoAeropuertoOrigen = ( " +
        				"select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadOrigen + "\" ) ) and CodigoAeropuertoDestino = ( " + 
        				"select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadDestino + "\" ) ) and FechaSalida = \"" + FechaIda + "\" and AsientosLibres > 0 Order by Precio";
        	stmt = connection.createStatement();
            rs = stmt.executeQuery(qs);
            if (rs.next()) {    
            	NroVuelo = rs.getInt("NroVuelo");
            	HorarioSalida = rs.getString("HorarioSalida");
//           	HorarioSalida = HorarioSalida.substring(0,5) + " Hs";
            	Precio = rs.getInt("Precio");
            	Envio = "AeroIguazu - Ida, Vuelo " + NroVuelo + ", " + PasoMinusculas(CiudadOrigen) + " a " + PasoMinusculas(CiudadDestino) + ", " + FechaIda + ", " + HorarioSalida + ", $" + Precio;
            	Datos.FijoVuelo1(NroVuelo);
				qs = "update vuelos set AsientosLibres = AsientosLibres - 1 where NroVuelo = " + NroVuelo; 
				stmt = connection.createStatement();
				Resultado = stmt.executeUpdate(qs);
				if (Resultado == 1) { System.out.println("BD : Se Actualizo Tabla Vuelos - Saque 1 Asiento Al Vuelo " + NroVuelo); }
            }
            else { 
        	Envio = "AeroIguazu - No hay Vuelo Disponible Para La Fecha De Ida";
            }
        } catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	    CiudadOrigenRet = CiudadDestino;
	    CiudadDestinoRet = CiudadOrigen;
	    FechaRetorno = Datos.ObtenerFechaRetorno();
		try {
        	qs = "select * from vuelos where CodigoAeropuertoOrigen = ( " +
        				"select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadOrigenRet + "\" ) ) and CodigoAeropuertoDestino = ( " + 
        				"select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadDestinoRet + "\" ) ) and FechaSalida = \"" + FechaRetorno + "\" and AsientosLibres > 0 Order by Precio";
        	stmt = connection.createStatement();
            rs = stmt.executeQuery(qs);
            if (rs.next()) {
            	NroVueloRet = rs.getInt("NroVuelo");
            	HorarioSalidaRet = rs.getString("HorarioSalida");
//            	HorarioSalidaRet = HorarioSalidaRet.substring(0,5) + " Hs";
            	PrecioRet = rs.getInt("Precio");
            	Envio = Envio + " - Retorno, Vuelo " + NroVueloRet + ", " + PasoMinusculas(CiudadOrigenRet) + " a " + PasoMinusculas(CiudadDestinoRet) + ", " + FechaRetorno + ", " + HorarioSalidaRet + ", $" + PrecioRet;
            	Datos.FijoVuelo2(NroVueloRet);
				qs = "update vuelos set AsientosLibres = AsientosLibres - 1 where NroVuelo = " + NroVueloRet; 
				stmt = connection.createStatement();
				Resultado = stmt.executeUpdate(qs);
				if (Resultado == 1) { System.out.println("BD : Se Actualizo Tabla Vuelos - Saque 1 Asiento Al Vuelo " + NroVueloRet); }
            }
            else {
            	Envio = Envio + " - Para El Retorno No Hay Vuelos Disponibles";
            }
        } catch (SQLException sqle) {
			sqle.printStackTrace();
		}
        if (Envio.contains("No hay Vuelo Disponible Para La Fecha De Ida") && Envio.contains("Para El Retorno No Hay Vuelos Disponibles")) {
        	Envio =  "AeroIguazu Le Informa Que No Hay Vuelos Disponibles Para El Destino Requerido En Las Fechas De Ida y Vuelta Deseados";
        }
        return Envio;				
	}
	
	public String BuscoEstadoVuelo(CDatos Datos) {
		SetearDatos();
		try {
			qs = "select * from vuelos where NroVuelo = " + Datos.ObtenerNroVuelo();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
            	HorarioSalida = rs.getString("HorarioSalida");
//				HorarioSalida = HorarioSalida.substring(0,5) + " Hs";
				Envio = "AeroIguazu - Vuelo " + Datos.ObtenerNroVuelo() + " - " + rs.getString("FechaSalida") + " - " + HorarioSalida + " - Estado Del Vuelo: " + rs.getString("Estado");  
			}
			else {
				Envio = "AeroIguazu - Vuelo " + Datos.ObtenerNroVuelo() + " - No Esta Programado En El Calendario De Vuelos";
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return Envio;
	}
	
	public void AlmacenoPedido(CDatos Datos) {
		SetearDatos();
		int Resultado;
		int SMSSaliente = 10;
		String Cliente = "";
		if (Datos.ObtenerTipoDato() == 2) { SMSSaliente = 11; }
		try { 																													// Verifico si el originador es cliente de la empresa
			qs = "select * from clientes where NroCelular = \"" + Datos.ObtenerNroCelOrigen() + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { Cliente = "S"; }
			else { Cliente = "N"; }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		if (Flag1 == 1) {																										// Arranco el contador que borra registros viejos
			Contador.FijarLapso(TiempoContador);
			new Thread(Contador).start();
			Flag1 = 0;
		}
		if (Datos.ObtenerTipoDato() == 1 || Datos.ObtenerTipoDato() == 2) {														// Almaceno pedido en Base de Datos
			try {
				qs = "insert into pedidos values (\"" + Datos.ObtenerNroCelOrigen() + "\"," + SMSSaliente + "," + Datos.ObtenerTipoDato() + ",\"" + Cliente +
				"\"," + PedidoNro + "," + Contador.ObtenerContador() + "," + Datos.ObtenerVuelo1() + "," + Datos.ObtenerVuelo2()+ "," + Datos.ObtenerVuelo3() + ")" ;
				stmt = connection.createStatement();
				Resultado = stmt.executeUpdate(qs);
				if (Resultado != 1 ) {
					System.out.println("BD : Resultado BD: " + Resultado);            	
					System.out.println("BD : Error Al Cargar Datos en BD - Tabla pedidos");
				}
				else { PedidoNro++; }
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
/*		if (Datos.ObtenerTipoDato() == 7) {
			try {
				qs = "insert into pedidos values (\"" + Datos.ObtenerNroCelOrigen() + "\",12," + Datos.ObtenerTipoDato() + ",\"" + Cliente +
				"\"," + PedidoNro + "," + Contador.ObtenerContador()+ ",0,0,0)" ;
				stmt = connection.createStatement();
				Resultado = stmt.executeUpdate(qs);
				if (Resultado != 1 ) {
					System.out.println("Resultado BD: " + Resultado);            	
					System.out.println("Error Al Cargar Datos en BD - Tabla pedidos");
				}
				else { PedidoNro++; }
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}*/	
	}
	
	public void ActualizoAsientoDeVuelo(int Vuelo) {
 		int Resultado;
 		try {
			qs = "update vuelos set AsientosLibres = AsientosLibres + 1 where NroVuelo = " + Vuelo;
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Actualizar Tabla en BD - Tabla \"vuelos\"");
            	System.out.println("BD : Nro De Vuelo: " + Vuelo);
            }
            else { System.out.println("BD : Se Actualizo Tabla Vuelos - Agregué 1 asiento al vuelo " + Vuelo); }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void ActualizarDatosVuelos(int CodigoReserva, int Flag) {					// Actualizo Los Asientos De Un Pedido o Una Reserva Borrada. Flag = 0 => Pedido, Flag = 1 => Reserva
		int Vuelo1,Vuelo2, Vuelo3;
 		Vuelo1 = Vuelo2 = Vuelo3 = 0;
 		if (Flag == 1) {
 			try {
 				qs = "select * from reservas where NroPedido = " + CodigoReserva;
 				stmt = connection.createStatement();
 				rs = stmt.executeQuery(qs);
 				if (rs.next()) { 
 					Vuelo1 = Integer.parseInt(rs.getString(3));
 					if (rs.getString(4).compareTo("-") != 0) { Vuelo2 = Integer.parseInt(rs.getString(4)); }  
 				}
 			} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			}
 		}
 		else {
 			try {
 				qs = "select * from pedidos where NroPedido = " + CodigoReserva;
 				stmt = connection.createStatement();
 				rs = stmt.executeQuery(qs);
 				if (rs.next()) { 
 					Vuelo1 = Integer.parseInt(rs.getString(7));
 					if (rs.getString(8).compareTo("0") != 0) { Vuelo2 = Integer.parseInt(rs.getString(8)); }
 					if (rs.getString(9).compareTo("0") != 0) { Vuelo3 = Integer.parseInt(rs.getString(9)); }
 				}
 			} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			}
 		}
 		ActualizoAsientoDeVuelo(Vuelo1);
		if (Vuelo2 != 0) { ActualizoAsientoDeVuelo(Vuelo2); }
		if (Vuelo3 != 0) { ActualizoAsientoDeVuelo(Vuelo3); }
	}
	
	//Funciones Que No Utilizo Todavia (MODIF)
	
	public void ActualizoPedido(int TipoAct) {
		SetearDatos();	
	}
	
	public int BorrarPedido(CDatos Datos)  {			// Borro El Pedido Después De Recibir Una Confirmación
		SetearDatos();
		int Resultado, NroPedido, Vuelo1, Vuelo2;
		Vuelo2 = 0;
		NroPedido = Datos.ObtenerCodigoReserva();
		Vuelo1 = Integer.parseInt(Datos.ObtenerReserva());
		if (!Datos.ObtenerReserva2().isEmpty()) { Vuelo2 = Integer.parseInt(Datos.ObtenerReserva2()); }
		System.out.println("BD : Vuelo1: " + Vuelo1 + "Vuelo2: " + Vuelo2);		// (MODIF)
		try {
			qs = "select * from pedidos where NroPedido = " + NroPedido; 
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
            if (rs.next()) {
            	for (int i=7;i<=9;i++) { 
            		if (Vuelo2 != 0) {
            			if (rs.getInt(i) != Vuelo1 && rs.getInt(i) != Vuelo2 && rs.getInt(i) != 0) { ActualizoAsientoDeVuelo(rs.getInt(i)); } 
            		}
            		else {
            			if (rs.getInt(i) != Vuelo1 && rs.getInt(i) != 0) { ActualizoAsientoDeVuelo(rs.getInt(i)); }
            		}
            	}
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
			qs = "delete from pedidos where NroPedido = " + NroPedido; 
			stmt = connection.createStatement();
			Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Borrar Datos en BD - Tabla \"pedidos\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public void BorrarPedidoPorTimeOut(long Tiempo) {
		int Resultado, NroPedido;
		ResultSet rs1;
		try {
			qs = "select NroPedido from pedidos where Hora < " + Tiempo; 
			stmt = connection.createStatement();
			rs1 = stmt.executeQuery(qs);
			while (rs1.next()) {
				NroPedido = rs1.getInt(1);
				ActualizarDatosVuelos(NroPedido,0);
			}
			qs = "delete from pedidos where Hora < " + Tiempo; 
			stmt = connection.createStatement();
			Resultado = stmt.executeUpdate(qs);
            if (Resultado != 0) { System.out.println("BD : Se Borraron " + Resultado + " Pedidos"); }
    		else { System.out.println("BD : No Se Borraron Pedidos De La Tabla \"pedidos\""); }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void AlmacenoSMSEntrantes(CIncomingMessage SMS,CDatos Datos) {
		SetearDatos();
		int Resultado;
		try {
			qs = "insert into smsinbox values (" + SMS.getId() + "," + SMS.getMemIndex() + ",\"" + SMS.getText() + "\",\"" + SMS.getOriginator() +
			"\",\"" + Datos.ObtenerTipoTicket() + "\"," + Datos.ObtenerTipoDato() + ")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla smsinbox");
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	// Almaceno SMS Que Son Enviados a Los Clientes En La Tabla "smsoutbox"
	
	public void AlmacenoSMSSalientes(CDatos Datos, String Texto, int TipoDato) {
		SetearDatos();
		int Resultado;
		try {
			qs = "insert into smsoutbox values (" + SMSSalida + ",\"" + Texto + "\"," + TipoDato + ",\"" + Datos.ObtenerNroCelOrigen() + "\"," + Datos.ObtenerIDSMS() + ")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla smsoutbox");
            }
            else {
            	SMSSalida++;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	// Actualizo Tablas Para Confirmar El Vuelo Requerido Por El Usuario
	
	public String ConfirmarVuelo(CDatos Datos) { 
		SetearDatos();
		int CodigoReserva, Col, VueloReserva, Flag1, Flag2;				// CodigoReserva es el NroPedido que se genero para el cliente y se almaceno en la tabla pedidos
		String Reserva1 ,Reserva2; 										// Los numeros de los vuelos a reservar
		Reserva1 = Reserva2 = "";
		Col = 7; Flag1 = Flag2 = 0;
		try {
			qs = "select * from pedidos where NroCelular = \"" + Datos.ObtenerNroCelOrigen() + "\" and (TipoUltimoSMSEnviado = 10 or TipoUltimoSMSEnviado = 11)";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
//			System.out.println(" Confirmar Vuelo: NroCelu = " + Datos.ObtenerNroCelOrigen() + " Datos.ObtenerReserva1: " + Datos.ObtenerReserva() + " Datos.ObtenerReserva2: " + Datos.ObtenerReserva2());
			if (rs.next()) {
// 				TipoConf = rs.getInt(2); Tipo de Confirmación, Si Son Para Dos Vuelos o Solo Para Uno (MODIF)
				CodigoReserva = rs.getInt(5); 
//				Verifico nro vuelo si concuerda con pedido.
				VueloReserva = Integer.parseInt(Datos.ObtenerReserva());
				while (Col < 10) {					
					if (VueloReserva == rs.getInt(Col)) {Flag1 = 1;}
					Col++;
				}
				if (Flag1 == 1) {
//					qs = "update vuelos set AsientosLibres = AsientosLibres - 1 where NroVuelo = " + Datos.ObtenerReserva();
//					stmt = connection.createStatement();
//					Resultado = stmt.executeUpdate(qs);
//					if (Resultado == 1) {
						Reserva1 = Datos.ObtenerReserva();
//					}
				}
//				Verifico nro vuelo si concuerda con pedido.
				if (!Datos.ObtenerReserva2().isEmpty()) {
					Col = 7;
					VueloReserva = Integer.parseInt(Datos.ObtenerReserva2());
					while (Col < 10) {					
						if (VueloReserva == rs.getInt(Col)) {Flag2 = 1;}
						Col++;
					}
					if (Flag2 == 1) {
//						qs = "update vuelos set AsientosLibres = AsientosLibres - 1 where NroVuelo = " + Datos.ObtenerReserva2(); 
//						stmt = connection.createStatement();
//						Resultado = stmt.executeUpdate(qs);
//						if (Resultado == 1) {
							Reserva2 = Datos.ObtenerReserva2();
//							System.out.println("Se Actualizo Tabla Vuelos");
//						}
					}
				}
				if (!Reserva1.isEmpty() && !Reserva2.isEmpty()) {
					Datos.FijoCodigoReserva(CodigoReserva);
					Envio = "Sus Vuelos Nro " + Reserva1 + " y " + Reserva2 + " Han Sido Confirmados - Su Codigo De Reserva Es " + CodigoReserva + " - Un Agente Se Comunicara Con Usted En Las Proximas Horas - Muchas Gracias";					
				}
				else {
					if ((!Reserva1.isEmpty() && Reserva2.isEmpty()) || (Reserva1.isEmpty() && !Reserva2.isEmpty())) {
						Datos.FijoCodigoReserva(CodigoReserva);
						if  (Reserva1.isEmpty()) {
							Reserva1 = Reserva2;
							Datos.FijoReserva(Datos.ObtenerReserva2());
						}
						Datos.FijoReserva2("");
						Envio = "Su Vuelo Nro " + Reserva1 + " Ha Sido Confirmado - Su Codigo De Reserva Es " + CodigoReserva + " - Un Agente Se Comunicara Con Usted En Las Proximas Horas - Muchas Gracias";
					}
					else {
						// System.out.println("No Se Actualizó La Tabla Vuelos - Se Enviará SMS De Error De Confirmación");
						// Aca tengo que ver que hago para volver atras las tablas (MODIF)
						if (Flag1 == 0 && Flag2 == 0 && !Datos.ObtenerReserva2().isEmpty()) { //El cero indica que el nro de vuelo es incorrecto
							Envio = "Sus Numeros De Vuelos Son Incorrectos, Por Favor, Envíe \"Reservar\" + \"Vuelo\" + \"NroVuelo1\" + \"y\" + \"NroVuelo2\" Para Confirmar Las Reservas";
						}
						else {
							Envio = "Su Numero De Vuelo Es Incorrecto, Por Favor, Envíe \"Reservar\" + \"Vuelo\" + \"NroVuelo\" Para Confirmar La Reserva";
						}
					}
				}		
			}
			else {
            	System.out.println("BD : No Se Encontró El Pedido Del Cliente");	
            	Envio = "Usted No Ha Realizado Una Busqueda De Vuelos. Envie Un SMS Para Realizar Una Busqueda De Vuelos Antes De Reservar. Muchas Gracias. AeroIguazu";
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return Envio;
	}
	
	public int BuscoTipoAyuda(CDatos Datos) {
		SetearDatos();
		int TipoSMS; 						// Ultimo SMS Enviado al Usuario	
		int TipoError = 1;					//TipoError 1 = Cuando No Mandó Ningún SMS Antes, TipoError 2 = Cuando Mandó un SMS De Búsqueda De Vuelo De Ida, TipoError 3 = Vuelo Ida Y Vuelta
		try {
			qs = "select * from pedidos where NroCelular = \"" + Datos.ObtenerNroCelOrigen() + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				TipoSMS = rs.getInt(2);		// Ultimo SMS Enviado Al Usuario
				if (TipoSMS == 10) { 		// Ver si no hay que agregar otras ayudas (MODIF)
					TipoError = 2;					
	            } else if (TipoSMS == 11) {
	            	TipoError = 3;
	            }
			}	
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return TipoError;
	}
	
	// Almaceno Lista De Contactos
	public int AlmacenoContacto(String NombreGrupo, String Nombre, String Cel) {
		SetearDatos();
		int Resultado;
		try {
			qs = "insert into contactos values (\"" + NombreGrupo + "\",\"" + Nombre + "\",\"" + Cel + "\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"contactos\"");
            	return 0;
            }
            else {
        		System.out.println("BD : Contacto " + Nombre + " Guardado");
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
        	return 0;
		}
		return 1;
	}

//  Creo Un Método Para Pasar Strings a Objetos y Poder Grabarlos En Una Cola De Objetos De Nombres De Grupos De Contactos
	
	public Object makeObj(final String item) { 
	return new Object() { 
		public String toString() { return item; } };
	}	
	
// Cargo Nombres De Grupos De Contactos En Cola De Objetos De Strings
	
	public LinkedList<Object> CargarTipoClientes() {
		SetearDatos();
		LinkedList<Object> TipoClientes = new LinkedList<Object>();
		try {
			qs = "select TipoCliente from clientes group by TipoCliente";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				TipoClientes.add(makeObj(rs.getString(1)));
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return TipoClientes;
	}

// Cargo Grupos De Clientes Por Ciudad De Residencia
	
	public LinkedList<Object> BuscoCiudadesRes() {
		ResultSet rs1;
		LinkedList<Object> Ciudades = new LinkedList<Object>();
		try {
			qs = "select CiudadResidencia from clientes group by CiudadResidencia";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				qs = "select Ciudad from ciudades where CodigoCiudad = \"" + rs.getString(1) + "\"";
				stmt = connection.createStatement();
				rs1 = stmt.executeQuery(qs);
            	if (rs1.next()) { Ciudades.add(makeObj(rs1.getString(1))); }
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return Ciudades;
	}
	
// Cargo Grupo De Contactos En Ventana Principal
	
	public int CargarContactos(String TipoCliente, String CiudadRes, javax.swing.DefaultListModel SModel, int Flag) {
		SetearDatos();
		int Filas = 0;	
		if (Flag == 0) {
			try {
				qs = "select * from clientes where TipoCliente = \"" + TipoCliente + "\" and RecibeSMSInfo = \"Si\"";
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
				while (rs.next()) {
					Filas += Inicio.IngresoContacto(rs.getString(1), rs.getString(5), SModel, TipoCliente, "-");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		else if (Flag == 1) {
			try {
				qs = "select CodigoCiudad from ciudades where Ciudad = \"" + CiudadRes + "\"";
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
            	if (rs.next()) {
            		qs = "select * from clientes where CiudadResidencia = \"" + rs.getString(1) + "\" and RecibeSMSInfo = \"Si\"";
            		stmt = connection.createStatement();
            		rs = stmt.executeQuery(qs);
            		while (rs.next()) {
            			Filas += Inicio.IngresoContacto(rs.getString(1), rs.getString(5), SModel, "-", CiudadRes);
            		}
            	}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		else {
			try {
				qs = "select CodigoCiudad from ciudades where Ciudad = \"" + CiudadRes + "\"";
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
            	if (rs.next()) {
            		qs = "select * from clientes where TipoCliente = \"" + TipoCliente + "\" and CiudadResidencia = \"" + rs.getString(1) + "\" and RecibeSMSInfo = \"Si\"";
            		stmt = connection.createStatement();
            		rs = stmt.executeQuery(qs);
            		while (rs.next()) {
            			Filas += Inicio.IngresoContacto(rs.getString(1), rs.getString(5), SModel, TipoCliente, CiudadRes);
            		}
            	}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return Filas;
	}
	
//  Almaceno Reserva De Vuelos Confirmada
	public void AlmacenoReserva(CDatos Datos) {
		SetearDatos();
		String Fecha = Inicio.CrearFecha(1,null);
		String Cliente = "";
		String Vuelo2 = "-";
//		Verifico si el originador es cliente de la empresa
		try {
			qs = "select * from clientes where NroCelular = \"" + Datos.ObtenerNroCelOrigen() + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { Cliente = "S"; }
			else { Cliente = "N"; }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		if (!(Datos.ObtenerReserva2().isEmpty())) { Vuelo2 = Datos.ObtenerReserva2(); }	
//		Almaceno Datos De La Reserva En La Base De Datos		
		int Resultado;
		try {
			qs = "insert into reservas values (\"" + Datos.ObtenerNroCelOrigen() + "\"," + Datos.ObtenerCodigoReserva() + ",\"" + Datos.ObtenerReserva() + "\",\"" + Vuelo2
			+ "\",\"" + Cliente +  "\",\"" + Fecha +"\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"reservas\"");
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		try {
			qs = "update pedidos set TipoUltimoSMSEnviado = 14 where NroCelular = \"" + Datos.ObtenerNroCelOrigen() + "\" and (TipoUltimoSMSEnviado = 10 or TipoUltimoSMSEnviado = 11)";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Actualizar Tabla en BD - Tabla \"pedidos\"");
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
//  Busco Reserva En BD
	
	public CReserva BuscoReserva(int CodigoReserva) {
		SetearDatos();
		Reserva.Limpiar();
//		Cargo Valor del resultado de la busqueda de la reserva en el objeto CReserva
		try {
			qs = "select * from reservas where NroPedido = " + CodigoReserva;
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
				Reserva.FijoNroCelular(rs.getString(1));
				Reserva.FijoVuelo1(rs.getString(3));
				Reserva.FijoVuelo2(rs.getString(4));
				Reserva.FijoCliente(rs.getString(5));
				Reserva.FijoNroPedido(rs.getInt(2));
				Reserva.FijoFechaCreacionReserva(rs.getString(6));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		try {
			qs = "select * from vuelos where NroVuelo = " + Reserva.ObtenerVuelo1();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				Reserva.FijoHorarioSalidaVuelo1(rs.getString(2));
				Reserva.FijoHorarioLlegadaVuelo1(rs.getString(3));
				Reserva.FijoFechaSalidaVuelo1(rs.getString(8));
				Reserva.FijoFechaLlegadaVuelo1(rs.getString(9));
				Reserva.FijoAeropuertoOrigenVuelo1(rs.getString(6));
				Reserva.FijoAeropuertoDestinoVuelo1(rs.getString(7));
				Reserva.FijoAvionVuelo1(rs.getString(5));
				Reserva.FijoAsientosLibresVuelo1(rs.getInt(4));
				Reserva.FijoEstadoVuelo1(rs.getString(11));
				Reserva.FijoPrecioVuelo1(rs.getInt(10));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		if (!(Reserva.ObtenerVuelo2().compareTo("-") == 0)) {
			try {
				qs = "select * from vuelos where NroVuelo = " + Reserva.ObtenerVuelo2();
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
				if (rs.next()) {
					Reserva.FijoHorarioSalidaVuelo2(rs.getString(2));
					Reserva.FijoHorarioLlegadaVuelo2(rs.getString(3));
					Reserva.FijoFechaSalidaVuelo2(rs.getString(8));
					Reserva.FijoFechaLlegadaVuelo2(rs.getString(9));
					Reserva.FijoAeropuertoOrigenVuelo2(rs.getString(6));
					Reserva.FijoAeropuertoDestinoVuelo2(rs.getString(7));
					Reserva.FijoAvionVuelo2(rs.getString(5));
					Reserva.FijoAsientosLibresVuelo2(rs.getInt(4));
					Reserva.FijoEstadoVuelo2(rs.getString(11));
					Reserva.FijoPrecioVuelo2(rs.getInt(10));
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	return Reserva;
	}

  	public int AgregoAeropuerto(String Nombre, int Pistas, String Ciudad, String Provincia, String CodigoAeropuerto) {
  		int Resultado;
  		String CodigoCiudad = "";
		try {
			qs = "select CodigoCiudad from ciudades where Ciudad = \"" + Ciudad + "\" and Provincia = \"" + Provincia + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
				CodigoCiudad = rs.getString(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
			qs = "insert into aeropuertos values (\"" + Nombre + "\"," + Pistas + ",\"" + CodigoCiudad + "\",\"" + CodigoAeropuerto + "\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"aeropuertos\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
           	return 1;
		}
		try {
			qs = "update ciudades set CantidadAeropuertos = 1 where CodigoCiudad = \"" + CodigoCiudad + "\"";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Actualizar Tabla en BD - Tabla \"ciudades\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
        	return 1;
		}
		return 0;
  	}
  	
  	public int AgregoAvion(String TipoAvion, int Capacidad, String CodigoAvion) {
  		int Resultado;
		try {
			qs = "insert into aviones values (\"" + TipoAvion + "\"," + Capacidad + ",\"" + CodigoAvion + "\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"aviones\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
        	return 1;
		}
		return 0;
  	}
  	
  	public int AgregoCliente(String Nombre, String Apellido, String NroCelular, int NroDoc, String TipoDoc, String FechaNac, String TipoCliente, String CiudadRes, String RecibeInfo) {
  		int Resultado;
		try {
			qs = "select CodigoCiudad from ciudades where Ciudad = \"" + CiudadRes + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
				CiudadRes = rs.getString(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
        	return 1;
		}
		try {
			qs = "insert into clientes values (\"" + Nombre + " " + Apellido + "\",\"" + TipoDoc + "\",\"" + FechaNac + "\"," + NroDoc + ",\"+" + NroCelular + "\"," +
					"\"" + TipoCliente + "\",\"" + CiudadRes + "\",\"" + RecibeInfo + "\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"clientes\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
        	return 1;
		}
		return 0;
  	}
  	
  	public int AgregoCiudad(String Nombre, String ZonaHoraria, int CantAero, String Provincia, String CodigoCiudad) {
  		int Resultado;
		try {
			qs = "insert into ciudades values (\"" + Nombre + "\",\"" + ZonaHoraria + "\"," + CantAero + ",\"" + Provincia + "\",\"" + CodigoCiudad + "\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"ciudades\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
        	return 1;
		}
		return 0;
  	}
	
  	public int AgregoVuelo(String Nro, String CiudadOrigen, String CiudadDestino, String CodigoAvion, String HoraSalida, String HoraLlegada, 
  							String FechaSalida, String FechaLlegada, String Precio, String Asientos) {
  		
  		int NroVuelo = Integer.parseInt(Nro);
  		int PrecioFinal = Integer.parseInt(Precio);
  		int AsientosLibres = Integer.parseInt(Asientos);
  		int Resultado;
		try {
			qs = "select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadOrigen + "\" )";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
				CiudadOrigen = rs.getString(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
			qs = "select CodigoAeropuerto from aeropuertos where CodigoCiudad = ( select CodigoCiudad from ciudades where Ciudad = \"" + CiudadDestino + "\" )";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
				CiudadDestino = rs.getString(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		try {
//			System.out.println("NroVuelo: " + NroVuelo + " HoraSalida: " + HoraSalida + " HoraLlegada: " + HoraLlegada + " AsientosLibres: " + AsientosLibres +
//					" CodigoAvion: " + CodigoAvion +  " CiudadOrigen: " + CiudadOrigen + " CiudadDestino: " + CiudadDestino + " FechaSalida: " + FechaSalida +
//					" FechaLlegada: " + FechaLlegada + " PrecioFinal:  " + PrecioFinal);
			qs = "insert into vuelos values (" + NroVuelo + ",\"" + HoraSalida + "\",\"" + HoraLlegada + "\"," + AsientosLibres + ",\"" + CodigoAvion +  "\",\""
											   + CiudadOrigen + "\",\"" + CiudadDestino + "\",\"" + FechaSalida +  "\",\"" + FechaLlegada + "\"," + PrecioFinal + ",\"En Horario\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"vuelos\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
  	}

 	public int AgregoVenta(String NombreCliente, String NroCel, int NroReserva, int Precio, String Pago) {
  		int Resultado;
  		String Fecha = Inicio.CrearFecha(1, null);
  		try {
			qs = "insert into ventas values (\"" + NombreCliente + "\",\"" + NroCel + "\"," + NroReserva + "," + Precio + ",\"" + Pago + "\",\"" + Fecha + "\")";
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Cargar Datos en BD - Tabla \"ventas\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0; 		
  	}
 	
 	public String BuscoNombreCliente(String Cel) {
		String NombreCliente;
		NombreCliente = "";
 		try {
			qs = "select Nombre from clientes where NroCelular = \"" + Cel + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { NombreCliente = rs.getString(1); }
			else { NombreCliente = "Inexistente"; }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return NombreCliente;
 	}
 	
 	public String BuscoCelReserva(int CodigoReserva) {
 		String NroCelular = "";
 		try {
			qs = "select NroCelular from reservas where NroPedido = " + CodigoReserva;
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { NroCelular = rs.getString(1); }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return NroCelular;
 	}
 	
 	public int BuscoPrecioFinal(int CodigoReserva) {
 		int PrecioFinal, Vuelo1,Vuelo2;
 		PrecioFinal = Vuelo1 = Vuelo2 = 0;
 		try {
			qs = "select * from reservas where NroPedido = " + CodigoReserva;
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { 
				Vuelo1 = Integer.parseInt(rs.getString(3));
				if (rs.getString(4).compareTo("-") != 0) { Vuelo2 = Integer.parseInt(rs.getString(4)); }  
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
 		try {
			qs = "select Precio from vuelos where NroVuelo = " + Vuelo1;
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) { PrecioFinal = rs.getInt(1); }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		if (Vuelo2 != 0) {
			try {
				qs = "select Precio from vuelos where NroVuelo = " + Vuelo2;
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
				if (rs.next()) { PrecioFinal += rs.getInt(1); }
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		return PrecioFinal;
 	}
 	
 	public boolean EsCliente(int CodigoReserva) {
 		String SiNo = "";
 		boolean Cliente = false;
		try {
			qs = "select Cliente from reservas where NroPedido = " + CodigoReserva;
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				SiNo = rs.getString(1);
				if (SiNo.compareTo("S") == 0) { Cliente = true; }
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
 		return Cliente;
 	}
 	
 	public int EliminoReserva(int CodigoReserva) {
 		int Resultado;
  		try {
			qs = "delete from reservas where NroPedido = " + CodigoReserva;
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Borrar Datos en BD - Tabla \"reservas\"");
            	return 1;
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
 	}
 	
	public void AltaClienteReserva(String Cel) {
		int Resultado;
		try {
			qs = "update reservas set Cliente = \"S\" where NroCelular = " + Cel;
        	stmt = connection.createStatement();
            Resultado = stmt.executeUpdate(qs);
            if (Resultado != 1 ) {
            	System.out.println("BD : Resultado BD: " + Resultado);            	
            	System.out.println("BD : Error Al Actualizar Tabla en BD - Tabla \"reservas\"");
            }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
//  Busco Ciudades Para Colocar En Ventanas Extras
  	
	public LinkedList<Object> BuscoCiudades() {
		LinkedList<Object> Ciudades = new LinkedList<Object>();
		try {
			qs = "select Ciudad from ciudades";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				Ciudades.add(makeObj(rs.getString(1)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Ciudades;
	}
	
	public String BuscoCiudadPorAeropuerto(String CodigoAeropuerto) {
		String Ciudad = "";
		String CodigoCiudad = "";
		try {
			qs = "select CodigoCiudad from aeropuertos where CodigoAeropuerto = \"" + CodigoAeropuerto + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				CodigoCiudad = rs.getString(1);
				qs = "select Ciudad from ciudades where CodigoCiudad = \"" + CodigoCiudad + "\"";
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
				if (rs.next()) { Ciudad = rs.getString(1); }
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Ciudad;
	}
	
	public LinkedList<Object> BuscoCiudadesConAeropuertos() {
		LinkedList<Object> Ciudades = new LinkedList<Object>();
		try {
			qs = "select Ciudad from ciudades where CantidadAeropuertos > 0";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				Ciudades.add(makeObj(rs.getString(1)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Ciudades;
	}
	
	public LinkedList<Object> BuscoCiudadesSinAeropuertos() {
		LinkedList<Object> Ciudades = new LinkedList<Object>();
		try {
			qs = "select Ciudad from ciudades where CantidadAeropuertos = 0";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				Ciudades.add(makeObj(rs.getString(1)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Ciudades;
	}
	
//  Busco Provincia Para Colocar En Ventanas Extras
  	
	public String BuscoProvincia(String NombreCiudad) {
		String Provincia = "";
		try {
			qs = "select Provincia from ciudades where Ciudad = \"" + NombreCiudad + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				Provincia = rs.getString(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return Provincia;
	}	
	
	
//  Busco Aviones Para Colocar En Ventanas Extras
  	
	public LinkedList<Object> BuscoAviones() {
		LinkedList<Object> Aviones = new LinkedList<Object>();
		try {
			qs = "select CodigoAvion from aviones";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				Aviones.add(makeObj(rs.getString(1)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Aviones;
	}
	
//  Busco Capacidad Del Avion Para Colocar En Ventana De Vuelo
  	
	public int BuscoCapacidadAvion(String CodigoAvion) {
		int Capacidad = 0;
		try {
			qs = "select Capacidad from aviones where CodigoAvion = \"" + CodigoAvion + "\"";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				Capacidad = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Capacidad;
	}

// Busco Números De Vuelos Para Modificar El Estado De Los Mismos	
	
	public LinkedList<Object> BuscoVueloModificar() {
		LinkedList<Object> Vuelos = new LinkedList<Object>();
		try {
			qs = "select NroVuelo from vuelos";
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			while (rs.next()) {
				Vuelos.add(makeObj(Integer.toString(rs.getInt(1))));				
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	return Vuelos;
	}
	
	public String BuscoEstadoVuelo(int Nro) {
		String Estado = "";
		try {
			qs = "select Estado from vuelos where NroVuelo = " + Nro;
			stmt = connection.createStatement();
			rs = stmt.executeQuery(qs);
			if (rs.next()) {
				Estado = rs.getString(1);				
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return Estado;
	}
	
	public int ModificoEstadoVuelo(int Nro, String Estado) {
		int Resultado;
		try {
			qs = "update vuelos set Estado = \"" + Estado + "\" where NroVuelo = " + Nro;
			stmt = connection.createStatement();
			Resultado = stmt.executeUpdate(qs);
			if (Resultado != 1 ) {
				System.out.println("BD : Resultado BD: " + Resultado);            	
				System.out.println("BD : Error Al Actualizar Tabla en BD - Tabla \"vuelos\"");
				return 1;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public int CargarReservasSinFinalizar() {
		String Cel, Agrego;
		int CodigoReserva;
		if (Inicio.ListaReservas.isEmpty()) {
			try {
				qs = "select * from reservas";
				stmt = connection.createStatement();
				rs = stmt.executeQuery(qs);
				while (rs.next()) { 
					Cel = rs.getString(1);
					CodigoReserva = rs.getInt(2);
					Agrego = "Cel: " + Cel + " - Reserva Nro: " + CodigoReserva;
					Inicio.ModeloLista2.addElement(Agrego);
					Inicio.ListaReservas.add(CodigoReserva);
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				return 1;
			}
		}
		return 0;
	}
	
/*	public int buscodato(String nombre) {
        
		int resultado = 0;
		
		try {
        	qs = "select edad from usuarios where nombre = \"" + nombre + "\"";
        	stmt = connection.createStatement();
            rs = stmt.executeQuery(qs);
            rs.next();
            resultado = rs.getInt("edad");
        } catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	    return resultado;
	} */
}
