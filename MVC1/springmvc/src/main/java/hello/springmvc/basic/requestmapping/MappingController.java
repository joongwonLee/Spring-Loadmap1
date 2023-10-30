package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    //    @RequestMapping({"/hello-basic", "hello-go"}) => mapping 조건 두 개 이상도 가능!
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");

        return "ok";
    }

//    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
//    public String mappingGetV1() {
//        log.info("mappingGetV1");
//        return "ok";
//    }

    /**
     * 편리한 축약 어노테이션
     *
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/hello-basic")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     *
     * @return
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * PathVariable 다중 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    // 만약 변수명(data)이 PathVariable(userId)과 같다면, ("userId") 생략 가능
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params = "mode",
     * params = "!mode",
     * params = "mode=debug",
     * params = "mode!=debug",
     * params = {"mode=debug", "data=good"}
     * 잘 사용하진 x, (대부분 PathVariable 사용)
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers = "mode",
     * headers = "!mode",
     * headers = "mode=debug",
     * headers = "mode!=debug",
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }


    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
//    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
//    @PostMapping(value = "/mapping-produce", produces = "text/html")
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}


/**
 * 1.
 * '/hello-basic'과 '/hello-basic/'은 다른 URL이지만,
 * 스프링은 같은 요청으로 매핑해 준다.
 * <p>
 * 2.
 * RequestMapping의 method로 HTTP 메서드를 지정해주지 않으면,
 * 모든 HTTP 메서드(GET, POST, PUT, DELETE) 적용이 가능하다.
 */
