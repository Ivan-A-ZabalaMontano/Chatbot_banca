package bo.edu.ucb.est.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Mensaje {
	
	private HashMap<Integer,String> mensajes;
	private HashMap<String,String> properties;
	
	private int key;
	private String status;
	public Mensaje()
	{
		this.key=1;
		this.mensajes= new HashMap<Integer, String>();
		this.properties= new HashMap<String, String>();
	}
	public String getStatus()
	{
		return this.status;
	}
	public int getKey()
	{
		return this.key;
	}
	
		
	public HashMap<String, String> getProperties() {
		return properties;
	}
	public HashMap<Integer, String> getMensajes() {
		return mensajes;
	}
	public void agregarMensaje(String mensaje)
	{
		mensajes.put(key, mensaje);
		key++;
	}
	public void agregarMensaje(String mensaje, String input)
	{
		mensajes.put(key, mensaje);
		properties.put(mensaje, input);
		key++;
	}
	public int validarMensaje(String key, String texto)
	{
		String property=properties.get(key);
		
		if(property.equals("String"))
		{
			return 0;
		}
		else
		{
			int n;
			try
			{
				n=Integer.parseInt(texto);
			}catch(Exception e)
			{
				return -1;
			}
			return n;
		}
	}
}
