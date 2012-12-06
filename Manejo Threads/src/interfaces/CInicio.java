package interfaces;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;

public class CInicio {

	public CInicio(CInterfazGrafica V1) {	
		this.V1 = V1;
		ListaContactos = new LinkedList<CContacto>();
		GrupoContactos1 = new LinkedList<Object>(); 			// Grupo De Contactos Por Tipo De Cliente
		GrupoContactos2 = new LinkedList<Object>(); 			// Grupo De Contactos Por Ciudad De Residencia
		ListaReservas  = new LinkedList<Integer>();
        BD = new CBaseDatos(this);
		Cola1 = new CColaEntrada();
		Cola2 = new CColaSalida();
		Maurix = new CInterfazHard(Cola1, Cola2);
		Consu = new CConsumidor(Cola1,BD,this);
		Prod = new CProductor(Cola2, this);
		ModeloLista2 = V1.ModeloLista2; 						//Cargo El Modelo Que Uso Para La Lista De Reservas	
		CodigoReserva = 0; 										//Inicializo El Nro De Reserva Que Muestro En La Lista De Reservas
		Reserva = null;
		XMLin = XMLout = true;
		GenerarLog = InfoProtocolo = true;
		Com = "COM7";
		Vel = "115200";
		TiempoIH = 20;
		TiempoConsu = 5;
		TiempoContador = 3;
		FlagContactos = Señal = Bateria = SMSParaEnviar = ContProd = 0;
		CreoLog();
	};
	
	public CInterfazGrafica V1;
	public CConsumidor Consu;
	public CProductor Prod;
	public CBaseDatos BD;
	public CColaEntrada Cola1;
	public CColaSalida  Cola2;
	public LinkedList<CContacto> ListaContactos;
	public LinkedList<Object> GrupoContactos1;
	public LinkedList<Object> GrupoContactos2;
	public LinkedList<Integer> ListaReservas;
	public CContacto Contacto;
	public CInterfazHard Maurix;
	public javax.swing.DefaultListModel ModeloLista2;
	public int CodigoReserva;
	public CReserva Reserva;
	public PrintWriter Log;
	public File MyLog;
	public String Com,Vel;								// Variables Que Indican Puerto De Comunicaciones y Velocidad De Conexión Al TC
	public boolean XMLin, XMLout;						// Contiene Opción De Si/No Para Grabar Archivos XML de Cada Mensaje SMS
	public int TiempoIH, TiempoConsu, TiempoContador;	// Tiempo Para Las Lecturas Del Dispositivo GSM, Para La Espera Entre Procesos De Mensajes SMS En El Consumidor y El Tiempo Del Contador.
	public int FlagContactos;							// Flag Para Saber Si Los Contactos Se Encuentran Cargados En La Lista De Contactos
	public boolean GenerarLog, InfoProtocolo;			// Contiene Opción De Si/No Para Generar El Archivo De Log y La Información De Protocolo 
	public int Señal, Bateria;							// Almaceno Los Datos Del TC Para No Repetir Lineas En Archivo De Log
	public int SMSParaEnviar;							// Cantidad de Mensajes SMS A Enviar A Los Clientes Que Figuran En La Lista De Contactos
	public int ContProd;								// Contador Para Saber En Que Momento Limpiar La Consola De Productor
	
	public void Conexion() {
		V1.jTextField13.setText(Com);
		V1.jTextField14.setText(Vel);
		Maurix.FijoDatosConexion(Com, Vel, TiempoIH);
		BD.ModificoContador(TiempoContador);
		new Thread(Consu).start();
		FijoTexto2("Consumidor de SMS activado.");
		EscriboLog("Consumidor de SMS Activado");
		new Thread(Maurix).start();
		FijoTexto2("Interfaz de hardware activado.");
		EscriboLog("Interfaz de Hardware Activado");		
//		FijoTexto2("Productor : Activado");
//		EscriboLog("Productor : Activado");
	}
	
	public void Desconexion() {
		V1.jTextField13.setText(" -");
		V1.jTextField14.setText(" -");
		Maurix.Desconectar();
//		V1.jTextField7.setText("Desconectado");
		Consu.Desconectar();
		BD.Contador.Desconectar();
	}
	
//	public void AjustoConsu(Consumidor Consu){this.Consu = Consu;}
//	public void AjustoProd(Productor Prod){this.Prod = Prod;}

	public int ActivoConsumidor() {
		int i = Consu.ObtenerFlag();
		if (i == 0) { 
			Consu.ModificarFlag(1);
			EscriboLog("Consumidor De SMS Activado");
			System.out.println("Inicio : Consumidor Activado");
		}
		else { 
			Consu.ModificarFlag(0);
			EscriboLog("Consumidor De SMS Desactivado");
			System.out.println("Inicio : Consumidor Desactivado");
		}
	return i;
	}
	
	public void FinalizaConsumidor() {
		V1.jTextField5.setText("Desactivado");
		EscriboLog("Interfaz de Hardware Desactivado");
		EscriboLog("Consumidor De SMS Desactivado");
		EscriboLog("Servidor Desactivado");
		V1.jMenuItem6.setEnabled(false);
		V1.jMenuItem1.setEnabled(true);
	}
	
    public void FijoTexto1(String Texto){
    	String Temp = V1.jTextArea1.getText();
    	if (Temp.length() > 0) {Temp = Temp + "\n";}
    	Temp = Temp + Texto;
    	V1.jTextArea1.setText(Temp);
    }
    
