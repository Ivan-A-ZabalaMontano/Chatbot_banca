package bo.edu.ucb.est.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Message {
	
	private HashMap<Integer,String> messages;
	private HashMap<String,String> properties;
	
	private int key;

	public Message()
	{
		this.key=1;
		this.messages= new HashMap<Integer, String>();
		this.properties= new HashMap<String, String>();
	}

	public int getKey()
	{
		return this.key;
	}
	
		
	public HashMap<String, String> getProperties() {
		return properties;
	}
	public HashMap<Integer, String> getMensajes() {
		return messages;
	}
	public void addMessage(String mensaje)
	{
		messages.put(key, mensaje);
		key++;
	}
	public void addMessage(String mensaje, String input)
	{
		messages.put(key, mensaje);
		properties.put(mensaje, input);
		key++;
	}
	public int verifyMessage(String key, String texto)
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
