package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 충족했는지")
    void prepare() throws IOException {

        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000));
        testAmount(BigDecimal.valueOf(3000), BigDecimal.valueOf(30000));

        // 원화환산금액 유효시간 계산
//        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());

    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보가져오기.
        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(convertedAmount);
    }

}