    public void FijoTexto2(String Texto){
    	String Temp = V1.jTextArea2.getText();
    	if (Temp.length() > 0) {Temp = Temp + "\n";}
    	Temp = Temp + Texto;
    	V1.jTextArea2.setText(Temp);
    }
    
    public void HabilitoConexion() {
    	V1.jMenuItem2.setEnabled(false);
    	V1.jMenuItem1.setEnabled(true);	
    }
	
	public void EnviarMensaje(javax.swing.DefaultListModel SModel) {
		if (V1.Caracteres>=0) {
			String Envio = V1.jTextArea1.getText();
//			String TextoLista = "";
//			ImprimirConsolaProductor(" Envío de Mensajes SMS a Los Clientes de la Lista de Contactos");
			for (SMSParaEnviar = 0;SMSParaEnviar < ListaContactos.size();SMSParaEnviar++) {
				Contacto = ListaContactos.get(SMSParaEnviar);
				String Cel = Contacto.ObtenerNumeroCel();
				System.out.println("Inicio : Número a Enviar : " + Cel);
				if (SMSParaEnviar+1 < ListaContactos.size()) { Prod.EnvioSMS(Envio,Cel,1); }
				else { Prod.EnvioSMS(Envio,Cel,2); }
//				TextoLista = SModel.getElementAt(i).toString() + " - Enviado";
//				SModel.removeElementAt(i);
//				SModel.insertElementAt(TextoLista,i);
			}
		}
		else { V1.jTextArea2.setText("La cantidad de caracteres del\nmensaje escrito supera el tamaño\nmáximo de 160 caracteres."); }
	}
	
//	Procesos de Mensajes SMS Entrantes
	
	public void ProcesarSMSTipo1(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 10;
		Cel = Datos.ObtenerNroCelOrigen();
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO: " + Cel);
		Envio = BD.BuscoVuelo(Datos);
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
		if (!Envio.contains("Le Informa Que No Hay Vuelos Disponibles Para El Destino y La Fecha Deseados")) { BD.AlmacenoPedido(Datos); }
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
//		(MODIF) Las Variables Envio y Cel Junto a la Variable TipoDato ponerlas todas en una sola variable global
//		ActivoContador();
//		Contador = new CContador();
//		Contador.run();
		Prod.EnvioSMS(Envio,Cel,0);
	}
	
	public void ProcesarSMSTipo2(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 10;
		Cel = Datos.ObtenerNroCelOrigen();
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO: " + Cel);
		Envio = BD.BuscoVueloIdaRetorno(Datos);
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
		if (!Envio.contains("AeroIguazu Le Informa Que No Hay Vuelos Disponibles Para El Destino Requerido En Las Fechas De Ida y Vuelta Deseados")) { BD.AlmacenoPedido(Datos); }
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
//		ActivoContador();
//		Contador = new CContador();
//		Contador.run();
		Prod.EnvioSMS(Envio,Cel,0);
	}
	
//	Verificar si el mensaje con error es de un cliente que ya hizo un pedido de vuelos o si proviene de un celular cualquiera (MODIF)
	public void ProcesarSMSTipo3(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 16;
		int TipoError;
		Cel = Datos.ObtenerNroCelOrigen();
		TipoError = BD.BuscoTipoAyuda(Datos);
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE MANDO SMS ERRONEO TOTAL: " + Cel);
		if (TipoError == 2) {
        	Envio = "Envíe \"Reservar\" + \"Vuelo\" + \"Nro\" Para Confirmar La Reserva / Para Los Estados De Vuelos, Envíe \"Estado\" + \"Vuelo\" + \"Nro\"";
			System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
			Prod.EnvioSMS(Envio,Cel,0);			
        	Envio = "Envíe \\\"Reservar\\\" + \\\"Vuelo\\\" + \\\"Nro\\\" para Confirmar La Reserva / Para Los Estados De Vuelos, Envíe \\\"Estado\\\" + \\\"Vuelo\\\" + \\\"Nro\\\"";
			BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
		}
		else { System.out.println("Inicio : Se Recibio Un SMS Basura De Esta Persona"); }
//		Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
//		Prod.EnvioSMS(Envio,Cel);
	}
	
	public void ProcesarSMSTipo4(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 13;
		Cel = Datos.ObtenerNroCelOrigen();
//		Envio = BD.BuscoTipoAyuda(Datos);
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO DE AYUDA: " + Cel);
		Envio = "Bienvenido a AeroIguazu - Para Ver Vuelos Disponibles, Envie \"Vuelo\" + \"Desde\" + \"Ciudad Origen\" + \"Ciudad Destino\" + \"Fecha Ida\" + \"Fecha Retorno\" (Opcional)";
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//		Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
		Prod.EnvioSMS(Envio,Cel,0);
		Envio = "Bienvenido a AeroIguazu - Para Ver Vuelos Disponibles, Envie \\\"Vuelo\\\" + \\\"Desde\\\" + \\\"Ciudad Origen\\\" + \\\"Ciudad Destino\\\" + \\\"Fecha Ida\\\" + \\\"Fecha Retorno\\\" (Opcional)";
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
	}
	
