package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // log를 자동으로 넣어줌
// @Controller -> 반환 값이 String이면, 뷰 이름으로 인식됨. 즉, 뷰를 찾고 뷰가 렌더링 됨
@RestController // 문자열이 HTTP 메시지 바디에 바로 입력됨.
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {

        String name = "Spring";

        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("info log = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        // log.trace("trace log = " + name); => 이렇게 사용하는 것 권장 x,
        // + 연산이 '먼저' 일어나면서 trace log를 쓰지 않아도 불필요한 연산이 일어나게 됨.

        return "ok";
    }
}


/*
로그의 장점

1. 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있다.
2. 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영 서버에서는 출력하지 않는 등 상황에 맞게 조절할 수 있다.
3. 시스템 아웃 콘솔에만 출력하는 것이 아닌, 파일이나 네트워크 등 로그를 별도의 위치에 남길 수 있다. 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그 분할이 가능하다.
4. 성능 자체도 일반 System.out 보다 좋다. (내부 버퍼링, 멀티 쓰레드 등) 때문에 실무에서 꼭 로그를 사용한다.

* 로그에 대해 더 알아보려면,
    - SLF4J: http://www.slf4j.org
    - Logback: http://logback.qos.ch
 */