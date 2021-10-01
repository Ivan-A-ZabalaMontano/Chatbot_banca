package bo.edu.ucb.est;



import java.util.ArrayList;

import bo.edu.ucb.est.utils.Mensaje;

public class UserInteraction {
	private String received;
	private ArrayList<String> send;
	private Mensaje message;
	

	public UserInteraction()
	{
		this.received="";
		this.send= new ArrayList<String>();
		this.message= new Mensaje();
	}
	
	//Getters
	public String getReceived()
	{
		return this.received;
	}
	public Mensaje getMessage()
	{
		return this.message;
	}
	public ArrayList<String> getSend()
	{
		return this.send;
	}
	//Setters
	public void setReceived(String lastMSG)
	{
		this.received=lastMSG;
	}
	public void setMessage(Mensaje message)
	{
		this.message=message;
	}
	public void addSend(String send)
	{
		this.send.add(send);
	}
	public void clearSend()
	{
		this.send.clear();
	}
}
