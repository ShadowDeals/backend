package com.shadow.deals.email.service;

import java.util.Map;

/**
 * This interface contains the signatures of the methods that must be implemented by the corresponding service class.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface MailService {
    /**
     * This signature describes a method that allows to send an email message.
     *
     * @param to            the recipient of the email.
     * @param subject       the subject of the letter.
     * @param html          HTML representation of the letter.
     * @param relatedImages a set of {@code image_id:file_path} pairs, where instead of {@code image_id} in the HTML
     *                      representation, the path to the file on the server will be substituted.
     */
    void sendMessage(String to, String subject, String html, Map<String, String> relatedImages);

    /**
     * This signature describes a method that allows to create an HTML representation of the message being sent. <br/>
     * <img src="{@docRoot}/image/email-example.png" alt="Email example."/>
     *
     * @param boldText    the text that is displayed at the top of the message.
     * @param link        the link that the user will follow when clicking on the button.
     * @param buttonText  the text that will be placed in the button.
     * @param regularText the text that will be written after the message title.
     * @return HTML representation of the message being sent.
     */
    String fillEmailTemplate(String boldText, String link, String buttonText, String regularText);
}
