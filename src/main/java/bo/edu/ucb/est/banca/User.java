package bo.edu.ucb.est.banca;

import java.util.ArrayList;

import bo.edu.ucb.est.UserInteraction;

public class User {
	private String status;
	private String name;
	private String pin;
	private ArrayList<Cuenta> accounts;

	private UserInteraction interaction;
	
	
	public User() 
	{
		this.status="Nuevo";
		this.name="";
		this.pin="";
		this.accounts=new ArrayList<Cuenta>();
		this.interaction= new UserInteraction();
	}
	//Getters
	public String getStatus()
	{
		return this.status;
	}
	public String getName()
	{
		return this.name;
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
	public void setName(String nombre)
	{
		this.name=nombre;
	}
	public void setPin(String pin)
	{
		this.pin=pin;
	}
	//Methods
	public void addAccount(Cuenta account)
	{
		accounts.add(account);
	}
	
	
}
