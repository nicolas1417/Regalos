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
		properties.put("mail.smtp.mail.sender","lucio.tzikas@gmail.com");
		properties.put("mail.smtp.user", "lucio.tzikas");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
	}
	
	public void sendEmail(String mail, String sujeto, String mensaje)
	{
		init();
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			message.setSubject(sujeto);
			message.setText(mensaje);
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), "evaristegalois");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
	}
}
