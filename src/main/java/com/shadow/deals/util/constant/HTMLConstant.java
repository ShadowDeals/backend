package com.shadow.deals.util.constant;

/**
 * This class contains constants related to HTML.
 *
 * @author Kirill "Tamada" Simovin
 */
public interface HTMLConstant {
    /**
     * <b>style</b> attribute.
     */
    String HTML_STYLE_ATTR = "style";

    /**
     * Style for main {@code <div/>} container of email template.
     */
    String EMAIL_TEMPLATE_MAIN_CONTAINER_STYLE = "display: flex; width: 40%; height: fit-content; background: white; flex-direction: column; gap: 25px; padding-bottom: 20px";

    /**
     * Style for header {@code <div/>} container of email template.
     */
    String EMAIL_TEMPLATE_HEADER_CONTAINER_STYLE = "display: flex; background: black; padding: 15px; flex-direction: column; gap: 25px; border-radius: 0 0 15px 15px; align-items: flex-start;";

    /**
     * Style for header text of email template.
     */
    String EMAIL_TEMPLATE_HEADER_TEXT_STYLE = "font-size: 18px; color: white; font-weight: bold;";

    /**
     * Style for {@code <a/>} tag that wrap {@code <button/>} tag in email template.
     */
    String EMAIL_TEMPLATE_A_BUTTON_STYLE = "width: 100%; height: fit-content;";

    /**
     * Style for {@code <button/>} tag in email template.
     */
    String EMAIL_TEMPLATE_BUTTON_STYLE = "font-size: 16px; font-weight: bold; color: black; background-color: white; border-radius: 10px; padding: 5px; width: 100%; height: fit-content; text-align: center; cursor: pointer; border: none; outline: none;";
}