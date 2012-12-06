package interfaces;

import java.util.*;

public class CProtocolMessage extends CMessage {
	/**
		This class represents a Protocol Message Between The InterfazHWVer3 & Consumidor
	*/
	public static final int CLASS_ALL = 0;	
	public static final int CLASS_REC_UNREAD = 1;
	public static final int CLASS_REC_READ = 2;
	public static final int CLASS_STO_UNSENT = 3;
	public static final int CLASS_STO_SENT = 4;
	private int NroLectura, SMSLeidos, SMSEnviados, SMSGrupales, Señal, Bateria;
	private boolean Conexion;

	//agregar estado de interfaz (MODIF)

	public CProtocolMessage(Date date, String originator, String text, int memindex, int superid, int NroLectura, int SMSLeidos, int SMSEnviados, int SMSGrupales, int Señal, int Bateria,
	boolean Conexion) {
		super(TYPE_PROTOCOL, date, originator, null, text, memindex , superid);
		this.NroLectura  = NroLectura;
		this.SMSLeidos   = SMSLeidos;
		this.SMSEnviados = SMSEnviados;
		this.SMSGrupales = SMSGrupales;
		this.Señal = Señal;
		this.Bateria = Bateria;
		this.Conexion = Conexion;
	}

	public void setOriginator(String originator) { this.originator = originator; }
	
	public String getOriginator() { return originator; }
	public int getSMSLeidos() { return SMSLeidos; }
	public int getSMSEnviados() { return SMSEnviados; }
	public int getSMSGrupales() { return SMSGrupales; }
	public int getNroLectura() { return NroLectura; }
	public int getSeñal() { return Señal; }
	public int getBateria() { return Bateria; }
	public boolean getConexion() { return Conexion;}
	
}


