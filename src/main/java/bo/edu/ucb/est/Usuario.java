package bo.edu.ucb.est;

import java.util.ArrayList;

import bo.edu.ucb.est.banca.Cuenta;
import bo.edu.ucb.est.utils.Mensaje;

public class Usuario {
	private String status;
	private String nombre;
	private String pin;
	private ArrayList<Cuenta> accounts;

	private UserInteraction interaction;
	
	
	public Usuario() 
	{
		this.status="Nuevo";
		this.nombre="";
		this.pin="";
		this.accounts=new ArrayList<Cuenta>();
		this.interaction= new UserInteraction();
	}
	//Getters
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
	public ArrayList<Cuenta> getAccounts()
	{
		return this.accounts;
	}
	public UserInteraction getInteraction()
	{
		return this.interaction;
	}
	//Setters
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
	//Methods
	public void agregarCuenta(Cuenta account)
	{
		accounts.add(account);
	}
	
	
}
