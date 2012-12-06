package interfaces;

public class CParseo {
	
	String Texto;
	String MiniTexto, MiniTexto2, TipoTicket, CiudadOrigen, CiudadDestino, FechaIda, FechaRetorno, Reserva, Reserva2, ReservaFull, EstadoVuelo, EstadoFull;
	int Index1, Index2;
	int flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8, flag9;
	public static String FechaDia = "", FechaMes = "", FechaAnio = "";
	
	public CParseo() {
		Index1 = 0;
		Index2 = 1;		
		flag1 = flag2 = flag3 = flag4 = flag5 = flag6 = flag7 = flag8 = flag9 = 0;
		MiniTexto = MiniTexto2 = TipoTicket = CiudadOrigen = CiudadDestino = FechaIda = FechaRetorno = Reserva = Reserva2 = ReservaFull = EstadoVuelo = EstadoFull = "";
	};
	
	public void LimpioParseo() {
		Index1 = 0;
		Index2 = 1;		
		flag1 = flag2 = flag3 = flag4 = flag5 = flag6 = flag7 = flag8 = flag9 = 0;
		MiniTexto = MiniTexto2 = TipoTicket = CiudadOrigen = CiudadDestino = FechaIda = FechaRetorno = Reserva = Reserva2 = ReservaFull = EstadoVuelo = EstadoFull = "";
	}

	public String ParsearFecha(String Fecha, int tipofecha) {
		
		int Index1 = 0, Index2 = 1, Index3 = 0;
		String MiniTexto = "", MiniTexto2 = "";
		int flag1 = 1, flag2 = 0, flag3 = 0;
		String Simbolo = "", Anio = "2007";
		
		if (Fecha.contains("/") || Fecha.contains("-") || Fecha.contains("\\") || Fecha.contains("_")) {
			
			if (Fecha.contains("200")) {
				Index3 = Fecha.lastIndexOf("200") - 1;
				Simbolo = Fecha.substring(Index3, Index3+1);
				Fecha = Fecha.substring(0, Index3+5);
				//System.out.println("Simbolo : " + Simbolo);
			}
			else {
				Index3 = Fecha.lastIndexOf("07");
				if (Index3 == -1 || Index3 <= 4) {
					Index3 = Fecha.lastIndexOf("08");
					Anio = "2008";
				}
				Index3--;
				Simbolo = Fecha.substring(Index3, Index3+1);
				Fecha = Fecha.substring(0, Index3+1);
				Fecha = Fecha.concat(Anio);
//				System.out.println("Simbolo : " + Simbolo);
			}

			Fecha = Fecha.concat(Simbolo);
			
			for (int i = 1 ; i<=3 ; i++) {
				Index2 = Fecha.indexOf(Simbolo,Index1);
				//System.out.println("Index2 : " + Index2 + "FechaIDA : " + FechaIda);
				MiniTexto = MiniTexto + "#" + Fecha.substring(Index1, Index2);
				MiniTexto2 = Fecha.substring(Index1, Index2);
				//Comienzo Parseo
				if (i == 1) {FechaDia = MiniTexto2;}
				else if (i == 2) {FechaMes = MiniTexto2;}
				else if (i == 3) {FechaAnio = MiniTexto2;}
				//Fin Parseo
				Index1 = Index2 + 1;
			}
		}
		else {
			while (Index1 < Fecha.length()) {
				Index2 = Fecha.indexOf(" ",Index1);
				if (Index2 > 0) {
					MiniTexto = MiniTexto + "#" + Fecha.substring(Index1, Index2);
					MiniTexto2 = Fecha.substring(Index1, Index2);
					//Comienzo Parseo
					if (flag1 == 1) {FechaDia = MiniTexto2; flag1 = 0; flag2 = 1;}
					else if (flag2 == 1 && MiniTexto2.compareTo("DE") != 0) {FechaMes = MiniTexto2; flag2 = 0; flag3 = 1;}
					else if (flag3 == 1 && MiniTexto2.compareTo("DEL") != 0) {FechaAnio = MiniTexto2; flag3 = 0;}
					//Fin Parseo
					Index1 = Index2 + 1;
				}
				else {
					Index1 = Fecha.length();
				}
			}
		}
/*		if (tipofecha == 0) {
			System.out.println("Mensaje Parseado Ida Dia : " + FechaDia);
			System.out.println("Mensaje Parseado Ida Mes : " + FechaMes);
			System.out.println("Mensaje Parseado Ida Año : " + FechaAnio);
		}
		if (tipofecha == 1) {
			System.out.println("Mensaje Parseado Vuelta Dia : " + FechaDia);
			System.out.println("Mensaje Parseado Vuelta Mes : " + FechaMes);
			System.out.println("Mensaje Parseado Vuelta Año : " + FechaAnio);
			}
*///	Fecha = Fecha.substring(0, Fecha.length()-1);
		Fecha = "";
		if (FechaDia.length()<2) { Fecha = "0" + FechaDia; }
		else { Fecha = FechaDia; }
		if (FechaMes.length()<2) { Fecha = Fecha + "/0" + FechaMes + "/"; }
		else { Fecha = Fecha + "/" + FechaMes + "/"; }
		Fecha = Fecha.concat(Anio);
		return Fecha;
	}
//  ParsearTipoTicket lo uso para eliminar caracteres sobrantes en palabras claves como ayuda o iguazuair	
	public String ParsearTipoTicket(String TipoTicket) {
		String TipoTicketFinal = "";
		for (int i=0 ; i<TipoTicket.length() ; i++) {
			char Caracter = TipoTicket.charAt(i);
			if (Caracter != '/' && Caracter != '*' && Caracter != '-'
			 && Caracter != '\\'&& Caracter != '+' && Caracter != ':'
			 && Caracter != ';' && Caracter != '_' && Caracter != '?'
			 && Caracter != '!' ) {
				TipoTicketFinal = TipoTicketFinal + Caracter;
			}
		}
		return TipoTicketFinal;
	}

