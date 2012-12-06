package interfaces;

public class CInterfazHard extends Thread {
	
	private CColaEntrada Cola1;
	private CColaSalida  Cola2;
	public  CService srv;
	int status, Vel, Tiempo;
	boolean Conexion;
	int antid, superid, recorrido, smsleidos, smsenviados, smsgrupales, señal, bateria, señaltemp, bateriatemp;
	COutgoingMessage msgsal;
	CMessage msg;
	String Com;
	
	public CInterfazHard (CColaEntrada Cola11, CColaSalida Cola22) {
		Cola1 = Cola11;
		Cola2 = Cola22;
		Cola1.Limpiar();
		srv = null;
		status = 0;
		Conexion = false;
		antid = superid = recorrido = smsleidos = smsenviados = smsgrupales = Vel = Tiempo = señal = bateria = 0;
		msg = null;
		Com = "";
	}
	
	public void FijoDatosConexion(String DatoPuerto, String DatoVel, int DatoTiempo) {
		boolean Cambio = false;
		DatoPuerto = DatoPuerto.toLowerCase();
		if (Com.compareTo(DatoPuerto) != 0) {
			Com = DatoPuerto;
			Cambio = true;
		}
		if (DatoVel.compareTo(Integer.toString(Vel)) != 0) {
			Vel = Integer.parseInt(DatoVel);
			Cambio = true;
		}
		if (srv == null) { srv = new CService(Com,Vel);	System.out.println("InterfazHW : Hola1"); } // (MODIF)
		else if (Cambio) { srv = new CService(Com,Vel); System.out.println("InterfazHW : Hola2"); } // (MODIF)
		Tiempo = DatoTiempo; 
	}
	
	public void Conectar() {
		try	{
			srv.initialize();
			srv.setCacheDir(".\\"); // USAR ESTO
			status = srv.connect(Cola1);
			if (status == CService.ERR_OK) {
				srv.setOperationMode(CService.MODE_PDU);
				srv.setSmscNumber("");
				Conexion = true;
			}
			else {
				System.out.println("InterfazHW : La Conexión Al Dispositivo GSM Falló, Tipo De Error: " + status);
				Conexion = false;
			}
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
	}
	
	public void Desconectar() {
		Conexion = false;
		System.out.println("InterfazHW : Comenzando Desconexión");
	}
	
	public void run() {
		Conectar();
		while (Conexion) {			
			recorrido++;
//			Comienzo Lectura De Mensajes SMS Entrantes			
			if (srv.readMessages(Cola1, CIncomingMessage.CLASS_ALL, superid+1) == CService.ERR_OK) {
				smsleidos = srv.MensajesLeidos();
				if (smsleidos>0) { superid += smsleidos; } 
				System.out.println("/------------------------------------/");
				System.out.println("InterfazHW : Leo Datos del Celular");	
//				if (Cola1.TamañoCola()>0) { 
//					msg = Cola1.UltimoCola();
//					if (msg.getId() != 0) {
//						if (Cola1.TamañoCola()>0) {
//						msg = Cola1.UltimoCola();
//						superid = msg.getId(); // Ultimo ID
//						memIndexVar = msg.getMemIndex(); // Ultimo MemIndex
//					}
//				}
			}
			if (recorrido > 1 && antid < superid) { System.out.println("InterfazHW : Nuevo Mensaje Recibido!!!"); }
//			smsleidos = superid - antid;
			System.out.println("InterfazHW : " + recorrido + " Lectura. SMS Leidos: " + smsleidos);
			System.out.println("InterfazHW : " + "Tamaño Cola Entrada : " + Cola1.TamañoCola() + " Mensajes");
//			Envío Mensajes SMS a la Red GSM			
			while (!Cola2.EstaVacio()) {
				System.out.println("InterfazHW : Hay Mensaje Para Enviar...!!!!");
				msgsal = Cola2.Sacar();
//				int x = srv.sendMessage(msgsal);	// Todavia NO!
//				if (x == CService.ERR_OK) { // Todavia NO!
					System.out.println("InterfazHW : Envio Mensaje SMS a la red GSM");
					System.out.println("InterfazHW : Tamaño Cola Salida : " + Cola2.TamañoCola());
					System.out.println("InterfazHW : Mensaje Enviado: " + msgsal.getText());
					smsenviados++;
					if (msgsal.getFlag() == 1 || msgsal.getFlag() == 2) { smsgrupales++; }
//				} // Todavia NO!
//				else { System.out.println("Se Produjo Un Error Con El Mensaje SMS A Enviar"); } // Todavia NO!
				if (!Cola2.EstaVacio()) { try { sleep(5000); } catch (Exception e) {} }
			}		
//			int superidp = superid;
//			Envío Información De La IH Al Consumidor
//			superid++;
			srv.refreshDeviceInfo();
			srv.EnvioMensajeProtocolo(Cola1,"IH","Información Del Estado De La Interfaz", 0, 0, recorrido, smsleidos, smsenviados, smsgrupales, srv.getDeviceInfo().getSignalLevel(),
			srv.getDeviceInfo().getBatteryLevel(), Conexion);
			System.out.println("InterfazHW : Envio Mensaje Protocolo a la Cola de Entrada");
			System.out.println("InterfazHW : " + "Tamaño Cola Entrada : " + Cola1.TamañoCola() + " Mensajes");				
			System.out.println("InterfazHW : Duermo InterfazHW " + Tiempo + " Segundos...");
			System.out.println("InterfazHW : AntId: " + antid + " SuperID:" + superid);
			System.out.println("/------------------------------------/");
			smsenviados = smsgrupales = 0;
//			msg = Cola1.UltimoCola();
//			antid = msg.getId();
			antid = superid;
			try { sleep(Tiempo*1000); } catch (Exception e) {}
		}
		if (recorrido == 0 && !Conexion) { 
			srv.disconnect();
			System.out.println("InterfazHW : No se Pudo Realizar La Conexión...Verifique Si El Dispositivo GSM Se Encuentra Conectado y Si Los Datos De Configuración Son Correctos");
			System.out.println("InterfazHW : Finaliza Ejecución Interfaz De Hardware");
			srv = null;
		}
		else {
			status = 0;
			msg = null;
			srv.refreshDeviceInfo();
//			superid++;
			srv.EnvioMensajeProtocolo(Cola1,"IH","Interfaz de Hardware Desactivado - Dispositivo GSM Desconectado", 0, superid, recorrido, smsleidos, smsenviados, smsgrupales,
			srv.getDeviceInfo().getSignalLevel(), srv.getDeviceInfo().getBatteryLevel(), Conexion);
			srv.disconnect();
//			smsenviados = smsgrupales = 0;
//			msg = Cola1.UltimoCola();
//			antid = msg.getId();
			System.out.println("InterfazHW : Finaliza Ejecución Interfaz De Hardware");
		}
	}
}

