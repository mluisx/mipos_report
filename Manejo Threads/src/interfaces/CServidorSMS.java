package interfaces;

public class CServidorSMS {
	
	public static CInterfazGrafica VentanaPrincipal;

	public static void main(String[] args) {

		/*System.out.println("Ejecutamos Thread HW");
		ColaEntrada Cola1 = new ColaEntrada();
		ColaSalida  Cola2 = new ColaSalida();
		InterfazHWVer3 Maurix = new InterfazHWVer3(Cola1, Cola2);
		Consumidor Consu = new Consumidor(Cola1);
		Productor Prod = new Productor(Cola2);
		//ServidorUIver2 = new ServidorUIVer2();
		Maurix.start();
		Consu.start();
		Prod.start();*/
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			    VentanaPrincipal = new CInterfazGrafica();
				VentanaPrincipal.setVisible(true);
		        //ColaEntrada Cola1 = new ColaEntrada();
		        //ColaSalida  Cola2 = new ColaSalida();  	
		        //InterfazHWVer3 Maurix = new InterfazHWVer3(Cola1, Cola2, VentanaPrincipal);
			}
	    });
	}	
}



