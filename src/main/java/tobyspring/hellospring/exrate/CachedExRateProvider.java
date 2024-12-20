package tobyspring.hellospring.exrate;

import tobyspring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiryTime;
    public CachedExRateProvider(ExRateProvider target){
        this.target=target;
    }

    @Override
    public BigDecimal getExRate(String currency)  {
        if(cachedExRate==null || cacheExpiryTime.isBefore(LocalDateTime.now())){
            cachedExRate = this.target.getExRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("캐시업데이트됨");
        }
        return cachedExRate;
    }
}
