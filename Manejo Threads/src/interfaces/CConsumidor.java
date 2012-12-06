package interfaces;

public class CConsumidor extends Thread {

	private CColaEntrada Cola1;
	private boolean Conexion; 
	public CMessage msg;
	public CIncomingMessage msginput;
	public CProtocolMessage DatosIH;
	public int Flag;
	public CBaseDatos BD;
	public CInicio Inicio;
	public CParseo Parseo;
	public CDatos Datos;
	
	public CConsumidor(CColaEntrada Cola11, CBaseDatos BD, CInicio Inicio) {
		Cola1 = Cola11;
		this.BD = BD;
		this.Inicio = Inicio;
		Parseo = new CParseo();
		Datos = new CDatos();
		msginput = null;
		DatosIH = null;
		Flag = 0;
	}
	
	public void ModificarFlag(int alta) {
		Flag = alta;
	}
	
	public int ObtenerFlag() {
		return Flag;
	}
	
	public void Desconectar() {
//		Conexion = false;
		Inicio.EscriboLog("Desactivando Consumidor De SMS");
		System.out.println("Consumidor : Comenzando Desconexión");
	}
	
	public void run() {
		Conexion = true;
//		int superid = 0, memIndexVar = 1;
//		Inicio.FinProcesoSMS();
		//boolean Conexion = false;
		//CIncomingMessage msg;
		System.out.println("Tamaño Cola: "+Cola1.TamañoCola());
		//System.out.println("Consumidor: Duermo Consumidor 30 Segundos...");
		//try { sleep(30000); } catch (Exception e) {}
		while (Conexion || Cola1.TamañoCola()>0) {
//			if (flag == 1) {
			if (Cola1.TamañoCola()>0) {
//				msgprotocol = (CProtocolMessage) Cola1.UltimoCola();		
//				Saco el mensaje de la cola y lo imprimo en pantalla
				msg = Cola1.Sacar();
//				msg = Cola1.UltimoCola();
				if (msg.getMemIndex() != 0) {
					msginput = (CIncomingMessage) msg;
//					superid = msginput.getId();
//					memIndexVar = msginput.getMemIndex();		
					Inicio.ImprimirConsolaConsumidor(msginput.toString());
					Inicio.MensajeRecibido();
					if (Inicio.XMLin) { Inicio.CreoArchivoXML(msginput); }
					Inicio.EscriboLogDatosSMS(msginput);
					Datos.Limpiar();
					Datos = Parseo.Parsear(msginput, Datos);
					BD.AlmacenoSMSEntrantes(msginput, Datos);
//					Almaceno en Base Datos el SMS
					if (Datos.ObtenerTipoDato() == 1) {
						Inicio.ProcesarSMSTipo1(Datos);
//						int aux = BD.buscovuelo(Datos);
//						Inicio.ImprimirConsolaConsumidor("Usted Puede viajar en vuelo nro: " + aux);
//						Pedido de Vuelo con fecha unica de ida								
					}
					else if (Datos.ObtenerTipoDato() == 2) {
						Inicio.ProcesarSMSTipo2(Datos);
//						Pedido de Vuelo con fecha de ida y de retorno (2 vuelos)										
					}
					else if (Datos.ObtenerTipoDato() == 4) {
						Inicio.ProcesarSMSTipo4(Datos);
//						Generar SMS Ayuda Pedido Vuelos (Palabra IguazuAir)
					}
					else if (Datos.ObtenerTipoDato() == 5) {
						Inicio.ProcesarSMSTipo5(Datos);
//						Generar SMS Final Confirmación Vuelo
					}
					else if (Datos.ObtenerTipoDato() == 6) {
//						Inicio.ProcesarSMSTipo6(Datos);
//						Generar SMS Mas Vuelos Pedidos
					}
					else if (Datos.ObtenerTipoDato() == 7) {
						Inicio.ProcesarSMSTipo7(Datos);
//						Generar SMS Estado Vuelo
					}
					else if (Datos.ObtenerTipoDato() == 8) {
						Inicio.ProcesarSMSTipo8(Datos);
//						Generar SMS Ayuda Pedido Vuelos (Mal Envio de SMS de Pedido de Vuelo)
					}
					else if (Datos.ObtenerTipoDato() == 9) {
						Inicio.ProcesarSMSTipo9(Datos);
//						Generar SMS Ayuda Confirmación Vuelos (Mal Envio de SMS de Confirmación de Vuelo)
					}
					else if (Datos.ObtenerTipoDato() == 10) {
						Inicio.ProcesarSMSTipo10(Datos);
//						Generar SMS Ayuda de Pedido de Estados De Vuelos
					}
					else if (Datos.ObtenerTipoDato() == 3) { 
						Inicio.ProcesarSMSTipo3(Datos);
//						Inicio.EnviarAyudaTipo2(Datos);
//						Generar SMS Ayuda Si Es Necesario (Si el SMS no es basura)							
					}
					//Inicio.FinProcesoSMS();
				}
				else {
//					escribe mensaje de protocolo en LOG
					DatosIH = (CProtocolMessage) msg;
//					superid = DatosIH.getId();
//					memIndexVar = DatosIH.getMemIndex();
					Inicio.ImprimirConsolaConsumidor(DatosIH.toString());
					Inicio.CreoArchivoXML(DatosIH);
					if (Inicio.InfoProtocolo) { Inicio.EscriboLog(DatosIH); }								// InfoProtocolo Me Indica Si El Usuario Quiere Almacenar Datos De Protocolo En El Log
					if (!DatosIH.getConexion()) {
						if (DatosIH.getText().contains("Tipo De Error")) {
							Conexion = false;
							Inicio.DesactivoServidor(DatosIH.getText());
						}
						else { 
							Inicio.V1.jTextField7.setText("Desconectado");
							if (DatosIH.getText().contains("Interfaz de Hardware Desactivado")) { Conexion = false; }
						}
					}
					else {
						if (DatosIH.getText().contains("Dispositivo GSM Conectado")) { Inicio.CelularConectado(); }
						Inicio.ActualizoDatosTC(DatosIH.getSeñal(),DatosIH.getBateria());
//						if (DatosIH.getText().contains("Dispositivo GSM Conectado")) {
						if (Inicio.SMSParaEnviar>0 && Inicio.SMSParaEnviar == DatosIH.getSMSGrupales()) {
							if (DatosIH.getSMSGrupales()<2) {
								Inicio.ImprimirTituloProductor();
								Inicio.ImprimirConsolaProductor(" Se Envió " + DatosIH.getSMSGrupales() + " Mensaje SMS al Cliente Elegido");
								Inicio.EscriboLog("Se Envió " + DatosIH.getSMSGrupales() + " Mensaje SMS al Destino Que Figura En La Lista De Clientes");	
							}
							else {
								Inicio.ImprimirTituloProductor();
								Inicio.ImprimirConsolaProductor(" Se Enviaron " + DatosIH.getSMSGrupales() + " Mensajes SMS al Grupo De Clientes");
								Inicio.EscriboLog("Se Enviaron " + DatosIH.getSMSGrupales() + " Mensajes SMS a Los Destinos Que Figuran En La Lista De Clientes");
							}
							Inicio.SMSParaEnviar = 0;
							if (Inicio.ContProd>2) { Inicio.ContProd = 2; }
						}
						else if (DatosIH.getSMSEnviados() == 1) {
							Inicio.ImprimirTituloProductor();
							Inicio.ImprimirConsolaProductor(" Se Envió " + DatosIH.getSMSEnviados() + " Mensaje SMS a La Red GSM");
							Inicio.EscriboLog("Se Envió " + DatosIH.getSMSEnviados() + " Mensaje SMS a La Red GSM");
							if (Inicio.ContProd>2) { Inicio.ContProd = 2; }
						}
						else if (DatosIH.getSMSEnviados() > 2) {
							Inicio.ImprimirTituloProductor();
							Inicio.ImprimirConsolaProductor(" Se Enviaron " + DatosIH.getSMSEnviados() + " Mensajes SMS a La Red GSM");
							Inicio.EscriboLog("Se Enviaron " + DatosIH.getSMSEnviados() + " Mensajes SMS a La Red GSM");
							if (Inicio.ContProd>2) { Inicio.ContProd = 2; }
						}
						for (int i=0;i<DatosIH.getSMSEnviados();i++) { Inicio.MensajeEnviado(); }
					}
				} 
//				Inicio.ImprimirConsolaConsumidor(" Se sacó un SMS de la cola de entrada");
				Inicio.ImprimirConsolaConsumidor(" Tamaño De La Cola De Entrada: " + Cola1.TamañoCola());
				Inicio.ImprimirConsolaConsumidor("Se Detiene El Consumidor De SMS Por " + Inicio.TiempoConsu + " Segundos");
				Inicio.ImprimirConsolaConsumidor(" Cont Prod: " + Inicio.ContProd);
				try { sleep(Inicio.TiempoConsu*1000); } catch (Exception e) {}
				Inicio.LimpiarConsolaConsumidor();
				Inicio.ContProd++;
				if (Inicio.ContProd == 5) {
					Inicio.ImprimirDatosProductor();
					Inicio.ContProd = 0;
				}
			}
			else {
				Inicio.ImprimirConsolaConsumidor(" Estado Del Consumidor De SMS");
				Inicio.ImprimirConsolaConsumidor(" No Hay Mensajes SMS En La Cola De Entrada");
				Inicio.ImprimirConsolaConsumidor(" Se Detiene El Consumidor De SMS Por " + Inicio.TiempoConsu + " Segundos");
				Inicio.ImprimirConsolaConsumidor(" Cont Prod: " + Inicio.ContProd);
				try { sleep(Inicio.TiempoConsu*2000); } catch (Exception e) {}
				Inicio.LimpiarConsolaConsumidor();
				Inicio.ContProd++;
				if (Inicio.ContProd == 5) {				
					Inicio.ImprimirDatosProductor();
					Inicio.ContProd = 0;
				}
			}
		}
		Inicio.FinalizaConsumidor();
		Inicio.LimpiarConsolaProductor();
		Inicio.LimpiarConsolaReservas();
		System.out.println("Consumidor : Finaliza Ejecución Consumidor");
	}
}
	

