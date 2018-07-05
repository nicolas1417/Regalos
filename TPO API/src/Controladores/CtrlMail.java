package Controladores;

import java.io.FileInputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CtrlMail {
	
	private static CtrlMail instancia;
	
	private final Properties sessionProperties = new Properties();
	private final Properties propiedadesMail = new Properties();
	private Session session;
	
	public static CtrlMail getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlMail();
		return instancia;
	}
	
	public CtrlMail()
	{
		getConfiguracion();
	}
	
	private void init() {

		sessionProperties.put("mail.smtp.host", "smtp.gmail.com");
		sessionProperties.put("mail.smtp.starttls.enable", "true");
		sessionProperties.put("mail.smtp.port",25);
		sessionProperties.put("mail.smtp.mail.sender",propiedadesMail.getProperty("MailApp"));
		sessionProperties.put("mail.smtp.user", propiedadesMail.getProperty("User"));
		sessionProperties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(sessionProperties);
	}
	
	public void EnviarEmailNotificarRegalos(String mail,String participantes)
	{
		init();
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)sessionProperties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			message.setSubject(propiedadesMail.getProperty("AsuntoNotificarRegalos"));
			message.setText(propiedadesMail.getProperty("MensajeNotificarRegalos") + participantes +"<br><br>"+ propiedadesMail.getProperty("Saludo"),"ISO-8859-1","html");
			Transport t = session.getTransport("smtp");
			t.connect((String)sessionProperties.get("mail.smtp.user"), propiedadesMail.getProperty("PasswordMailApp"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
	}
	
	
	public void EnviarEmailAvisoCierre(String mail)
	{
		init();
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)sessionProperties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			message.setSubject(propiedadesMail.getProperty("AsuntoAvisoCierre"));
			message.setText(propiedadesMail.getProperty("MensajeAvisoCierre") + propiedadesMail.getProperty("Saludo"),"ISO-8859-1","html");
			Transport t = session.getTransport("smtp");
			t.connect((String)sessionProperties.get("mail.smtp.user"), propiedadesMail.getProperty("PasswordMailApp"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
	}
	
	public void EnviarEmailAvisoInicio(String mail)
	{
		init();
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)sessionProperties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			message.setSubject(propiedadesMail.getProperty("AsuntoAvisoInicio"));
			message.setText(propiedadesMail.getProperty("MensajeAvisoInicio") + propiedadesMail.getProperty("Saludo"),"ISO-8859-1","html");
			Transport t = session.getTransport("smtp");
			t.connect((String)sessionProperties.get("mail.smtp.user"), propiedadesMail.getProperty("PasswordMailApp"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
	}
	
	private void getConfiguracion()
	{
		String configuracion = "ConfigMail.txt";
	   
		try 
		{
			FileInputStream f = new FileInputStream(configuracion);	 
		    propiedadesMail.load(f);
		    f.close();    		   
		}
		catch (Exception e) 
	    {
			System.out.println("Mensaje Error: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
	    }
	}
}
