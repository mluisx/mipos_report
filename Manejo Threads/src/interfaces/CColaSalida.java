package interfaces;

import java.util.LinkedList;

public class CColaSalida {
	
	CColaSalida(){};
	private Object _SYNC_ = new Object();
	LinkedList<COutgoingMessage> ColaSal = new LinkedList<COutgoingMessage>();
	public COutgoingMessage Temporal;
	
	public void Agregar(COutgoingMessage Mensaje) {
		synchronized (_SYNC_) {	ColaSal.add(Mensaje); }
		}
	
	public COutgoingMessage Obtener(int i) {
		synchronized (_SYNC_) {
			Temporal = ColaSal.get(i);
			return Temporal;
		}
	}
	
	public COutgoingMessage Sacar() {
		synchronized (_SYNC_) {	
			Temporal = ColaSal.remove();
			return Temporal;
		}
	}
	
	public void Limpiar() {
		synchronized (_SYNC_) {	ColaSal.clear(); }
	}
	
	public int TamañoCola() {
		synchronized (_SYNC_) {	return ColaSal.size(); }
	}

	public COutgoingMessage UltimoCola() {
		synchronized (_SYNC_) {	return ColaSal.getLast(); }
	}
	
	public boolean EstaVacio() {
		synchronized (_SYNC_) {	return ColaSal.isEmpty(); }
	}
}
