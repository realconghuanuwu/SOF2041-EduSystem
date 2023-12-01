/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author huanl
 */
public class XEmail {
    public static void sendSingleMail(String title, String mess, String email) throws AddressException {
        String[] emailList = {email};
        sendMultiEmail(title, mess, emailList);
    }
    
    public static void sendMultiEmail (String title, String mess, Object[] emailList) throws AddressException {
        if(emailList.length != 0) {
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("điền email ở đây", "điền password ở đây!");
                }
            });
            
            try {
                //Tạo đối tượng Message từ session 
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("điền email ở đây"));
                InternetAddress[] IAddresses = new InternetAddress[emailList.length];
                for (int i = 0; i < emailList.length; i++) {
                    String email = String.valueOf(emailList[i]);
                    IAddresses[i] = new InternetAddress(email);
                }
                message.setRecipients(Message.RecipientType.TO, IAddresses);

                message.setSubject(title);
                message.setText(mess);

                Transport.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
