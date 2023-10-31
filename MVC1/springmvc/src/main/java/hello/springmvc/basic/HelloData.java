package hello.springmvc.basic;

import lombok.Data;

// @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 등 자동 적용
@Data
public class HelloData {

    private String username;
    private int age;
}
