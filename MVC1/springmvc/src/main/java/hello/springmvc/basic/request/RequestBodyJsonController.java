package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username": "joongwon", "age": 23}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        response.getWriter().write("ok");
    }


    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    /**
     * @RequestBody 객체 파라미터
     * - @RequestBody HelloData data
     * - @RequestBody에 직접 만든 객체를 지정할 수 있음
     *
     * @RequestBody 생략 불가능(@ModelAttribute가 적용되어 버림)
     * - @ModelAttribute는 생략 시, argument resolver로 지정해준 타입 외, 단순 타입 외(String, int, Integer 등) 나머지에 규칙 적용
     * - 때문에 객체 파라미터를 넘기는데 @RequestBody를 생략한다면, @ModelAttribute가 적용됨 [HTTP 메시지 바디 x, 요청 파라미터 처리]
     *
     * HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter (content-type: application/json)
     * - converter가 objectMapper.readValue(...)를 대신 처리해줌. 즉, JSON을 객체로 변환해줌.
     *
     * '주의!'
     * HTTP 요청 시 content-type이 application/json인지 확인해야 함. 그래야 JSON을 처리할 수 있는 HTTP 메시지 컨버터가 실행됨
     */
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> data) {
        HelloData helloData = data.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


    /**
     * @ResponseBody 적용
     * - 메시지 바디 정보 직접 반환 (view 조회 x)
     * - HttpMessageConverter 사용 -> MappingJackson2HttpMessageConverter 적용(Accept: application/json)
     *
     * @RequestBody 요청
     * - JSON 요청 -> HTTP 메시지 컨버터 -> 객체
     * @ResponseBody 응답
     * - 객체 -> HTTP 메시지 컨버터 -> JSON 응답
     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data;
    }
    
}
