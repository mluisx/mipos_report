package interfaces;

public class CDatos {
	
	String TipoTicket, CiudadOrigen, CiudadDestino, FechaIda, FechaRetorno, FechaIdaDia, Cel;
	String FechaIdaMes, FechaIdaAnio, FechaRetornoDia, FechaRetornoMes, FechaRetornoAnio, Reserva, Reserva2, EstadoVuelo;
	int TipoDato, Vuelo1, Vuelo2, Vuelo3, CodigoReserva, IDSMS; 

	//   Tipo de Datos Recibidos al Servidor
	//   TipoDato 1 = Datos completos pedido de vuelos sin fecha de retorno
	//   TipoDato 2 = Datos completos pedido de vuelos con fecha de retorno
	//   TipoDato 3 = Datos incompletos - Mensaje erroneo
	//   TipoDato 4 = Pedido de ayuda de adquisicion de vuelos
	//   TipoDato 5 = Confirmación reserva de vuelo
	//   TipoDato 6 = Pedido de mas vuelos disponibles
	//   TipoDato 7 = Pedido de estado de vuelo
	//   Tipo de Datos Enviados Por el Servidor
	//   0  = No se envio Nada
	//   10 = Se envio Vuelos (Respuesta al TipoDato 1 o 2) 
	//	 11 = Se envio Mas Vuelos (Respuesta al TipoDato 6)
	// 	 12 = Se envio Estado de Vuelo (Respuesta al TipoDato 7)
	//   13 = Se envio Help (Respuesta TipoDato 4)
	//	 14 = Se envio Confirmacion de Reserva (Respuesta TipoDato 5)
	
	public CDatos() {
		TipoTicket = CiudadOrigen = CiudadDestino = FechaIdaDia = FechaIdaMes = "";
		FechaIdaAnio = FechaRetornoDia = FechaRetornoMes = FechaRetornoAnio = "";
		FechaIda = FechaRetorno = Cel = Reserva = Reserva2 = EstadoVuelo = "";
		TipoDato = Vuelo1 = Vuelo2 = Vuelo3 = CodigoReserva = IDSMS = 0;
	}
	
	public void Limpiar() {
		TipoTicket = CiudadOrigen = CiudadDestino = FechaIdaDia = FechaIdaMes = "";
		FechaIdaAnio = FechaRetornoDia = FechaRetornoMes = FechaRetornoAnio = "";
		FechaIda = FechaRetorno = Cel = Reserva = Reserva2 = EstadoVuelo = "";
		TipoDato = Vuelo1 = Vuelo2 = Vuelo3 = CodigoReserva = IDSMS = 0;
	}
	
	public void FijoTipoTicket(String A1) {	TipoTicket = A1; }
	public void FijoCiudadOrigen(String A1) { CiudadOrigen = A1; }
	public void FijoCiudadDestino(String A1) { CiudadDestino = A1; }
	public void FijoFechaIda(String A1) { FechaIda = A1; }
	public void FijoFechaRetorno(String A1) { FechaRetorno = A1; }
	public void FijoNroCelOrigen(String A1) { Cel = A1; }
	public void FijoReserva(String A1) { Reserva = A1; }
	public void FijoReserva2(String A1) { Reserva2 = A1; }
	public void FijoNroVuelo(String A1) { EstadoVuelo = A1; }
	public void FijoTipoDato(int TD) { TipoDato = TD; }
	public void FijoVuelo1(int A1) { Vuelo1 = A1; } // Almaceno los vuelos ofrecidos
	public void FijoVuelo2(int A1) { Vuelo2 = A1; } // Almaceno los vuelos ofrecidos
	public void FijoVuelo3(int A1) { Vuelo3 = A1; } // Almaceno los vuelos ofrecidos
	public void FijoCodigoReserva(int A1) { CodigoReserva = A1; } // Almaceno codigo de la reserva
	public void FijoIDSMS(int A1) { IDSMS = A1; } // Almaceno El ID Del SMS
/*	public void FijoFechaIdaDia(String A1) { FechaIdaDia = A1; }
	public void FijoFechaIdaMes(String A1) { FechaIdaMes = A1; }
	public void FijoFechaIdaAnio(String A1) { FechaIdaAnio = A1; }
	public void FijoFechaRetornoDia(String A1) { FechaRetornoDia = A1; }
	public void FijoFechaRetornoMes(String A1) { FechaRetornoMes = A1; }
	public void FijoFechaRetornoAnio(String A1) { FechaRetornoAnio = A1; } */

    public String ObtenerTipoTicket() { return TipoTicket; }
    public String ObtenerCiudadOrigen() { return CiudadOrigen; }
    public String ObtenerCiudadDestino() { return CiudadDestino; }
    public String ObtenerFechaIda() { return FechaIda; }
    public String ObtenerFechaRetorno() { return FechaRetorno; }
    public String ObtenerNroCelOrigen() { return Cel; }
    public String ObtenerReserva() { return Reserva; }
    public String ObtenerReserva2() { return Reserva2; }
    public String ObtenerNroVuelo() { return EstadoVuelo; }
    public int ObtenerTipoDato() { return TipoDato; }
    public int ObtenerVuelo1() { return Vuelo1; }
    public int ObtenerVuelo2() { return Vuelo2; }
    public int ObtenerVuelo3() { return Vuelo3; }
	public int ObtenerCodigoReserva() { return CodigoReserva; }
	public int ObtenerIDSMS() { return IDSMS; }
/*  public String ObtenerFechaIdaDia() { return FechaIdaDia; }
    public String ObtenerFechaIdaMes() { return FechaIdaMes; }
    public String ObtenerFechaIdaAnio() { return FechaIdaAnio; }
    public String ObtenerFechaRetornoDia() { return FechaRetornoDia; }
    public String ObtenerFechaRetornoMes() { return FechaRetornoMes; }
    public String ObtenerFechaRetornoAnio() { return FechaRetornoAnio; } */

}

