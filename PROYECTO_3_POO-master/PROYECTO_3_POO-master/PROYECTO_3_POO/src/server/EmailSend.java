
package server;

import java.util.*;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;


/**
 * Clase de utilidad para enviar correos electronicos.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class EmailSend {

    /**
     * Envia un correo electrónico sin archivo adjunto.
     * 
     * @param para destinatario
     * @param sujeto asunto del correo
     * @param mensaje cuerpo del mensaje
     */
    public static void enviar(String para, String sujeto, String mensaje){
        try{
            String host ="smtp.gmail.com" ;
            String user = "rentacarcr2019@gmail.com";
            String pass = "Proyecto1";
            String to = para;
            String from = "rentacarcr2019@gmail.com";
            String subject = sujeto;
            String messageText = mensaje;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
    
    /**
     * Envia un correo electrónico con un archivo adjunto.
     * 
     * @param para destinatario
     * @param sujeto asunto del correo
     * @param archivo direccion del archivo a adjuntar
     * @param nombre del archivo a adjuntar
     */
    public static void enviarArchivo(String para, String sujeto, String archivo, String nombre) {

        final String username = "rentacarcr2019@gmail.com"; //ur email
        final String password = "Proyecto1";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }});

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rentacarcr2019@gmail.com"));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(para));//u will send to
            message.setSubject(sujeto);    
            message.setText("PFA");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();




        //attached 1 --------------------------------------------
            String file = archivo;
            String fileName = nombre;
            messageBodyPart = new MimeBodyPart();   
            DataSource source = new FileDataSource(file);      
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
        //------------------------------------------------------    
/*
         //attached 2 --------------------------------------------  
            String file2="path of file";
            String fileName2 = "AnyName2";
            messageBodyPart = new MimeBodyPart();   
            DataSource source2 = new FileDataSource(file2);      
            messageBodyPart.setDataHandler(new DataHandler(source2));
            messageBodyPart.setFileName(fileName2);
            multipart.addBodyPart(messageBodyPart);
*/
        //>>>>>>>>  
            message.setContent(multipart);

            System.out.println("sending");
            Transport.send(message);
            System.out.println("Done");

        }catch (MessagingException e) {
            e.printStackTrace();
        }
  }
}
