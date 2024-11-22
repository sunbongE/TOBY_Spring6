package tobyspring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

// 스프링 컨테이너를 사용한 테스트
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)  // 테스트가 실행될때 구성정보 만듬
class PaymentServiceSpringTest {

    @Autowired PaymentService paymentService;
    @Autowired
    Clock clock;
    @Autowired ExRateProviderStub exRateProviderStub;


    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 충족했는지")
    void prepare() {
        // exRate 1000
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        Assertions.assertThat(payment.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(1000));
        Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10000));

        // exRate 1000
        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        Assertions.assertThat(payment2.getExRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
        Assertions.assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5000));

        // 원화환산금액 유효시간 계산
//        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());

    }
    @Test
    void validUntil(){

        Payment payment = paymentService.prepare(1L,"USD",BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedCalidUntil = now.plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil().isEqual(expectedCalidUntil));
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) {

    }

}