	public CDatos Parsear(CIncomingMessage msg, CDatos Datos) {

		Datos.FijoNroCelOrigen(msg.getOriginator());
		Datos.FijoIDSMS(msg.getId());
		Texto = msg.getText().trim();
//		Texto = "Vuelo de Posadas a Buenos Aires fecha de ida 10/12/2007 fecha de retorno 10/1/07- Maurix";
		Texto = Texto + " ";
		Texto = Texto.toUpperCase();
		System.out.println("Parseo : Mensaje SMS: " + Texto);
		LimpioParseo();
		
		while (Index1 < Texto.length()) {
			Index2 = Texto.indexOf(" ",Index1);
			//System.out.println("Index1: " + Index1);
			//System.out.println("Index2: " + Index2);
			if (Index2 > 0 && flag5 == 0) {
				MiniTexto = MiniTexto + "#" + Texto.substring(Index1, Index2);
				MiniTexto2 = Texto.substring(Index1, Index2);
				//Comienzo Parseo
				if (MiniTexto2.compareTo("VUELO") == 0 || MiniTexto2.compareTo("VUELOS") == 0 || MiniTexto2.contains("AYUDA") || MiniTexto2.contains("IGUAZUAIR") ||
					MiniTexto2.contains("INFOVUELOS")) {
					if (flag6 == 0 && flag7 == 0 && flag8 == 0) {TipoTicket = ParsearTipoTicket(MiniTexto2);}
					else if (flag6 == 0 && flag7 == 0 && flag8 == 1) {TipoTicket = "OTROS VUELOS";}
				}
				else if (MiniTexto2.compareTo("COLECTIVO") == 0) {TipoTicket = ParsearTipoTicket(MiniTexto2);}
				else if (MiniTexto2.compareTo("RESERVAR") == 0) {TipoTicket = ParsearTipoTicket(MiniTexto2); flag6 = 1;}
				else if (MiniTexto2.compareTo("ESTADO") == 0) {TipoTicket = ParsearTipoTicket(MiniTexto2); flag7 = 1;}
				else if (MiniTexto2.compareTo("OTROS") == 0) {TipoTicket = ParsearTipoTicket(MiniTexto2); flag8 = 1;}
				else if (MiniTexto2.compareTo("DESDE") == 0 || MiniTexto2.compareTo("ORIGEN") == 0 || (MiniTexto2.compareTo("DE") == 0 && flag3 == 0)) {flag1 = 1;}
				else if (MiniTexto2.compareTo("A") == 0 || MiniTexto2.compareTo("DESTINO") == 0 || MiniTexto2.compareTo("HACIA") == 0) {flag2 = 1; flag1 = 0;}
				else if (MiniTexto2.compareTo("EL") == 0 || MiniTexto2.compareTo("FECHA") == 0 || (MiniTexto2.compareTo("DE") == 0 && flag3 == 1) || 
						(MiniTexto2.compareTo("IDA") == 0) || (MiniTexto2.compareTo("Y") == 0 && flag6 == 0)) {flag2 = 0; flag3 = 1;}
				else if (MiniTexto2.compareTo("RETORNO") == 0) {flag4 = 1; flag3 = 0;}
				else if (flag1 == 1) {CiudadOrigen = CiudadOrigen + MiniTexto2 + " ";}
				else if (flag2 == 1) {CiudadDestino = CiudadDestino + MiniTexto2 + " ";}
				else if (flag3 == 1) {FechaIda = FechaIda + MiniTexto2 + " "; flag3 = 0;}
				else if (flag4 == 1) {FechaRetorno = FechaRetorno + MiniTexto2 + " "; flag4 = 0; flag5 = 1;}
				else if (flag6 == 1) {if (flag9 == 0) {Reserva = MiniTexto2; flag9 = 1;} else if (flag9 == 1 && !(MiniTexto2.compareTo("Y") == 0)) {Reserva2 = MiniTexto2; flag6 = 0; flag9 = 0;}}
				else if (flag7 == 1) {EstadoVuelo = MiniTexto2; flag7 = 0;}
				//Fin Parseo
				Index1 = Index2 + 1;
			}
			else {
				Index1 = Texto.length();
			}
		}
		
		TipoTicket = TipoTicket.trim();
		// Borrar (MODIF)
		System.out.println("Parseo : Tipo De SMS : " + TipoTicket);
		// Borrar (MODIF)
		// Fijo El Tipo De Dato Del SMS Recibido
		if (TipoTicket.compareTo("VUELO") == 0 || TipoTicket.compareTo("VUELOS") == 0) {
			if (!CiudadOrigen.isEmpty() && !CiudadDestino.isEmpty() && !FechaIda.isEmpty()) {
				if (FechaRetorno.isEmpty()) {Datos.FijoTipoDato(1);}
				else {Datos.FijoTipoDato(2);}
			}
			else {Datos.FijoTipoDato(8);} 
		}
		else if (TipoTicket.compareTo("IGUAZUAIR") == 0) { Datos.FijoTipoDato(4); }
		else if (TipoTicket.compareTo("AYUDA") == 0) { Datos.FijoTipoDato(9); }
		else if (TipoTicket.compareTo("INFOVUELOS") == 0) { Datos.FijoTipoDato(10); }
		else if (TipoTicket.compareTo("RESERVAR") == 0) {
			// Verifico Que El SMS De Confirmación Tenga Nros De Vuelos Validos
			for (int i=0 ; i<Reserva.length() ; i++) {
				char Caracter = Reserva.charAt(i);
				if (Caracter == '1' || Caracter == '2' || Caracter == '3'
				 || Caracter == '4' || Caracter == '5' || Caracter == '6'
				 || Caracter == '7' || Caracter == '8' || Caracter == '9'
				 || Caracter == '0' ) {
					ReservaFull = ReservaFull + Caracter;
				}
			}
			Reserva = ReservaFull;
			ReservaFull = "";
			Datos.FijoReserva(Reserva);
			System.out.println("Parseo : Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Parseo : Mensaje Parseado TipoTicket : " + Datos.ObtenerTipoTicket());
			System.out.println("Parseo : Mensaje Parseado Reserva 1 : " + Datos.ObtenerReserva());
			// Verifico si hay otra reserva mas realizada
			if (!Reserva2.isEmpty()) {
				for (int i=0 ; i<Reserva2.length() ; i++) {
					char Caracter = Reserva2.charAt(i);
					if (Caracter == '1' || Caracter == '2' || Caracter == '3'
					 || Caracter == '4' || Caracter == '5' || Caracter == '6'
					 || Caracter == '7' || Caracter == '8' || Caracter == '9'
					 || Caracter == '0' ) {
						ReservaFull = ReservaFull + Caracter;
					}
				}
				Reserva2 = ReservaFull;
				Datos.FijoReserva2(Reserva2);
				System.out.println("Parseo : Mensaje Parseado Reserva 2 : " + Datos.ObtenerReserva2());
			}
			if (!Reserva.isEmpty()) {Datos.FijoTipoDato(5);}
			else {Datos.FijoTipoDato(9);}
		}
		else if (TipoTicket.compareTo("ESTADO") == 0) {
			if (!EstadoVuelo.isEmpty()) {Datos.FijoTipoDato(7);}
			else {Datos.FijoTipoDato(9);}
		}
		else if (TipoTicket.compareTo("OTROS VUELOS") == 0) {Datos.FijoTipoDato(6);}
		else {Datos.FijoTipoDato(3);}
		
		// Fin FijoTipoDato
		
		if (Datos.ObtenerTipoDato() == 1 || Datos.ObtenerTipoDato() == 2) {
			Datos.FijoTipoTicket(TipoTicket);
			CiudadOrigen = CiudadOrigen.trim();
			Datos.FijoCiudadOrigen(CiudadOrigen);
			CiudadDestino = CiudadDestino.trim();
			Datos.FijoCiudadDestino(CiudadDestino);
			FechaIda = FechaIda.trim();
			FechaIda = ParsearFecha(FechaIda,0);
			Datos.FijoFechaIda(FechaIda);
			System.out.println("Parseo : Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Parseo : Mensaje Parseado TipoTicket : " + Datos.ObtenerTipoTicket());
			System.out.println("Parseo : Mensaje Parseado CiudadOrigen : " + Datos.ObtenerCiudadOrigen());
			System.out.println("Parseo : Mensaje Parseado CiudadDestino : " + Datos.ObtenerCiudadDestino());
			System.out.println("Parseo : Mensaje Parseado FechaIda : " + Datos.ObtenerFechaIda());
		}
		else if (Datos.ObtenerTipoDato() == 4) {
			Datos.FijoTipoTicket(TipoTicket);
			System.out.println("Parseo : Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Parseo : Mensaje Parseado TipoTicket : " + Datos.ObtenerTipoTicket());
		}
		else if (Datos.ObtenerTipoDato() == 5) {
			Datos.FijoTipoTicket(TipoTicket);
		}
		else if (Datos.ObtenerTipoDato() == 7) {
			Datos.FijoTipoTicket(TipoTicket);
			for (int i=0 ; i<EstadoVuelo.length() ; i++) {
				char Caracter = EstadoVuelo.charAt(i);
				if (Caracter == '1' || Caracter == '2' || Caracter == '3'
				 || Caracter == '4' || Caracter == '5' || Caracter == '6'
				 || Caracter == '7' || Caracter == '8' || Caracter == '9'
				 || Caracter == '0' ) {
					EstadoFull = EstadoFull + Caracter;
				}
			}
			EstadoVuelo = EstadoFull;
			Datos.FijoNroVuelo(EstadoVuelo);
			System.out.println("Parseo : Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Parseo : Mensaje Parseado TipoTicket : " + Datos.ObtenerTipoTicket());
			System.out.println("Parseo : Mensaje Parseado Reserva : " + Datos.ObtenerNroVuelo());
		}
		else if (Datos.ObtenerTipoDato() == 6) {
			Datos.FijoTipoTicket(TipoTicket);
			System.out.println("Parseo : Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Parseo : Mensaje Parseado TipoTicket : " + Datos.ObtenerTipoTicket());
//			System.out.println("Mensaje Para OTROS VUELOS");
		}
		else if (Datos.ObtenerTipoDato() == 9 && TipoTicket.compareTo("AYUDA") == 0) {
			Datos.FijoTipoTicket(TipoTicket);
		}
		else if (Datos.ObtenerTipoDato() == 10) {
			Datos.FijoTipoTicket(TipoTicket);
		}
		else  {
			TipoTicket = "ERROR";
			Datos.FijoTipoTicket(TipoTicket);
        	System.out.println("Parseo : Faltan Datos Necesarios Para Realizar la Busqueda o El SMS Es Basura: " + Datos.ObtenerTipoTicket());
        }
		
		//if (!FechaRetorno.isEmpty()) {System.out.println("Mensaje Parseado FechaRetorno : " + FechaRetorno);}
        
//		FechaIda = ParsearFecha(FechaIda,0);
		//System.out.println("Mensaje Parseado Ida Dia : " + FechaDia);
		//System.out.println("Mensaje Parseado Ida Mes : " + FechaMes);
		//System.out.println("Mensaje Parseado Ida Año : " + FechaAnio);
		//System.out.println("Mensaje Parseado Ida FechaIDA : " + FechaIda);
        if (Datos.ObtenerTipoDato() == 2) {
    		FechaRetorno = FechaRetorno.trim();
        	FechaRetorno = ParsearFecha(FechaRetorno,1);
			Datos.FijoFechaRetorno(FechaRetorno);
        	//System.out.println("Mensaje Parseado Vuelta Dia : " + FechaDia);
        	//System.out.println("Mensaje Parseado Vuelta Mes : " + FechaMes);
        	//System.out.println("Mensaje Parseado Vuelta Año : " + FechaAnio);
        	System.out.println("Parseo : Mensaje Parseado FechaRetorno : " + Datos.ObtenerFechaRetorno());
        }
        return Datos;
	}
}
