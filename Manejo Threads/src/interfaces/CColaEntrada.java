package interfaces;

import java.util.*;

public class CColaEntrada {

	CColaEntrada() {};

	private Object _SYNC_ = new Object();
	LinkedList<CMessage> ColaEnt = new LinkedList<CMessage>();

	public CMessage Temporal;

	public void Agregar(CMessage Mensaje) {
		synchronized (_SYNC_) {	ColaEnt.add(Mensaje); }
	}

	public CMessage Obtener(int i) {
		synchronized (_SYNC_)
		{
			Temporal = ColaEnt.get(i);
			return Temporal;
		}
	}

	public CMessage Sacar() {
		synchronized (_SYNC_)
		{
			Temporal = ColaEnt.remove();
			return Temporal;
		}
	}

	public void Limpiar() {
		synchronized (_SYNC_) { ColaEnt.clear(); }
	}

	public int TamañoCola() {
		synchronized (_SYNC_) { return ColaEnt.size(); }
	}

	public CMessage UltimoCola() {
		synchronized (_SYNC_) { return ColaEnt.getLast(); }
	}

	public boolean EstaVacio() {
		synchronized (_SYNC_) { return ColaEnt.isEmpty(); }
	}
}
