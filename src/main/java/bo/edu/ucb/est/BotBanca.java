package bo.edu.ucb.est;

import java.util.ArrayList;
import java.util.HashMap;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import bo.edu.ucb.est.banca.User;
import bo.edu.ucb.est.utils.Message;

public class BotBanca extends TelegramLongPollingBot{

	
	private HashMap<Long,User> usuarios;

	
	public BotBanca()
	{
		usuarios= new HashMap<Long,User>();
	}
	public User verificarUsuario(Long id)
	{
		if(usuarios.containsKey(id))
		{
			System.out.println("Usuario encontrado");
		}
		else
		{
			usuarios.put(id, new User());
			System.out.println("Usuario agregado");
		}
		return usuarios.get(id);
	}

	
	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		System.out.println("Nuevo mensaje");
		if(update.hasMessage())
		{
			 // Creo el objeto para enviar un mensaje
            SendMessage message = new SendMessage();
            //Define a quien le vamos a enviar el mensaje
            message.setChatId(update.getMessage().getChatId().toString());
            //Obtiene el id del chat del usuario
            Long userID=update.getMessage().getChatId();
            // Obtiene el mensaje del usuario
            String texto=update.getMessage().getText();
       
            User user=verificarUsuario(userID);
            sendMessage(message,user,texto);
            
		}
	}
	
	public void sendMessage(SendMessage message, User user,String texto)
	{
		 BotInteraction answer= new BotInteraction(user);
		 answer.receiveAnswer(texto);
		 Message mensaje= answer.getMensaje();
		 int n= mensaje.getKey();
		 
		 for(int i=1;i<n;i++)
		 {
			 String m= mensaje.getMensajes().get(i);
			 message.setText(m);
			 try
			 {
				 execute(message);
			 }catch(Exception e)
			 {
				 e.printStackTrace();
			 }
		 }
	}
	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "";
	}

}
