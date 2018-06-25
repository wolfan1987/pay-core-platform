package org.andrew.commons.utils.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送实现类。
 *
 * @author andrewliu 2017年9月4日
 */
public class TlsJavaMailSenderImpl extends JavaMailSenderImpl implements JavaMailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        setTlsMailConfiger();
        super.send(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage[] simpleMessages) throws MailException {
        setTlsMailConfiger();
        super.send(simpleMessages);
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
        setTlsMailConfiger();
        super.send(mimeMessage);
    }

    @Override
    public void send(MimeMessage[] mimeMessages) throws MailException {
        setTlsMailConfiger();
        super.send(mimeMessages);
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
        setTlsMailConfiger();
        super.send(mimeMessagePreparator);
    }

    @Override
    public void send(MimeMessagePreparator[] mimeMessagePreparators) throws MailException {
        setTlsMailConfiger();
        super.send(mimeMessagePreparators);
    }

    private void setTlsMailConfiger() {
        Properties props = this.getJavaMailProperties();
        final String username = this.getUsername();
        final String password = this.getPassword();
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        super.setSession(session);
    }

}
