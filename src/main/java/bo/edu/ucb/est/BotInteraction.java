package bo.edu.ucb.est;

import java.util.Random;

import bo.edu.ucb.est.banca.Cuenta;
import bo.edu.ucb.est.banca.User;
import bo.edu.ucb.est.utils.Message;

public class BotInteraction {
	
	private Message message;
	
	private User user;
	private  UserInteraction interaction;
	public BotInteraction(User user)
	{
		this.user=user;
		message= new Message();
		this.interaction=user.getInteraction();
	}
	
	public void receiveAnswer(String input)
	{
		Message message=interaction.getMessage();
		String send=interaction.getReceived();
		switch(user.getStatus())
		{
		case "Nuevo":
			if(user.getName().equals("") && send.equals(""))
			{
				System.out.println("Lugar nombre");
				defineRegisterName();
			}
			else 
			{
				if(!send.equals("") && message.verifyMessage(send, input)!=-1 && user.getName().equals(""))
				{
					System.out.println("Lugar Pin");
					user.setName(input);
				}
				else if(message.verifyMessage(send, input)!=-1)
				{
					if(input.length()==4)
					{
						user.setPin(input);
						System.out.println("Registrado correctamente");
					}
				}
				defineRegisterPin();
			}

		;break;
		case "Registrado":
			if(message.verifyMessage(send, input)!=-1 && send.contains("Solo por seguridad"))
			{
				if(user.getPin().equals(input))
				{
					defineMenu();
				}
				else
				{
					defineWelcome();
				}
			}
			else if(send.contains("Elige"))
			{
				if(input.equals("1") || input.equals("2") || input.equals("3"))
				{
					if(user.getAccounts().size()==0)
					{
						defineWithoutAccount();
					}
					else
					{
						user.getInteraction().getSend().add(0, input);
						defineShowAccounts();
					}
				}
				else if(input.equals("4"))
				{
					defineSelectCoin();
				}
				else if(input.equals("5"))
				{
					defineWelcome();
				}
				else
				{
					defineMenu();
				}
			}
			else if(send.contains("moneda"))
			{
				int option=message.verifyMessage(send, input);
				if(send.contains(input) &&  message.verifyMessage(send,input)!=-1)
				{
					if(option==2) user.getInteraction().addSend("Bolivianos");
					else if(option==1) user.getInteraction().addSend("Dólares");
					defineSelectType();
					
				}
				else
				{
					this.message.addMessage("Opcion incorrecta");
					defineSelectCoin();
				}		
			}
			else if(send.contains("tipo"))
			{
				int option=message.verifyMessage(send, input);
				if(send.contains(input) &&  message.verifyMessage(send,input)!=-1)
				{
					if(option==1)user.getInteraction().addSend("Cuenta Corriente");
					else if(option==2)user.getInteraction().addSend("Caja de Ahorros");
					defineAccountCreated();
					
				}
				else
				{
					this.message.addMessage("Opcion incorrecta");
					defineSelectType();
				}
			}
			else if(send.contains("Seleccione una Cuenta"))
			{
				System.out.println("Selecciono una cuenta: ");
				if(send.contains(input) && message.verifyMessage(send, input)!=-1 
						&& message.verifyMessage(send,input)>0 
						&& message.verifyMessage(send, input)<=user.getAccounts().size())
				{
					System.out.println("Opcion: "+user.getInteraction().getSend().get(0));
					user.getInteraction().addSend(input);
					if(user.getInteraction().getSend().get(0).equals("1"))
					{
						System.out.println("Ver cuenta");
						defineShowInfo();
						user.getInteraction().clearSend();
					}
					else if(user.getInteraction().getSend().get(0).equals("2")) defineWithdraw();
					else if(user.getInteraction().getSend().get(0).equals("3")) defineDeposit();
					
					
				}else
				{
					defineShowAccounts();
				}
			}
			else if(send.contains("monto"))
			{
				try
				{
					double n=Double.parseDouble(input);
					
					int n2= Integer.parseInt(user.getInteraction().getSend().get(1));
					System.out.println("Valor incial: "+n2);
					n2-=1;
					System.out.println("Valor final: "+n2);
					if(send.contains("retirar"))
					{
						if(user.getAccounts().get(n2).retirar(n))
						{
							this.message.addMessage("Retiro exitoso");
						}
						else
						{
							this.message.addMessage("Monto inválido");
						}
					}
					else
					{
						if(user.getAccounts().get(n2).depositar(n))
						{
							this.message.addMessage("Depósito exitoso");
						}
						else
						{
							this.message.addMessage("Monto inválido");
						}
						
					}
					defineMenu();
					user.getInteraction().clearSend();
				}catch(Exception e)
				{
					System.out.println("El usuario envio un monto incorrecto");
					this.message.addMessage("Monto inválido");
					defineMenu();
					user.getInteraction().clearSend();
				}
			}
			else
			{
				defineWelcome();
			}
		;break;
		}
		user.getInteraction().setReceived(this.message.getMensajes().get(this.message.getKey()-1));
		user.getInteraction().setMessage(this.message);
		System.out.println("Nombre: "+user.getName());
		System.out.println("Pin: "+user.getPin());
		System.out.println("Ultimo mensaje recibido: "+interaction.getReceived());
		System.out.println("Mensaje: "+interaction.getMessage().getProperties());
	}
	public BotInteraction getAnswer()
	{
		return this;
	}
	public Message getMensaje()
	{
		return this.message;
	}
	public void defineRegisterName()
	{
		message.addMessage("Bienvenido al Banco de los Dioses");
		message.addMessage("He notado que aún no eres cliente, procedamos a registrarte.");
		message.addMessage("¿Cúal es tu nombre completo?","String");
	}
	public void defineWithoutAccount()
	{
		message.addMessage("Usted no tiene cuentas, cree una primero");
		defineMenu();
	}
	public void defineRegisterPin()
	{
		if(user.getPin().equals(""))
		{
			message.addMessage("Por favor elige un PIN de seguridad, este te será requerido cada que ingreses al sistema.","Integer");
		}
		else
		{
			message.addMessage("Te hemos registrado correctamente.");
			user.setStatus("Registrado");
			defineWelcome();
		}
		
	
	}
	public void defineWelcome()
	{
		message.addMessage("Hola de nuevo "+user.getName());
		message.addMessage("Solo por seguridad ¿Cuál es tu PIN?","Integer");
	}
	public void defineMenu() 
	{
		message.addMessage("Bienvenido.");
		message.addMessage("Elige una opción: "
						+ "\n1. Ver Saldo."
						+ "\n2. Retirar dinero."
						+ "\n3. Depositar dinero."
						+ "\n4. Crear cuenta."
						+ "\n5. Salir.","Integer");
	}
	public void defineSelectCoin()
	{
		message.addMessage("Seleccione la moneda: "
				+ "\n1. Dólares."
				+ "\n2. Bolivianos","Integer");
	}
	public void defineSelectType() 
	{
		message.addMessage("Seleccione el tipo de cuenta: "
				+ "\n1. Cuenta Corriente."
				+ "\n2. Caja de Ahorros","Integer");
	}
	public void defineAccountCreated()
	{
		String moneda=user.getInteraction().getSend().get(0);
		String tipo=user.getInteraction().getSend().get(1);
		String nro="";
		if(tipo.equals("Caja de Ahorros"))
		{
			nro+="301-";
		}
		else if(tipo.equals("Cuenta Corriente"))
		{
			nro+="101-";
		}
		if(moneda.equals("Dólares"))
		{
			nro+="200-";
		}
		else if(moneda.equals("Bolivianos"))
		{
			nro+="104-";
		}
		Random rand= new Random();
		int rng= rand.nextInt(500);
		nro+=rng;
		user.addAccount(new Cuenta(moneda,nro,tipo,0.0));
		message.addMessage("Se le ha creado una cuenta: "
				+ "\nNro de Cuenta: "+nro
				+"\nMoneda: "+moneda
				+ "\nTipo: "+tipo
				+ "\nSaldo: 0.0");
		defineMenu();
		user.getInteraction().clearSend();
	}
	public void defineShowInfo() 
	{
		
		int n= Integer.parseInt(user.getInteraction().getSend().get(1));
		n-=1;
		System.out.println("Definir cuenta info: "+n);
		Cuenta cuenta= user.getAccounts().get(n);
		String msg="Nro cuenta: " +cuenta.getNroCuenta()
				+ "\nTipo: "+cuenta.getTipo()
				+ "\nMoneda: "+cuenta.getMoneda()
				+ "\nSaldo disponible: "+cuenta.getSaldo();
		message.addMessage(msg);
		defineMenu();
	}
	public void defineWithdraw()
	{
		message.addMessage("Ingrese el monto a retirar: ","Integer");
	}
	public void defineDeposit()
	{
		message.addMessage("Ingrese el monto a depositar: ","Integer");
	}
	public void defineShowAccounts()
	{
		String msg="Seleccione una Cuenta: ";
		for(int i=0;i<user.getAccounts().size();i++) 
		{
			Cuenta account=user.getAccounts().get(i);
			msg+="\n";
			msg+=(i+1)+". "+account.getTipo()+"-"+account.getNroCuenta();
		}
		message.addMessage(msg,"Integer");
	}
}
