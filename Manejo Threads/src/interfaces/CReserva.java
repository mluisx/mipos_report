package interfaces;

public class CReserva {

	public String NroCelular,Vuelo1,Vuelo2,Cliente, FechaCreacion;
	public String HorarioSalidaVuelo1,HorarioLlegadaVuelo1,FechaSalidaVuelo1,FechaLlegadaVuelo1,AeropuertoOrigenVuelo1,AeropuertoDestinoVuelo1,EstadoVuelo1,AvionVuelo1;
	public String HorarioSalidaVuelo2,HorarioLlegadaVuelo2,FechaSalidaVuelo2,FechaLlegadaVuelo2,AeropuertoOrigenVuelo2,AeropuertoDestinoVuelo2,EstadoVuelo2,AvionVuelo2;
	public int NroPedido,AsientosLibresVuelo1,AsientosLibresVuelo2,PrecioVuelo1,PrecioVuelo2;
	
	public CReserva() {
		NroCelular = Vuelo1 = Vuelo2 = Cliente = FechaCreacion = "";
		HorarioSalidaVuelo1 = HorarioLlegadaVuelo1 = FechaSalidaVuelo1 = FechaLlegadaVuelo1 = AeropuertoOrigenVuelo1 = AeropuertoDestinoVuelo1 = EstadoVuelo1 = AvionVuelo1 = "";
		HorarioSalidaVuelo2 = HorarioLlegadaVuelo2 = FechaSalidaVuelo2 = FechaLlegadaVuelo2 = AeropuertoOrigenVuelo2 = AeropuertoDestinoVuelo2 = EstadoVuelo2 = AvionVuelo2 = "";
		NroPedido = AsientosLibresVuelo1 = AsientosLibresVuelo2 = PrecioVuelo1 = PrecioVuelo2 = 0;
	}

	public void Limpiar() {
		NroCelular = Vuelo1 = Vuelo2 = Cliente = FechaCreacion = "";
		HorarioSalidaVuelo1 = HorarioLlegadaVuelo1 = FechaSalidaVuelo1 = FechaLlegadaVuelo1 = AeropuertoOrigenVuelo1 = AeropuertoDestinoVuelo1 = EstadoVuelo1 = AvionVuelo1 = "";
		HorarioSalidaVuelo2 = HorarioLlegadaVuelo2 = FechaSalidaVuelo2 = FechaLlegadaVuelo2 = AeropuertoOrigenVuelo2 = AeropuertoDestinoVuelo2 = EstadoVuelo2 = AvionVuelo2 = "";
		NroPedido = AsientosLibresVuelo1 = AsientosLibresVuelo2 = PrecioVuelo1 = PrecioVuelo2 = 0;
	}
	
	public void FijoNroCelular(String A1) { NroCelular = A1; }
	public void FijoVuelo1(String A1) { Vuelo1 = A1; }
	public void FijoVuelo2(String A1) { Vuelo2 = A1; }
	public void FijoCliente(String A1) { Cliente = A1; }
	public void FijoHorarioSalidaVuelo1(String A1) { HorarioSalidaVuelo1 = A1; }
	public void FijoHorarioLlegadaVuelo1(String A1) { HorarioLlegadaVuelo1 = A1; }
	public void FijoFechaSalidaVuelo1(String A1) { FechaSalidaVuelo1 = A1; }
	public void FijoFechaLlegadaVuelo1(String A1) { FechaLlegadaVuelo1 = A1; }
	public void FijoAeropuertoOrigenVuelo1(String A1) { AeropuertoOrigenVuelo1 = A1; }
	public void FijoAeropuertoDestinoVuelo1(String A1) { AeropuertoDestinoVuelo1 = A1; }
	public void FijoEstadoVuelo1(String A1) { EstadoVuelo1 = A1; }
	public void FijoAvionVuelo1(String A1) { AvionVuelo1 = A1; }
	public void FijoHorarioSalidaVuelo2(String A1) { HorarioSalidaVuelo2 = A1; }
	public void FijoHorarioLlegadaVuelo2(String A1) { HorarioLlegadaVuelo2 = A1; }
	public void FijoFechaSalidaVuelo2(String A1) { FechaSalidaVuelo2 = A1; }
	public void FijoFechaLlegadaVuelo2(String A1) { FechaLlegadaVuelo2 = A1; }
	public void FijoAeropuertoOrigenVuelo2(String A1) { AeropuertoOrigenVuelo2 = A1; }
	public void FijoAeropuertoDestinoVuelo2(String A1) { AeropuertoDestinoVuelo2 = A1; }
	public void FijoEstadoVuelo2(String A1) { EstadoVuelo2 = A1; }
	public void FijoAvionVuelo2(String A1) { AvionVuelo2 = A1; }
	public void FijoFechaCreacionReserva(String A1) { FechaCreacion = A1; }
	public void FijoNroPedido(int A1) { NroPedido = A1; }
	public void FijoAsientosLibresVuelo1(int A1) { AsientosLibresVuelo1 = A1; }
	public void FijoAsientosLibresVuelo2(int A1) { AsientosLibresVuelo2 = A1; }
	public void FijoPrecioVuelo1(int A1) { PrecioVuelo1 = A1; }
	public void FijoPrecioVuelo2(int A1) { PrecioVuelo2 = A1; }

	
	public String ObtenerNroCelular() {return NroCelular;}
	public String ObtenerVuelo1() {return Vuelo1;}
	public String ObtenerVuelo2() {return Vuelo2;}
	public String ObtenerCliente() {return Cliente;}
	public String ObtenerHorarioSalidaVuelo1() { return HorarioSalidaVuelo1; }
	public String ObtenerHorarioLlegadaVuelo1() { return HorarioLlegadaVuelo1; }
	public String ObtenerFechaSalidaVuelo1() { return FechaSalidaVuelo1; }
	public String ObtenerFechaLlegadaVuelo1() { return FechaLlegadaVuelo1; }
	public String ObtenerAeropuertoOrigenVuelo1() { return AeropuertoOrigenVuelo1; }
	public String ObtenerAeropuertoDestinoVuelo1() { return AeropuertoDestinoVuelo1; }
	public String ObtenerEstadoVuelo1() { return EstadoVuelo1; }
	public String ObtenerAvionVuelo1() { return AvionVuelo1; }
	public String ObtenerHorarioSalidaVuelo2() { return HorarioSalidaVuelo2; }
	public String ObtenerHorarioLlegadaVuelo2() { return HorarioLlegadaVuelo2; }
	public String ObtenerFechaSalidaVuelo2() { return FechaSalidaVuelo2; }
	public String ObtenerFechaLlegadaVuelo2() { return FechaLlegadaVuelo2; }
	public String ObtenerAeropuertoOrigenVuelo2() { return AeropuertoOrigenVuelo2; }
	public String ObtenerAeropuertoDestinoVuelo2() { return AeropuertoDestinoVuelo2; }
	public String ObtenerEstadoVuelo2() { return EstadoVuelo2; }
	public String ObtenerAvionVuelo2() { return AvionVuelo2; }
	public String ObtenerFechaCreacionReserva() { return FechaCreacion; }
	public int ObtenerNroPedido() { return NroPedido; }
	public int ObtenerAsientosLibresVuelo1() { return AsientosLibresVuelo1; }
	public int ObtenerAsientosLibresVuelo2() { return AsientosLibresVuelo2; }
	public int ObtenerPrecioVuelo1() { return PrecioVuelo1; }
	public int ObtenerPrecioVuelo2() { return PrecioVuelo2; }
	
}

