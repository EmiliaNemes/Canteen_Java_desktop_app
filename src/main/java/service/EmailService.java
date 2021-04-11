package service;

import dto.ProductDto;
import javafx.collections.ObservableList;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailService {

    private final static Logger log = Logger.getLogger(EmailService.class.getName());

    public EmailService(String to, String name, String address, ObservableList<ProductDto> order, String total, String time) {

        // Put sender’s address
        String from = "b08545a2a4-2ae9a0@inbox.mailtrap.io";
        final String username = "45bd67d3337075";//username generated by Mailtrap
        final String password = "da1a8c2b910582";//password generated by Mailtrap

        // Paste host address from the SMTP settings tab in your Mailtrap Inbox
        String host = "smtp.mailtrap.io";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");//it’s optional in Mailtrap
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "2525");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(from));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject("Food order - cantina UTCN - " + name);
            // Put the content of your message
            message.setText(prettyMail(name, address, order, total, time));
            // Send message
            Transport.send(message);
            log.info("Message sent successfully");

        } catch (MessagingException e) {
            log.warning("Message could not be sent successfully");
            throw new RuntimeException(e);
        }
    }

    private String prettyMail(String name, String address, ObservableList<ProductDto> order, String total, String time) {
        return "Dear " + name + ", \n\n" +
                "We are glad to inform you that your ordered was successfully received and your food is on it's way! \n\n" + "A summary of your order is available:\n\n" + prettyOrder(order) + prettyDetails(address, time, total) + prettyEnding();
    }


    private String prettyDetails(String address, String time, String total) {
        StringBuilder prettyDetails = new StringBuilder();
        prettyDetails.append("-----------------\n");
        prettyDetails.append("Total: ").append(total).append("\n");

        if (address.equals("Take-in")) {
            prettyDetails.append("\nYou can get your order from Cantina UTCN as soon as possible!\n\n");
        } else {
            prettyDetails.append("\nYour order will be delivered at: ").append(address).append(" ( *5 ron transportation)").append("\nEstimated time:").append(time).append("\n\n");
        }
        return prettyDetails.toString();
    }

    private String prettyOrder(ObservableList<ProductDto> order) {
        StringBuilder prettyOrder = new StringBuilder();
        int index = 1;
        for (ProductDto orderItem : order) {
            prettyOrder.append(index).append(". ").append(orderItem.getName()).append("    Quantity - ").append(orderItem.getQuantity()).append("    Price - ").append(orderItem.getQuantity() * orderItem.getPrice()).append("\n\n");
            index++;
            }

        return prettyOrder.toString();

    }

    private String prettyEnding() {
        return "Bon appetit! \nTeam Cantina UTCN\n\n";
    }

}
