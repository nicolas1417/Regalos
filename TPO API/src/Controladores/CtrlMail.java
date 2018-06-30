package Controladores;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CtrlMail {
	
	private static CtrlMail instancia;
	
	private final Properties properties = new Properties();
	private String password;
	private Session session;
	
	public static CtrlMail getInstancia()
	{
		if (instancia == null)
			instancia = new CtrlMail();
		return instancia;
	}
	
	public CtrlMail()
	{
		
	}
	
	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",25);
		properties.put("mail.smtp.mail.sender","mail@gmail.com");
		properties.put("mail.smtp.user", "usuario");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
		;
	}

	public void sendEmail(){

		init();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("mail@yahoo.com"));
			message.setSubject("Prueba");
			message.setText("Texto");
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException me)
		{
			me.printStackTrace();
			//Aqui se deberia o mostrar un mensaje de error o en lugar
                        //de no hacer nada con la excepcion, lanzarla para que el modulo
                        //superior la capture y avise al usuario con un popup, por ejemplo.
			return;
		}
		
	}
}
