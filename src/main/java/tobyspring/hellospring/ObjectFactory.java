package tobyspring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(CachedExRateProvider());
    }

    @Bean
    public ExRateProvider CachedExRateProvider(){
        return new CachedExRateProvider(exRateProvider());
    }
    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }

}