	public void ProcesarSMSTipo5(CDatos Datos) {
		String Envio, Cel, Agrego;
		int TipoDato = 14;
//		Envio = "CONFIRMO VUELO";
		Envio = BD.ConfirmarVuelo(Datos); // Aca modifico CodigoReserva
		Cel = Datos.ObtenerNroCelOrigen();
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO DE CONFIRMACION: " + Cel);
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//		Contacto = new CContacto(Nombre, NumeroCel);
//		ListaContactos.add(Contacto);
//  	Almaceno Reserva En BD - Tabla reservas
//		if (Cel.compareTo("+8238302131") == 0) {Prod.EnvioSMS(Envio,Cel);}		
		CodigoReserva = Datos.ObtenerCodigoReserva();
		if (CodigoReserva != 0) { 
			Agrego = "Cel: " + Cel + " - Reserva Nro: " + CodigoReserva;
			ModeloLista2.addElement(Agrego);
			ListaReservas.add(CodigoReserva);
			BD.AlmacenoReserva(Datos);
			BD.BorrarPedido(Datos);
			Prod.EnvioSMS(Envio,Cel,0);
			BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
		}
		else {
			Prod.EnvioSMS(Envio,Cel,0);
			if (Envio.contains("Sus Numeros De Vuelos Son Incorrectos")) {
				Envio = "Sus Numeros De Vuelos Son Incorrectos, Por Favor, Envíe \\\"Reservar\\\" + \\\"Vuelo\\\" + \\\"NroVuelo1\\\" + \\\"y\\\" + \\\"NroVuelo2\\\" Para Confirmar Las Reservas";
			}
			else if (Envio.contains("Su Numero De Vuelo Es Incorrecto")) {
				Envio = "Su Numero De Vuelo Es Incorrecto, Por Favor, Envíe \\\"Reservar\\\" + \\\"Vuelo\\\" + \\\"NroVuelo\\\" Para Confirmar La Reserva";
			}
			BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
		}
	}
	
	public void ProcesarSMSTipo7(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 12;
		Cel = Datos.ObtenerNroCelOrigen();
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO DE ESTADOS DE VUELOS: " + Cel);
		Envio = BD.BuscoEstadoVuelo(Datos);
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//		BD.AlmacenoPedido(Datos);
		Prod.EnvioSMS(Envio,Cel,0);
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
	}
	
	public void ProcesarSMSTipo8(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 15;
		Cel = Datos.ObtenerNroCelOrigen();
//		Envio = BD.BuscoTipoAyuda(Datos);
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO ERRONEO DE VUELOS: " + Cel);
		Envio = "Para Ver Vuelos Disponibles, Envie \"Vuelo\" + \"Desde\" + \"Ciudad Origen\" + \"Ciudad Destino\" + \"Fecha Ida\" + \"Fecha Retorno\" (El Retorno Es Opcional)";
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//		Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
		Prod.EnvioSMS(Envio,Cel,0);
		Envio = "Para Ver Vuelos Disponibles, Envie \\\"Vuelo\\\" + \\\"Desde\\\" + \\\"Ciudad Origen\\\" + \\\"Ciudad Destino\\\" + \\\"Fecha Ida\\\" + \\\"Fecha Retorno\\\" " +
				"(El Retorno Es Opcional)";
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
	}
	
	public void ProcesarSMSTipo9(CDatos Datos) {
		String Envio, Cel;
		int TipoAyuda, TipoDato;
		Cel = Datos.ObtenerNroCelOrigen();
		TipoAyuda = BD.BuscoTipoAyuda(Datos);
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE MANDO SMS CONFIRMACION ERRONEO: " + Cel);
		if (TipoAyuda == 1) {
			TipoDato = 18;
			Envio = "Para Ver Vuelos Disponibles, Envie \"Vuelo\" + \"Desde\" + \"Ciudad Origen\" + \"Ciudad Destino\" + \"Fecha Ida\" + \"Fecha Retorno\" (El Retorno Es Opcional)";
			System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//			Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
			Prod.EnvioSMS(Envio,Cel,0);
			Envio = "Para Ver Vuelos Disponibles, Envie \\\"Vuelo\\\" + \\\"Desde\\\" + \\\"Ciudad Origen\\\" + \\\"Ciudad Destino\\\" + \\\"Fecha Ida\\\" + \\\"Fecha Retorno\\\" " +
					"(El Retorno Es Opcional)";
		}
		else if (TipoAyuda == 2) {
			TipoDato = 16;
			Envio = "Envie \"Reservar\" + \"Vuelo\" + \"NroDeVuelo\" Para Confirmar La Reserva / Para Los Estados De Vuelos, Envie \"Estado\" + \"Vuelo\" + \"Nro\"";
			System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//			Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
			Prod.EnvioSMS(Envio,Cel,0);
			Envio = "Envie \\\"Reservar\\\" + \\\"Vuelo\\\" + \\\"NroDeVuelo\\\" Para Confirmar La Reserva / Para Los Estados De Vuelos, Envie \\\"Estado\\\" + \\\"Vuelo\\\" + \\\"Nro\\\"";
		}
		else {
			TipoDato = 16;
			Envio = "Envie \"Reservar\" + \"Vuelo\" + \"NroVueloIda\" + \"NroVueloVuelta\" Para Confirmar Reserva / Para Estados De Vuelos, Envie \"Estado\" + \"Vuelo\" + \"Nro\"";
			System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//			Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
			Prod.EnvioSMS(Envio,Cel,0);
			Envio = "Envie \\\"Reservar\\\" + \\\"Vuelo\\\" + \\\"NroVueloIda\\\" + \\\"NroVueloVuelta\\\" Para Confirmar Reserva / Para Estados De Vuelos, Envie \\\"Estado\\\" + " +
					"\\\"Vuelo\\\" + \\\"Nro\\\"";
		}
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
	}
	
