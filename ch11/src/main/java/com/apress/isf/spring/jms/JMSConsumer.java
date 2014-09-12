package com.apress.isf.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.utils.XmlUtils;
import com.apress.isf.spring.data.DocumentDAO;

@Component
public class JMSConsumer implements MessageListener {

    @Autowired
    DocumentDAO documentDAO;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            Document document = XmlUtils.fromXML(textMessage.getText(), Document.class);
            documentDAO.save(document);
        } catch(JMSException ex) {
            ex.printStackTrace();
        }
    }

}
