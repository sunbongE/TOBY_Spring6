package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

// 수동 DI 테스트
class PaymentServiceTest {
    Clock clock;
    @BeforeEach
    void beforeEach(){
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 충족했는지")
    void prepare() {
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), clock);
        testAmount(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), clock);
        testAmount(BigDecimal.valueOf(3000), BigDecimal.valueOf(30000), clock);

        // 원화환산금액 유효시간 계산
//        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());

    }
    @Test
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1000)), clock);

        Payment payment = paymentService.prepare(1L,"USD",BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedCalidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil().isEqual(expectedCalidUntil));
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보가져오기.
        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualByComparingTo(convertedAmount);
    }

}