	public void ProcesarSMSTipo10(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 17;
		Cel = Datos.ObtenerNroCelOrigen();
//		Envio = BD.BuscoTipoAyuda(Datos);
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO AYUDA ESTADOS DE VUELOS: " + Cel);
		Envio = "Bienvenido a AeroIguazu - Para Conocer El Estado De Su Vuelo, Envie Las Palabras \"Estado\" + \"Vuelo\" + \"Nro De Vuelo\"";
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
//		Verificar si la ayuda es del tipo 1 o 2 viendo las filas de la tabla pedidos (MODIF)
		Prod.EnvioSMS(Envio,Cel,0);
		Envio = "Bienvenido a AeroIguazu - Para Conocer El Estado De Su Vuelo, Envie Las Palabras \\\"Estado\\\" + \\\"Vuelo\\\" + \\\"Nro De Vuelo\\\"";
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
	}
	
//		Este Procedimiento todavia no lo uso (MODIF)
//		Borrar
/*	public void EnviarAyudaTipo2(CDatos Datos) {
		String Envio, Cel;
		int TipoDato = 13;
		Cel = Datos.ObtenerNroCelOrigen();
		System.out.println("Inicio : NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO ERRONEO: " + Cel);
		Envio = "Envie \"Reservar\" + \"Vuelo\" + \"Nro\" Para Confirmar La Reserva / Para Conocer Los Estados De Vuelos, Envie \"Estado\" + \"Vuelo\" + \"Nro\"";
		System.out.println("Inicio : Envio Al Que Hizo Pedido: " + Envio);
		Prod.EnvioSMS(Envio,Cel,0);
		BD.AlmacenoSMSSalientes(Datos, Envio, TipoDato);
	}*/
//		Borrar (MODIF)
	
/*	
	public void ProcesarSMSTipo6(CDatos Datos) {
		String Envio, Cel;
		Cel = Datos.ObtenerNroCelOrigen();
		System.out.println("NUMERO DE CEL DE PERSONA QUE HIZO PEDIDO: " + Cel);
//		Envio = BD.BuscoVuelo(Datos);
//		System.out.println("Envio Al Que Hizo Pedido: " + Envio);
//		Prod.EnvioSMS(Envio,Cel);	
	}*/
	
/*	public void CargarReservas() {
		String Envio, Cel, Agrego;
		
		Agrego = "Cel: " + Cel + " - Reserva Nro: " + CodigoReserva;
		ModeloLista2.addElement(Agrego);
		ListaReservas.add(CodigoReserva);
	}*/
	
//	Contactos
	
	public int IngresoContacto(String Nombre, String NumeroCel, javax.swing.DefaultListModel SModel, String TipoCliente, String Ciudad) {
		int Flag, Filas;
		Flag = Filas = 0;
		CContacto ContactoTemp = null;
		if (!ListaContactos.isEmpty()) {
			for (int i=0;i<ListaContactos.size();i++) {
				ContactoTemp = ListaContactos.get(i);
				if (NumeroCel.compareTo(ContactoTemp.ObtenerNumeroCel()) == 0) {
					Flag = 1;
					FlagContactos++;
					if (ContactoTemp.ObtenerTipoCliente().compareTo("-") == 0 && TipoCliente.compareTo("-") != 0) { ContactoTemp.FijarTipoCliente(TipoCliente); }
					else if (ContactoTemp.ObtenerCiudadRes().compareTo("-") == 0 && Ciudad.compareTo("-") != 0) { ContactoTemp.FijarCiudadRes(Ciudad); }
				}
			}
		}
		if (Flag == 0) {
			Contacto = new CContacto(Nombre, NumeroCel , TipoCliente, Ciudad);
			ListaContactos.add(Contacto);
			String Agrego = Contacto.ObtenerNombre() + " : " + Contacto.ObtenerNumeroCel();
			SModel.addElement(Agrego);
			Filas = 1;
		}
		return Filas;
    }

	public void BorrarContacto(javax.swing.DefaultListModel SModel) {
		int Largo = ListaContactos.size();
		for (int i=0;i<Largo;i++) { ListaContactos.remove(); }
		SModel.removeAllElements();
	}

//  Creo Un Método Para Pasar Strings a Objetos y Poder Grabarlos En Una Cola De Objetos De Nombres De Grupos De Contactos
	
	public Object makeObj(final String item) { 
	return new Object() { 
		public String toString() { return item; } };
	}	

//  Guardo Contactos Seleccionados En La BD Con Un Nombre De Grupo (MODIF) Ahora No Guardo Más Grupos De Clientes
	
	public void GuardarContactos(int indice[], String NombreGrupo, int flag) {
		int Resultado = 0;
		for (int i=0;i<indice.length;i++) {
			String Cel, Nombre;
			Contacto = ListaContactos.get(indice[i]);
			Cel = Contacto.ObtenerNumeroCel();
			Nombre = Contacto.ObtenerNombre();
			Resultado = Resultado + BD.AlmacenoContacto(NombreGrupo,Nombre,Cel);
		}
		if (Resultado == indice.length && Resultado != 0 && flag == 0) {
			GrupoContactos1.add(makeObj(NombreGrupo));
		}
		else if (indice.length == 0) {
			System.out.println("Inicio : Debe Seleccionar Contactos Para Poder Guardarlos En Un Grupo");
		}
	}
	
//  Cargo Grupo De Contactos Almacenado En La BD En Ventana Principal
	
