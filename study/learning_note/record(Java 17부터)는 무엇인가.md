# record(Java 17부터)는 무엇인가 

# record

Java 17에서 추가된 `record`는 불변(immutable) 데이터 객체를 간결하게 정의할 수 있는 새로운 타입입니다. 이를 사용하면 데이터의 구조를 명확하게 표현하면서, 불변 객체의 표준 기능(예: 생성자, `equals`, `hashCode`, `toString` 등)을 자동으로 제공합니다.

### 주요 특징 및 역할

1. **데이터 클래스 생성 간소화**: `record`는 데이터를 저장하는 간단한 클래스 정의에 적합합니다. 기존 클래스와 달리, 필드를 선언하고 `getter`, `equals`, `hashCode`, `toString` 등을 직접 정의할 필요 없이, Java 컴파일러가 자동으로 생성합니다.

2. **불변 객체 지원**: `record`로 생성된 객체는 불변입니다. 즉, 필드가 `final`로 선언되어 생성 이후 값이 변경되지 않습니다. 이러한 특성 덕분에 데이터 안전성이 높아지고, 멀티스레드 환경에서 유리합니다.

3. 자동으로 생성되는 메서드

   :

   - **생성자**: 모든 필드를 초기화하는 컴팩트한 생성자가 자동 생성됩니다.
   - **`getter` 메서드**: 각 필드에 대한 `getter`가 자동 생성되며, 메서드 이름은 필드 이름과 동일합니다.
   - **`equals` 및 `hashCode`**: 두 `record` 객체가 같은 필드 값을 가질 경우 동등한 객체로 간주합니다.
   - **`toString`**: 객체 내용을 보기 쉽게 출력하도록 자동 생성됩니다.

### 예제 코드

아래 예시에서는 `Person`이라는 `record`를 정의해 보겠습니다.

```java
public record Person(String name, int age) {}
```

위 코드는 아래와 같은 일반 클래스를 자동으로 생성하는 것과 같습니다.

```java
public final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person[name=" + name + ", age=" + age + "]";
    }
}
```

### `record`의 활용 예

데이터를 저장하는 객체가 복잡하지 않고, 불변성을 유지해야 할 때 유용합니다. 특히 API 응답 데이터를 처리하거나, DTO(Data Transfer Object)를 정의할 때 자주 활용됩니다.

### 요약

- **간결성**: 필드, 생성자, `getter` 등 불변 객체에 필요한 대부분의 코드를 자동 생성합니다.
- **불변성**: 모든 필드는 `final`로 정의되어 객체 생성 이후 변경되지 않습니다.
- **구조적 데이터 표현**: 데이터 중심의 객체를 정의할 때 효율적입니다.

Java 17의 `record`는 이를 통해 불변 데이터를 안전하게 관리하고, 코드 간결성을 높여 가독성을 크게 향상합니다.