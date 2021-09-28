package bo.edu.ucb.est;

import java.util.HashMap;

import bo.edu.ucb.est.banca.Cuenta;
import bo.edu.ucb.est.utils.Mensaje;

public class Usuario {
	private String status;
	private String nombre;
	private String pin;
	private int nroCuenta;
	private HashMap<Integer,Cuenta> cuentas;
	private String lastMSG;
	private Mensaje mensaje;
	public Usuario() 
	{
		this.status="Nuevo";
		this.nombre="";
		this.pin="";
		lastMSG="";
		
		this.nroCuenta=0;
		this.mensaje= new Mensaje();
	}
	public String getStatus()
	{
		return this.status;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public String getPin()
	{
		return this.pin;
	}
	public String getLastMSG()
	{
		return this.lastMSG;
	}
	public void agregarCuenta(Cuenta cuenta)
	{
		if(nroCuenta==0)
		{
			this.nroCuenta=1;
		}
		else
		{
			this.nroCuenta++;
		}
		cuentas.put(nroCuenta, cuenta);
	}
	public int getNroCuenta()
	{
		return this.nroCuenta;
	}
	public Cuenta getCuenta(int n)
	{
		return this.cuentas.get(n);
	}
	public Mensaje getMensaje()
	{
		return this.mensaje;
	}
	public void setMensaje(Mensaje mensaje) 
	{
		this.mensaje=mensaje;
	}
	public void setLastMSG(String lastMSG)
	{
		this.lastMSG=lastMSG;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public void setPin(String pin)
	{
		this.pin=pin;
	}
	
}