	public void CargarContactos(Object Grupo, Object Grupo2, javax.swing.DefaultListModel SModel, int Flag) {	// Cargo Grupo De Contactos. Flag = 0 => Cargo Por Tipo De Cliente. Flag = 1 => ...
		String TipoCliente, CiudadRes;																			// ... Cargo Por Ciudad De Residencia. Flago = 2 => Cargo Por Ambas Categorías.
		int Filas;
		TipoCliente = CiudadRes = "";
		if (Flag == 0) { TipoCliente = Grupo.toString(); }											
		else if (Flag == 1) { CiudadRes = Grupo2.toString(); }
		else { 
			TipoCliente = Grupo.toString();
			CiudadRes = Grupo2.toString();
		}
		FlagContactos = 0;
		Filas = BD.CargarContactos(TipoCliente, CiudadRes, SModel, Flag);
		if ( Flag != 2 && Filas == 0 && FlagContactos>0) { V1.jTextArea2.setText("Los datos de los clientes requeridos\nse encuentran cargados en la lista de contactos."); }
		else if ( Flag == 2 && Filas == 0 && FlagContactos == 0) { V1.jTextArea2.setText("No existen clientes con el tipo de\ncliente y ciudad de residencia\nrequeridos."); }
		else if ( Flag == 2 && Filas == 0 && FlagContactos>0) { V1.jTextArea2.setText("Los datos de los clientes requeridos\nse encuentran cargados en la lista de contactos."); }
	}

	public void MensajeRecibido() {
		String Cantidad;
		int Temp;
		Cantidad = V1.jTextField11.getText();
		Temp = Integer.parseInt(Cantidad);
		Temp++;
		V1.jTextField11.setText(Integer.toString(Temp));
	}
	
	public void MensajeEnviado() {
		String Cantidad;
		int Temp;
		Cantidad = V1.jTextField12.getText();
		Temp = Integer.parseInt(Cantidad);
		Temp++;
		V1.jTextField12.setText(Integer.toString(Temp));
	}
	
	public void CelularConectado() {
		V1.jTextField7.setText("Conectado");
		V1.jTextArea2.setText("");
	}
	
	public int IngresoBD(String Usuario, String Password) {
		int Resultado = 0;
		try {
			Resultado = BD.activarbd(Usuario, Password);
//			BD.CargarGrupoContactos(GrupoContactos);
		} catch (ClassNotFoundException Error1) {
			FijoTexto2("Error Tipo 1 En Base De Datos");
			return 1;
		} catch (IOException Error2) {
			FijoTexto2("Error Tipo 2 En Base De Datos");
			return 1;
		}
		return Resultado;
	}
	
    public void DesconectarBD() {
    	int Resultado;
    	Resultado = BD.desactivarbd();
    	if (Resultado == 0) {
    		V1.jTextField6.setText("Desconectada");
    		V1.jMenuItem4.setEnabled(true);
    		V1.jMenuItem2.setEnabled(false);    		
    	}
    	else {
    		V1.jTextArea2.setText("No se pudo desconectarse de la base de datos. Por favor, reintentelo\nnuevamente.");
    	}
    }
    
	public void ImprimirConsolaConsumidor(String Texto) {
		String TextoFull;
		TextoFull = V1.jTextArea6.getText();
    	if (TextoFull.length() > 0) {TextoFull = TextoFull + "\n";}
    	TextoFull = TextoFull + Texto;
    	V1.jTextArea6.setText(TextoFull);
	}
	
	public void ImprimirConsolaProductor(String Texto) {
		String TextoFull;
		TextoFull = V1.jTextArea7.getText();
    	if (TextoFull.length() > 0) {TextoFull = TextoFull + "\n";}
    	TextoFull = TextoFull + Texto;
    	V1.jTextArea7.setText(TextoFull);
	}
	
	public void ImprimirConsolaReservas(String Texto) {
		String TextoFull;
		int Lineas = V1.jTextArea9.getLineStartOffset(0);
		int Temp = 45;
        System.out.println("AREA1: " + V1.jTextArea9.getLineStartOffset(1));
		TextoFull = V1.jTextArea9.getText();
    	if (TextoFull.length() > 0) {TextoFull = TextoFull + "\n";}
    	TextoFull = TextoFull + Texto;
    	V1.jTextArea9.setText(TextoFull);
        System.out.println("AREA2: " + V1.jTextArea9.getRows());
        for (int i=Lineas;i<=V1.jTextArea9.getLineCount();i++) {
        	 System.out.println("Hola");
        	V1.jTextArea9.insert("      ",Temp);
        	Temp +=45;
//        	V1.jTextArea9.setCaretPosition(10);
        }
	}
	
	public void LimpiarConsolaConsumidor() {
    	V1.jTextArea6.setText("");
	}
	
	public void LimpiarConsolaProductor() {
    	V1.jTextArea7.setText("");
	}
	
	public void LimpiarConsolaReservas() {
    	V1.jTextArea9.setText("");
	}
	
	public void ImprimirTituloProductor() {
		LimpiarConsolaProductor();
		ImprimirConsolaProductor(" Estado Del Productor De SMS");		
	}
	
	public void ImprimirDatosProductor() {
		ImprimirTituloProductor();
		if (Cola2.TamañoCola()>0) { ImprimirConsolaProductor(" Tamaño De La Cola De Salida: " + Cola2.TamañoCola()); }
		else { ImprimirConsolaProductor(" No Hay Mensajes SMS En La Cola De Salida "); }
	}
	
	public void ModificoMenuServidor1() {						// Desactivo Opción "Conectar" y Activo "Desconectar" 
	    V1.jMenuItem6.setEnabled(true);
    	V1.jMenuItem1.setEnabled(false);
	}
	
