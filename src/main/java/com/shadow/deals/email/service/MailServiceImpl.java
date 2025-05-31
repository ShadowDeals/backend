package com.shadow.deals.email.service;

import com.shadow.deals.util.constant.HTMLConstant;
import io.micronaut.context.annotation.Value;
import io.micronaut.email.Email;
import io.micronaut.email.EmailSender;
import j2html.TagCreator;
import j2html.tags.specialized.ATag;
import j2html.tags.specialized.ButtonTag;
import j2html.tags.specialized.SpanTag;
import jakarta.inject.Singleton;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * This class contains methods that perform logic related to the sending email messages.
 *
 * @author Kirill "Tamada" Simovin
 */
@Singleton
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * Who send the message.
     */
    @Value("${shadow-deals.mail.username}")
    private String from;

    /**
     * Instance of the service that allows to send emails.
     */
    private final EmailSender<MimeMessage, ?> emailSender;

    /**
     * This method allows to send an email message.
     *
     * @param to            the recipient of the email.
     * @param subject       the subject of the letter.
     * @param html          HTML representation of the letter.
     * @param relatedImages a set of {@code image_id:file_path} pairs, where instead of {@code image_id} in the HTML
     *                      representation, the path to the file on the server will be substituted.
     * @throws RuntimeException in following cases:
     *                          <li>• The recipient's email does not exist.</li>
     *                          <li>• An error occurred during the formation of the email body.</li>
     */
    @Override
    public void sendMessage(String to, String subject, String html, Map<String, String> relatedImages) throws RuntimeException {
        Email.Builder builder = Email.builder()
                .from(from)
                .to(to)
                .subject(subject);

        try {
            CompletableFuture.runAsync(() -> emailSender.send(builder, message -> {
                try {
                    message.setContent(configureMultipart(html, relatedImages));
                } catch (MessagingException e) {
                    LOG.error("Exception during setting content to email!", e);
                    throw new RuntimeException(e);
                }
            }));
        } catch (Exception e) {
            LOG.error("Error sending email!", e);
        }
    }

    /**
     * This method allows to form the message body.
     *
     * @param html          HTML representation of the letter.
     * @param relatedImages a set of {@code image_id:file_path} pairs, where instead of {@code image_id} in the HTML
     *                      representation, the path to the file on the server will be substituted.
     * @return The configured message body.
     * @throws RuntimeException in case an error occurred during the formation of the email body.
     */
    @NotNull
    private Multipart configureMultipart(String html, @NotNull Map<String, String> relatedImages) throws RuntimeException {
        Multipart multipart = new MimeMultipart("related");
        MimeBodyPart htmlPart = new MimeBodyPart();
        try {
            htmlPart.setText(html, "utf-8", "html");
            multipart.addBodyPart(htmlPart);

            for (String contentId : relatedImages.keySet()) {
                MimeBodyPart imgPart = new MimeBodyPart();
                imgPart.attachFile(relatedImages.get(contentId));
                imgPart.setContentID(contentId);
                multipart.addBodyPart(imgPart);
            }
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
        return multipart;
    }

    /**
     * This method allows to create an HTML representation of the message being sent. <br/>
     * <img src="{@docRoot}/image/email-example.png" alt="Email example."/>
     *
     * @param boldText    the text that is displayed at the top of the message.
     * @param link        the link that the user will follow when clicking on the button.
     * @param buttonText  the text that will be placed in the button.
     * @param regularText the text that will be written after the message title.
     * @return HTML representation of the message being sent.
     */
    @NotNull
    @Override
    public String fillEmailTemplate(String boldText, @NotNull String link, String buttonText, String regularText) {
        return TagCreator.div(
                        TagCreator.div(
                                TagCreator.img()
                                        .withSrc("cid:logo")
                                        .withAlt("Shadow Deals logo")
                                        .withHeight("32px"),
                                TagCreator.span(
                                        boldText
                                ).attr(HTMLConstant.HTML_STYLE_ATTR, HTMLConstant.EMAIL_TEMPLATE_HEADER_TEXT_STYLE),
                                TagCreator.iffElse(
                                        !link.isBlank(),
                                        createEmailTemplateAButton(link, buttonText),
                                        createEmailTemplateButton(buttonText)
                                )
                        ).attr(HTMLConstant.HTML_STYLE_ATTR, HTMLConstant.EMAIL_TEMPLATE_HEADER_CONTAINER_STYLE),
                        createEmailTemplateRegularTextSpan(regularText)
                ).attr(HTMLConstant.HTML_STYLE_ATTR, HTMLConstant.EMAIL_TEMPLATE_MAIN_CONTAINER_STYLE)
                .render();
    }

    /**
     * This method allows to create a {@code <span/>} tag for text without formatting in the email template. All
     * {@code \n} will be replaced with {@code <br/>} tag.
     *
     * @param regularText the text to form the {@code <span/>} tag from.
     * @return Generated {@code <span/>} tag.
     */
    @NotNull
    private SpanTag createEmailTemplateRegularTextSpan(@NotNull String regularText) {
        String[] split = regularText.split("\n");
        SpanTag spanTag = TagCreator.span();
        for (String s : split) {
            if (!s.isEmpty()) {
                spanTag.withText(s.trim());
            } else {
                spanTag.with(TagCreator.br());
            }
        }
        return spanTag;
    }

    /**
     * This method allows to create a {@code <button/>} tag in the email template wrapped in {@code <a/>} tag. <br/>
     * Using {@link HTMLConstant#EMAIL_TEMPLATE_A_BUTTON_STYLE} as style.
     *
     * @param link       the link that the user follows when clicking on the button.
     * @param buttonText the text inside the button.
     * @return Generated {@code <button/>} tag wrapped in {@code <a/>} tag.
     */
    private ATag createEmailTemplateAButton(String link, String buttonText) {
        return TagCreator.a(
                        createEmailTemplateButton(buttonText)
                ).withHref(link)
                .attr(HTMLConstant.HTML_STYLE_ATTR, HTMLConstant.EMAIL_TEMPLATE_A_BUTTON_STYLE);
    }

    /**
     * This method allows to create a {@code <button/>} tag with given text in the email template. <br/>
     * Using {@link HTMLConstant#EMAIL_TEMPLATE_BUTTON_STYLE} as style.
     *
     * @param buttonText the text inside the button.
     * @return Generated {@code <button/>} tag with given text.
     */
    private ButtonTag createEmailTemplateButton(String buttonText) {
        return TagCreator.button()
                .withText(buttonText)
                .attr(HTMLConstant.HTML_STYLE_ATTR, HTMLConstant.EMAIL_TEMPLATE_BUTTON_STYLE);
    }
}