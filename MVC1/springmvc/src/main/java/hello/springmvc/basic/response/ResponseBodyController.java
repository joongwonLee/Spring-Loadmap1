package hello.springmvc.basic.response;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody --> 전역으로 선언해서 사용 가능, HTTP 메시지 바디에 직접 데이터 입력
@RestController //-> @Controller + @ResponseBody 합친 것
public class ResponseBodyController {

    @GetMapping("response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @GetMapping("response-body-string-v3")
    public String responseBodyV3() throws IOException {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("joongwon");
        helloData.setAge(23);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }


    /**
     * @ResponseStatus(HTTP 상태 코드 지정)
     * 어노테이션이기 때문에 응답 코드를 동적으로 변경할 수 x
     * HTTP 응답 코드를 동적으로 변경하고 싶으면, ResponseEntity를 사용할 것
     *
     * 보통 이러한 방식을 자주 사용
     */
    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("joongwon");
        helloData.setAge(23);
        return helloData;
    }
}
