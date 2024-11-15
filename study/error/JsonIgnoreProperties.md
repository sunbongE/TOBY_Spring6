**ErrorMSG**

provider 필드를 받아주는 부분이 없어서 나오는 error

```java
Exception in thread "main" com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "provider" (class tobyspring.hellospring.ExRateData), not marked as ignorable (2 known properties: "rates", "result"])
 at [Source: (String)"{"result":"success","provider":"<https://www.exchangerate-api.com>","documentation":"<https://www.exchangerate-api.com/docs/free","terms_of_use":"https://www.exchangerate-api.com/terms","time_last_update_unix":1731369751,"time_last_update_utc":"Tue>, 12 Nov 2024 00:02:31 +0000","time_next_update_unix":1731456781,"time_next_update_utc":"Wed, 13 Nov 2024 00:13:01 +0000","time_eol_unix":0,"base_code":"USD","rates":{"USD":1,"AED":3.6725,"AFN":68.18752,"ALL":91.859213,"AMD":387.349451,"ANG":1.79,"AOA":92"[truncated 2418 chars]; line: 1, column: 2919] (through reference chain: tobyspring.hellospring.ExRateData["provider"])
	at com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException.from(UnrecognizedPropertyException.java:61)
	at com.fasterxml.jackson.databind.DeserializationContext.handleUnknownProperty(DeserializationContext.java:1138)
	at com.fasterxml.jackson.databind.deser.std.StdDeserializer.handleUnknownProperty(StdDeserializer.java:2224)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.handleUnknownProperty(BeanDeserializerBase.java:1709)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.handleUnknownProperties(BeanDeserializerBase.java:1659)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer._deserializeUsingPropertyBased(BeanDeserializer.java:460)
	at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1409)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
	at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
	at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
	at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4825)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3772)
	at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3740)
	at tobyspring.hellospring.PaymentService.prepare(PaymentService.java:29)
	at tobyspring.hellospring.PaymentService.main(PaymentService.java:41)
```

## 해결

`@JsonIgnoreProperties(ignoreUnknown = true)` 를 추가해서 해결 이 애노테이션을 클래스에 붙이면 JSON 데이터에 포함된 필드 중에 해당 **Java 클래스에**서 **정의되지 않은 필드**가 있더라도 **무시하고 처리**합니다. 즉, 매핑되지 않은 JSON 필드가 있어도 **에러 없이 객체로 변환**할 수 있습니다.

```java
package tobyspring.hellospring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
```