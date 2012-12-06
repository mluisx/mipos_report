package interfaces;

public class CContacto {

	public String Nombre;
	public String NumeroCel;
	public String TipoCliente;
	public String CiudadRes;
	
	public CContacto(String Nombre, String NumeroCel, String TipoCliente, String CiudadRes) {
		
		this.Nombre = Nombre;
		this.NumeroCel = NumeroCel;
		this.TipoCliente = TipoCliente;
		this.CiudadRes = CiudadRes;

	}
	
	public String ObtenerNombre() {return Nombre;}
	public String ObtenerTipoCliente() {return TipoCliente;}
	public String ObtenerNumeroCel() {return NumeroCel;}
	public String ObtenerCiudadRes() {return CiudadRes;}
	
	public void FijarNombre(String A1) {Nombre = A1;}
	public void FijarTipoCliente(String A1) {TipoCliente = A1;}
	public void FijarNumeroCel(String A1) {NumeroCel = A1;}
	public void FijarCiudadRes(String A1) {CiudadRes = A1;}

}