	public void DesactivoServidor(String Texto) {				// Desactivo Opción "Desconectar" y Activo "Conectar". Muestro Errores De Conexión En Consola
		if (Texto.contains("La Conexión Al Dispositivo GSM Falló")) {
			V1.jTextArea2.setText("La conexión al dispositivo GSM falló.\nVerifique si el dispositivo GSM se\nencuentra conectado y si los datos de\nconfiguración son correctos. Luego\n" +
			"reinicie la aplicación y vuelva a probar.");
		}
		else if (Texto.contains("El Puerto Serial Ya Se Encuentra Utilizado Por Otra Aplicación")) {
			V1.jTextArea2.setText("La conexión al dispositivo GSM falló.\nEl puerto serial ya se encuentra\nutilizado por otra aplicación.");
		}
	}
	
	public void ActualizoDatosTC(int Señal, int Bateria) {
		V1.jTextField8.setText(Integer.toString(Señal));
		V1.jTextField10.setText(Integer.toString(Bateria));
	}
	
	public void FijoDatosConexion(String DatoPuerto, String DatoVel) {
		Com = DatoPuerto;
		Vel = DatoVel;
		System.out.println("Inicio : Datos Conexión: " + Com + " BPS: " + Vel);
	}
	
	public void FijoConfigXML(boolean indice1, boolean indice2) {
		XMLin = indice1;
		XMLout = indice2;
		System.out.println("Inicio : Fijo XML In: " + indice1 + " Fijo XML Out: " + indice2);
	}
	
	public void FijoTiempoLecturas(String Tiempo1, String Tiempo2, String Tiempo3) {
		TiempoIH = Integer.parseInt(Tiempo1);
		TiempoConsu = Integer.parseInt(Tiempo2);
		TiempoContador = Integer.parseInt(Tiempo3);
	}
	
	public void FijoConfigLog(boolean indice1, boolean indice2) {
		GenerarLog = indice1;
		InfoProtocolo = indice2;	// InfoProtocolo = Información De Mensajes De Protocolo	
	}
	
//	Imprimo Los Datos De Las Reservas De La Lista De Reservas	
	
	public void ImprimirReserva(int indice[]) {
		LimpiarConsolaReservas();
		String Origen, Destino;
		for (int i=0;i<indice.length;i++) {
			Reserva = BD.BuscoReserva(ListaReservas.get(indice[i]));
			ImprimirConsolaReservas(" Número De Reserva: " + Reserva.ObtenerNroPedido());
			ImprimirConsolaReservas(" Número De Celular: " + Reserva.ObtenerNroCelular());
			if (Reserva.ObtenerCliente().compareTo("S") == 0) { ImprimirConsolaReservas(" Cliente (Si/No): Si"); }
			else { ImprimirConsolaReservas(" Cliente (Si/No): No"); } 
			ImprimirConsolaReservas(" Número Del Vuelo De Ida: " + Reserva.ObtenerVuelo1());
			ImprimirConsolaReservas(" Fecha De Salida: " + Reserva.ObtenerFechaSalidaVuelo1());
			if (Reserva.ObtenerFechaLlegadaVuelo1().compareTo(Reserva.ObtenerFechaSalidaVuelo1()) != 0) { ImprimirConsolaReservas("Fecha De Llegada: " + Reserva.ObtenerFechaLlegadaVuelo1()); }
			ImprimirConsolaReservas(" Hora De Salida: " + Reserva.ObtenerHorarioSalidaVuelo1() + " - Hora De Llegada: " + Reserva.ObtenerHorarioLlegadaVuelo1());
			Origen = BD.BuscoCiudadPorAeropuerto(Reserva.ObtenerAeropuertoOrigenVuelo1());
			Destino = BD.BuscoCiudadPorAeropuerto(Reserva.ObtenerAeropuertoDestinoVuelo1());
			ImprimirConsolaReservas(" Origen: " + Origen + " - Destino: " + Destino);
			ImprimirConsolaReservas(" Tipo De Avión: " + Reserva.ObtenerAvionVuelo1() + " - Asientos Libres: " + Reserva.ObtenerAsientosLibresVuelo1());
			ImprimirConsolaReservas(" Precio ($ Argentinos): " + Reserva.ObtenerPrecioVuelo1());
			ImprimirConsolaReservas(" Estado Del Vuelo: " + Reserva.ObtenerEstadoVuelo1());
			if (Reserva.ObtenerVuelo2().compareTo("-") != 0 ) {
				ImprimirConsolaReservas(" Número Del Vuelo De Retorno: " + Reserva.ObtenerVuelo2());
				ImprimirConsolaReservas(" Fecha De Salida: " + Reserva.ObtenerFechaSalidaVuelo2());
				if (Reserva.ObtenerFechaLlegadaVuelo2().compareTo(Reserva.ObtenerFechaSalidaVuelo2()) != 0) { ImprimirConsolaReservas("Fecha De Llegada: " + Reserva.ObtenerFechaLlegadaVuelo2()); }
				ImprimirConsolaReservas(" Hora De Salida: " + Reserva.ObtenerHorarioSalidaVuelo2() + " - Hora De Llegada: " + Reserva.ObtenerHorarioLlegadaVuelo2());
				Origen = BD.BuscoCiudadPorAeropuerto(Reserva.ObtenerAeropuertoOrigenVuelo2());
				Destino = BD.BuscoCiudadPorAeropuerto(Reserva.ObtenerAeropuertoDestinoVuelo2());
				ImprimirConsolaReservas(" Origen: " + Origen + " - Destino: " + Destino);
				ImprimirConsolaReservas(" Tipo De Avión: " + Reserva.ObtenerAvionVuelo2() + " - Asientos Libres: " + Reserva.ObtenerAsientosLibresVuelo2());
				ImprimirConsolaReservas(" Precio ($ Argentinos): " + Reserva.ObtenerPrecioVuelo2());
				ImprimirConsolaReservas(" Estado Del Vuelo: " + Reserva.ObtenerEstadoVuelo2());
			}
			ImprimirConsolaReservas(" Fecha De Creación De La Reserva: " + Reserva.ObtenerFechaCreacionReserva());
//			ImprimirConsolaReservas(" Fecha De Vencimiento De La Reserva: ");
		}
	}
	
