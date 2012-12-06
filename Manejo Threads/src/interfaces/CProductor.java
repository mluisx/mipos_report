package interfaces;

public class CProductor {
	
	private CColaSalida Cola2;
	private Object _SYNC_ = new Object();
	public int idsal = 1;
	public int FlagAnt = 0;
	public CInicio Inicio;
	
	public CProductor (CColaSalida Cola11, CInicio Inicio) {
		Cola2 = Cola11;
		this.Inicio = Inicio;
	}

	public void EnvioSMS(String Envio, String Cel, int Flag) {								// Flag = 0 => Envio Por Respuesta a Pedido Del Cliente, Flag = 1 => Envio Por Lista De Contactos
		synchronized (_SYNC_) {																// Flag = 2 => Ultimo SMS De La Lista De Contactos
//			System.out.println("Productor: Duermo Productor 23 Segundos...");
//			try { sleep(23000); } catch (Exception e) {}
//			String Envio = "Mauro, al final te quedas para la despedida del gordo? Saludos.";
//			String NroCel = "+";
//			System.out.println("Cel: " + Cel);
//			Nrocel.concat(Cel);
//			NroCel = "+" + Cel;
//			System.out.println("NroCel: " + NroCel);
			COutgoingMessage msgsal = new COutgoingMessage(Cel, Envio, idsal, Flag);
			idsal++;
			msgsal.setMessageEncoding(CMessage.MESSAGE_ENCODING_7BIT);
			Cola2.Agregar(msgsal);
			Inicio.EscriboLogDatosSMS(msgsal);
			if (Inicio.XMLout) { Inicio.CreoArchivoXML(msgsal); }
			if (Flag == 0) {
				Inicio.LimpiarConsolaProductor();
				Inicio.ImprimirConsolaProductor(msgsal.toString());
				Inicio.ImprimirConsolaProductor(" Mensaje Enviado a la Cola De Salida");
				Inicio.ImprimirConsolaProductor(" Tamaño De La Cola De Salida: " + Cola2.TamañoCola());
				Inicio.ContProd = 0;
			}
			else if (Flag == 1 || Flag == 2) {
				if (FlagAnt == 0 || FlagAnt == 2) { 
					Inicio.LimpiarConsolaProductor();
					Inicio.ImprimirConsolaProductor(" Envío De Mensajes SMS a Grupo De Clientes");
					Inicio.ImprimirConsolaProductor(" Cantidad De Mensajes SMS a Enviar: " + Inicio.ListaContactos.size());
					Inicio.ContProd = 0;
				}
				Inicio.ImprimirConsolaProductor(msgsal.ImprimirSMSGrupo());
				if (Flag == 2) {
					Inicio.ImprimirConsolaProductor(msgsal.ImprimirUltimoSMSGrupo());
					if (FlagAnt == 0 || FlagAnt == 2) { Inicio.ImprimirConsolaProductor(" Mensaje Enviado a la Cola De Salida"); }
					else { Inicio.ImprimirConsolaProductor(" Mensajes Enviados a la Cola De Salida"); }
					Inicio.ImprimirConsolaProductor(" Tamaño De La Cola De Salida: " + Cola2.TamañoCola());
				}
			}
			FlagAnt = Flag;
		}
	}
}

