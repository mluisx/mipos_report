package netbin;

public class ProbarCosas2 {
	
	public static String FechaDia = "", FechaMes = "", FechaAnio = "";
	
	public static String ParsearFecha(String Fecha, int tipofecha) {
		
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
/*	if (tipofecha == 0) {
		System.out.println("Mensaje Parseado Ida Dia : " + FechaDia);
		System.out.println("Mensaje Parseado Ida Mes : " + FechaMes);
		System.out.println("Mensaje Parseado Ida Año : " + FechaAnio);
		}
	if (tipofecha == 1) {
		System.out.println("Mensaje Parseado Vuelta Dia : " + FechaDia);
		System.out.println("Mensaje Parseado Vuelta Mes : " + FechaMes);
		System.out.println("Mensaje Parseado Vuelta Año : " + FechaAnio);
		}
*/
//	Fecha = Fecha.substring(0, Fecha.length()-1);
		Fecha = "";
		if (FechaDia.length()<2) { Fecha = "0" + FechaDia; }
		else { Fecha = FechaDia; }
		if (FechaMes.length()<2) { Fecha = Fecha + "/0" + FechaMes + "/"; }
		else { Fecha = Fecha + "/" + FechaMes + "/"; }
		Fecha = Fecha.concat(Anio);
		return Fecha;

	}