	public void MostrarReserva() {
		LimpiarConsolaReservas();
		ImprimirConsolaReservas("NúmerocDezReserva:a12asdddddddddddddddddddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		ImprimirConsolaReservas("Número De Reserva: 12");
/*		ImprimirConsolaReservas("Número De Celular: +394318137");
		ImprimirConsolaReservas("Cliente (Si/No): Si");
		ImprimirConsolaReservas("Número Vuelo Ida: ");
		ImprimirConsolaReservas("Fecha De Salida: 15/10/2008");
		ImprimirConsolaReservas("Fecha De Llegada: 15/10/2008");
		ImprimirConsolaReservas("Hora De Salida: 5:40 PM - Hora De Llegada: 6:50 PM ");
		ImprimirConsolaReservas("Origen: Buenos Aires - Destino: Mar Del Plata");
		ImprimirConsolaReservas("Tipo De Avion: Mc Donell Douglas - Asientos Libres: 412");
		ImprimirConsolaReservas("Precio: $213");
		ImprimirConsolaReservas("Estado Del Vuelo: En Horario");
		ImprimirConsolaReservas("Número Vuelo Retorno: 23");
		ImprimirConsolaReservas("Fecha De Salida: 15/10/2008");
		ImprimirConsolaReservas("Fecha De Llegada: 15/10/2008");
		ImprimirConsolaReservas("Hora De Salida: 12:40 PM - Hora De Llegada: 12:50 PM ");
		ImprimirConsolaReservas("Origen: Posadas - Destino: Buenos Aires");
		ImprimirConsolaReservas("Precio: $213");
		ImprimirConsolaReservas("Tipo De Avion: Airbus A3201234 - Asientos Libres: 123");
		ImprimirConsolaReservas("Estado Del Vuelo: En Horario");
		ImprimirConsolaReservas("Fecha De Creación De La Reserva: ");*/
	}    
	
//	Creo Fecha Del Tipo 1 Para Mostrar En Pantalla ,Para El Log y Para La Base De Datos. Fecha Del Tipo 2 Para Los Nombres De Los Archivos De Logs.	
	
    public String CrearFecha(int TipoFecha, Date FechaDato) {					
    	String FechaTemp, FechaFinal;
    	FechaTemp = FechaFinal = "";
    	Date Fecha;
    	if (FechaDato == null) { Fecha = new Date(); }
    	else { Fecha = FechaDato; }  	
    	FechaTemp = Fecha.toString();
    	if (TipoFecha == 1) { FechaFinal = FechaTemp.substring(8, 10) + "/"; }
    	else { FechaFinal = FechaTemp.substring(32, 34) + "-"; }
    	if (FechaTemp.contains("Jan")) {FechaFinal += "01";}
    	else if (FechaTemp.contains("Feb")) {FechaFinal += "02";}
    	else if (FechaTemp.contains("Mar")) {FechaFinal += "03";}
    	else if (FechaTemp.contains("Apr")) {FechaFinal += "04";}
    	else if (FechaTemp.contains("May")) {FechaFinal += "05";}
    	else if (FechaTemp.contains("Jun")) {FechaFinal += "06";}
    	else if (FechaTemp.contains("Jul")) {FechaFinal += "07";}
    	else if (FechaTemp.contains("Aug")) {FechaFinal += "08";}
    	else if (FechaTemp.contains("Sep")) {FechaFinal += "09";}
    	else if (FechaTemp.contains("Oct")) {FechaFinal += "10";}
    	else if (FechaTemp.contains("Nov")) {FechaFinal += "11";}
    	else if (FechaTemp.contains("Dec")) {FechaFinal += "12";}
    	if (TipoFecha == 1) { FechaFinal += "/" + FechaTemp.substring(32, 34) + " " + FechaTemp.substring(11, 19); }
    	else {
    		FechaFinal += "-" + FechaTemp.substring(8, 10) + " " + FechaTemp.substring(11, 19); 
    		FechaFinal = FechaFinal.replace(":", "-");
    	}
    	return FechaFinal;
    }

//	Log    
    
	public void CreoLog() {
/*		String Directorio;
		Directorio = 
		File dir = new File("c:\\winnt");
		dir.*/
		try {
			MyLog = File.createTempFile("Servidor SMS - Log - " + CrearFecha(2,null) + " - ", ".txt", new File("C:\\Mensajes\\Logs\\"));
			Log = new PrintWriter(new FileWriter(MyLog.getCanonicalPath()));
			Log.println(CrearFecha(1,null) + " - Inicio De La Aplicación Versión 1.0");
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void EscriboLog(String S) {
		try { Log.println(CrearFecha(1,null) + " - " + S); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public void EscriboLogDatosSMS(CIncomingMessage SMS) {
		String Texto = CrearFecha(1,null) + " - SMS Recibido, Número de ID: ";
		try { 
			if (SMS.getId()<10) { Texto += "  "; }
			else if (SMS.getId()<100){ Texto += " "; }
			Texto += SMS.getId() + ", Indice de Memoria: ";
			if (SMS.getMemIndex()<10) { Texto += "  "; }
			else if (SMS.getMemIndex()<100){ Texto += " "; }
			Texto += SMS.getMemIndex() + ", Fecha De Recepción: " + CrearFecha(1, SMS.getDate());
			Log.println(Texto);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void EscriboLogDatosSMS(COutgoingMessage SMS) {
		String Texto = CrearFecha(1,null) + " - SMS Creado, Número de ID: ";
		Texto += SMS.getId();
		try { Log.println(Texto); } catch (Exception e) { e.printStackTrace(); }
	}
	
	public void EscriboLog(CProtocolMessage DatosIH) {
		String Texto;
		Texto = DatosIH.getText();
		if (DatosIH.getConexion()) {
			try { 
				if (!DatosIH.getText().contains("Información")) { Log.println(CrearFecha(1,null) + " - " + Texto); }
				if (Señal != DatosIH.getSeñal() || Bateria != DatosIH.getBateria()) {
					Señal = DatosIH.getSeñal();
					Bateria = DatosIH.getBateria();	
					Log.println(CrearFecha(1,null) + " - Información Del Dispositivo GSM - Intensidad De La Señal: " + Señal + ", Nivel De Batería: " + Bateria);
				}
			}
			catch (Exception e) { e.printStackTrace(); }
		}
		else {
			try { Log.println(CrearFecha(1,null) + " - " + Texto); } catch (Exception e) { e.printStackTrace(); }
		}
	}
	 
	public void CierroLog() {
		try {
			Log.println(CrearFecha(1,null) + " - Finaliza Ejecución De La Aplicación");
			Log.close();
			if (!GenerarLog) {
				if (MyLog.delete()) { System.out.println("Inicio : Borro Archivo De Log"); }
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
//	XML
	
	public void CreoArchivoXML(CProtocolMessage msg) {
		File xmlFile;
		PrintWriter out;
		System.out.println("Inicio : Creo Archivo XML De Mensaje Protocolo");
		try {
			xmlFile = File.createTempFile("DATOS_IH - " + CrearFecha(2,null) + " - ", ".xml", new File("C:\\Mensajes\\Datos De La Interfaz De Hardware\\"));
			out = new PrintWriter(new FileWriter(xmlFile.getCanonicalPath()));
			out.println("<?xml version='1.0' encoding='iso-8859-7'?>");
			out.println("<Mensaje>");
			out.println("	<Originador> " + msg.getOriginator() + " </Originador>");
			out.println("	<Fecha> " + CrearFecha(1,msg.getDate()) + " </Fecha>");
			out.println("	<ID> " + msg.getId() + " </ID>");
			out.println("	<Texto> " + msg.getText() + " </Texto>");
			out.println("	<SMSLeidos> " + msg.getSMSLeidos() + " </SMSLeidos>");
			out.println("	<NroLectura> " + msg.getNroLectura() + " </NroLectura>");		
			out.println("	<SMSEnviados> " + msg.getSMSEnviados() + " </SMSEnviados>");
			out.println("</Mensaje>");	
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CreoArchivoXML(CIncomingMessage msg) {
		File xmlFile;
		PrintWriter out;
		System.out.println("Inicio : Creo XML DE SMS Entrante");
		try {
			xmlFile = File.createTempFile("SMS_IN - " + msg.getId() + " - " + CrearFecha(2,null) + " - ", ".xml", new File("C:\\Mensajes\\SMS Entrantes\\"));
			out = new PrintWriter(new FileWriter(xmlFile.getCanonicalPath()));
			out.println("<?xml version='1.0' encoding='iso-8859-7'?>");
			out.println("<Mensaje>");
			out.println("	<Originador> " + msg.getOriginator() + " </Originador>");
			out.println("	<Fecha> " + CrearFecha(1,msg.getDate()) + " </Fecha>");
			out.println("	<ID> " + msg.getId() + " </ID>");
			out.println("	<Indice De Memoria> " + msg.getMemIndex() + " </Indice De Memoria>");
			out.println("	<Texto> <![CDATA[" + msg.getText() + "]]> </Texto>");
			out.println("</Mensaje>");	
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CreoArchivoXML(COutgoingMessage msg) {
		File xmlFile;
		PrintWriter out;
		System.out.println("Inicio : Creo XML DE SMS Saliente");
		try {
			xmlFile = File.createTempFile("SMS_OUT - " + msg.getId() + " - " + CrearFecha(2,null) + " - ", ".xml", new File("C:\\Mensajes\\SMS Salientes\\"));
			out = new PrintWriter(new FileWriter(xmlFile.getCanonicalPath()));
			out.println("<?xml version='1.0' encoding='iso-8859-7'?>");
			out.println("<Mensaje>");
			out.println("	<Originador>" + "Servidor SMS 1.0" + "</Originador>");
			out.println("	<Destino>" + msg.getRecipient() + "</Destino>");
			out.println("	<Fecha>" + CrearFecha(1,msg.getDate()) + "</Fecha>");
			out.println("	<ID>" + msg.getId() + "</ID>");
			out.println("	<Texto> <![CDATA[" + msg.getText() + "]]> </Texto>");
			out.println("</Mensaje>");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	




