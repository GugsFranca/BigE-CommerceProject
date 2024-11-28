package store.notification.email;

import lombok.Getter;

@Getter
public enum EmailTemplates {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"), //subject é o asssunto do email e o html é o arquivo que vai carregar o template para a formatação do email
    ORDER_CONFIRMATION("order-confirmation.html", "Order successfully processed");

    private final String template;
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
