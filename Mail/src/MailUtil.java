import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtil 
{
	public static void SendMail(String recepient) throws Exception
	{
		System.out.println("Prepering to send email");
		Properties properties = new Properties();
		
	    properties.put("mail.smtp.host","smtp.gmail.com");
	    properties.put("mail.smtp.port", 587);
	    properties.put("mail.smtp.auth", true);
	    properties.put("mail.smtp.starttls.enable", "true");
		
		String myAccountEmail = "xxxxxx@gmail.com";
		String password = "xxxxxxxx";
		
		Session session = Session.getInstance(properties, new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message messagae = PreperMessage(session, myAccountEmail, recepient);
		
		Transport.send(messagae);
		
		
		System.out.println("Message sent successully");
	}

	private static Message PreperMessage(Session session, String myAccountEmail, String recepient) 
	{	
		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("You Won!");
			
			//You Can Use message.setText To send Only a text setContent Sending an HTML type text you can use CSS too like in here
			
			//the string can be whatever you want (;
			String htmlCode = "<body style=\"background-color: powderblue;\"> \r\n"
					+ "<h1 style=\"text-align: center; color: blue; font-family: verdana; font-size: 300%;\">YOU WON</h1><br>\r\n"
					+ "<p style=\"text-align: center; color: red; font-family: courier; font-size: 200%;\">Yey Congratulations You Won A Spamm Message From Muffin Cat</p>";
			
			message.setContent(htmlCode, "text/html");
			return message;
		}
		
		catch (Exception ex)
		{
			Logger.getLogger(MailUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return null;
	}
}