	public static void main(String[] args) {

		String Texto;
		String MiniTexto = "";
		String MiniTexto2 = "";
		String TipoTicket = "", CiudadOrigen = "", CiudadDestino = "", FechaIda = "", FechaRetorno = "", Reserva = "", EstadoVuelo = "";
		int Index1 = 0,Index2 = 1, TipoDato = 0;
		int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0, flag6 = 0, flag7 = 0, flag8 = 0;
		
		//Texto = "Vuelo desde Buenos Aires a Posadas el 4 de Noviembre del 2007";
		//Texto = "Vuelo desde Posadas a Buenos Aires el 1/10/2007";
		//Texto = "Vuelo de Posadas a Buenos Aires fecha de ida 10/1/2007 fecha de retorno 10/07/07- Maurix";
		//Texto = "Reservar Vuelo 5- Maurix";
		//Texto = "IguazuAir";
		//Texto = "Ayuda";
		//Texto = "Estado Vuelo 5- sdafasdf";
		Texto = "otros vuelos";
		Texto = Texto + " ";
		Texto = Texto.toUpperCase();
		System.out.println("Mensaje : " + Texto);
		while (Index1 < Texto.length()) {
			Index2 = Texto.indexOf(" ",Index1);
			//System.out.println("Index1: " + Index1);
			//System.out.println("Index2: " + Index2);
			if (Index2 > 0 && flag5 == 0) {
				MiniTexto = MiniTexto + "#" + Texto.substring(Index1, Index2);
				MiniTexto2 = Texto.substring(Index1, Index2);
				//Comienzo Parseo
				if (MiniTexto2.compareTo("VUELO") == 0 || MiniTexto2.compareTo("AYUDA") == 0 || MiniTexto2.compareTo("IGUAZUAIR") == 0) {
					if (flag6 == 0 && flag7 == 0 && flag8 == 0) {TipoTicket = MiniTexto2;}
				}
				else if (MiniTexto2.compareTo("COLECTIVO") == 0) {TipoTicket = MiniTexto2;}
				else if (MiniTexto2.compareTo("RESERVAR") == 0) {TipoTicket = MiniTexto2; flag6 = 1;}
				else if (MiniTexto2.compareTo("ESTADO") == 0) {TipoTicket = MiniTexto2; flag7 = 1;}
				else if (MiniTexto2.compareTo("OTROS") == 0) {TipoTicket = MiniTexto2; flag8 = 1;}
				else if (MiniTexto2.compareTo("DESDE") == 0 || MiniTexto2.compareTo("ORIGEN") == 0 || (MiniTexto2.compareTo("DE") == 0 && flag3 == 0)) {flag1 = 1;}
				else if (MiniTexto2.compareTo("A") == 0 || MiniTexto2.compareTo("DESTINO") == 0 || MiniTexto2.compareTo("HACIA") == 0) {flag2 = 1; flag1 = 0;}
				else if ((MiniTexto2.compareTo("EL") == 0) || (MiniTexto2.compareTo("FECHA") == 0) || (MiniTexto2.compareTo("DE") == 0 && flag3 == 1) || 
						(MiniTexto2.compareTo("IDA") == 0) || (MiniTexto2.compareTo("Y") == 0) || (MiniTexto2.compareTo("RETORNO") == 0)) {flag2 = 0; flag3 = 1;}
				else if (flag1 == 1) {CiudadOrigen = CiudadOrigen + MiniTexto2 + " ";}
				else if (flag2 == 1) {CiudadDestino = CiudadDestino + MiniTexto2 + " ";}
				else if (flag3 == 1 && flag4 == 0) {FechaIda = FechaIda + MiniTexto2 + " "; flag4 = 1;}
				else if (flag4 == 1) {FechaRetorno = FechaRetorno + MiniTexto2 + " "; flag5 = 1;}
				else if (flag6 == 1) {Reserva = MiniTexto2; flag6 = 0;}
				else if (flag7 == 1) {EstadoVuelo = MiniTexto2; flag7 = 0;}
				//Fin Parseo
				Index1 = Index2 + 1;
			}
			else {
				Index1 = Texto.length();
			}
		}
		
		TipoTicket = TipoTicket.trim();
		System.out.println("TipoTicket: " + TipoTicket);
		
		if (!TipoTicket.isEmpty() && !CiudadOrigen.isEmpty() && !CiudadDestino.isEmpty() && !FechaIda.isEmpty()) {
			if (FechaRetorno.isEmpty()) {TipoDato = 1; System.out.println("Tipo Dato " + TipoDato);}
			else {TipoDato = 2; System.out.println("Tipo Dato " + TipoDato);}
		}
		else if (TipoTicket.compareTo("AYUDA") == 0 || TipoTicket.compareTo("IGUAZUAIR") == 0) { TipoDato = 4; System.out.println("Tipo Dato " + TipoDato); }
		else if (TipoTicket.compareTo("RESERVAR") == 0) {TipoDato = 5; System.out.println("Tipo Dato " + TipoDato); }
		else if (TipoTicket.compareTo("ESTADO") == 0) {TipoDato = 7; System.out.println("Tipo Dato " + TipoDato);}
		else if (TipoTicket.compareTo("OTROS") == 0) {TipoDato = 6; System.out.println("Tipo Dato " + TipoDato);}
		else {TipoDato = 3; System.out.println("Tipo Dato " + TipoDato);}
		
		if (TipoDato == 1 || TipoDato == 2) {		
		CiudadOrigen = CiudadOrigen.trim();
		CiudadDestino = CiudadDestino.trim();
		FechaIda = FechaIda.trim();
		FechaRetorno = FechaRetorno.trim();
		System.out.println("Mensaje Parseado Completo : " + MiniTexto);
		System.out.println("Mensaje Parseado TipoTicket : " + TipoTicket);
		System.out.println("Mensaje Parseado CiudadOrigen : " + CiudadOrigen);
		System.out.println("Mensaje Parseado CiudadDestino : " + CiudadDestino);
		System.out.println("Mensaje Parseado FechaIda : " + FechaIda);
		if (!FechaRetorno.isEmpty()) {System.out.println("Mensaje Parseado FechaRetorno : " + FechaRetorno);}
		
        FechaIda = ParsearFecha(FechaIda,0);
		System.out.println("Mensaje Parseado Ida Dia : " + FechaDia);
		System.out.println("Mensaje Parseado Ida Mes : " + FechaMes);
		System.out.println("Mensaje Parseado Ida Año : " + FechaAnio);
		System.out.println("Mensaje Parseado Ida FechaIDA : " + FechaIda);
        if (!FechaRetorno.isEmpty()) {
        	FechaRetorno = ParsearFecha(FechaRetorno,1);
        	System.out.println("Mensaje Parseado Vuelta Dia : " + FechaDia);
        	System.out.println("Mensaje Parseado Vuelta Mes : " + FechaMes);
        	System.out.println("Mensaje Parseado Vuelta Año : " + FechaAnio);
        	System.out.println("Mensaje Parseado Ida FechaRETORNO : " + FechaRetorno);
        }
		}
		else if (TipoDato == 4) {
			System.out.println("Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Mensaje Parseado TipoTicket : " + TipoTicket);	
		}
		else if (TipoDato == 5) {
			System.out.println("Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Mensaje Parseado TipoTicket : " + TipoTicket);
			String ReservaFull = "";
			for (int i=0 ; i<Reserva.length() ; i++) {
				char Caracter = Reserva.charAt(i);
				if (Caracter == '1' || Caracter == '2' || Caracter == '3'
				 || Caracter == '4' || Caracter == '5' || Caracter == '6'
				 || Caracter == '7' || Caracter == '8' || Caracter == '9'
				 || Caracter == '0' ) {
					ReservaFull = ReservaFull + Caracter;
				}
			}
			System.out.println("Mensaje Parseado Reserva : " + Reserva);
			System.out.println("Mensaje Parseado ReservaFull : " + ReservaFull);
		}
		//Maurix
		else if (TipoDato == 7) {
			System.out.println("Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Mensaje Parseado TipoTicket : " + TipoTicket);
			String EstadoFull = "";
			for (int i=0 ; i<EstadoVuelo.length() ; i++) {
				char Caracter = EstadoVuelo.charAt(i);
				if (Caracter == '1' || Caracter == '2' || Caracter == '3'
				 || Caracter == '4' || Caracter == '5' || Caracter == '6'
				 || Caracter == '7' || Caracter == '8' || Caracter == '9'
				 || Caracter == '0' ) {
					EstadoFull = EstadoFull + Caracter;
				}
			}
			System.out.println("Mensaje Parseado EstadoVuelo : " + EstadoVuelo);
			System.out.println("Mensaje Parseado EstadoFull  : " + EstadoFull);
		}
		else if (TipoDato == 6) {
			System.out.println("Mensaje Parseado Completo : " + MiniTexto);
			System.out.println("Mensaje Parseado TipoTicket : " + TipoTicket);
			System.out.println("Mensaje Para OTROS VUELOS");
		}
		//Maurix
		else  {
        	System.out.println("Faltan Datos Necesarios Para Realizar la Busqueda");
		}
	}
}
