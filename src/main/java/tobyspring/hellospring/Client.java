package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        Payment payment = paymentService.prepare(100L, "KRW", BigDecimal.valueOf(50.7));
        PaymentService paymentService2 = new PaymentService(new SimpleExRateProvider());
        Payment payment2 = paymentService2.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
        System.out.println(payment2);
    }
}
