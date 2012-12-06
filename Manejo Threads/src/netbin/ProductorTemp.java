package netbin;

import interfaces.CMessage;
import interfaces.COutgoingMessage;
import interfaces.CService;

public class ProductorTemp {
	
	public static void main(String[] args) {
		int status = 0, idsal = 1; //superid = 0, recorrido = 1, memIndexVar = 1;
		boolean Conexion = false;
		//CIncomingMessage msg;
		//COutgoingMessage msgsal;
		CService srv = new CService("com7",115200);
		//Cola1.Limpiar();
		//InterfazPreFinal V1 = null;
		try	{
			srv.initialize();
			srv.setCacheDir(".\\");
			status = srv.connect();
			if (status == CService.ERR_OK){
				srv.setOperationMode(CService.MODE_PDU);
				srv.setSmscNumber("");
				Conexion = true;
			}
			else {
				System.out.println("Connection to mobile failed, error: " + status);
				System.exit(0);
			}
		}
		catch (Exception e)	{
			e.printStackTrace();
		}
		if (Conexion){
			System.out.println("ProductorTemp...");
			COutgoingMessage msgsal = new COutgoingMessage("+8237027217", "Mensaje Salida", idsal);
			msgsal.setMessageEncoding(CMessage.MESSAGE_ENCODING_UNICODE);
			System.out.println(msgsal);
			if (srv.sendMessage(msgsal) == CService.ERR_OK) {
				System.out.println("Interfaz: Envio Mensaje Almacenado En Cola Salida");
			}
			srv.disconnect();
			System.out.println("Termina ProdTemp");
		}	
	System.exit(0);	
	}
}

