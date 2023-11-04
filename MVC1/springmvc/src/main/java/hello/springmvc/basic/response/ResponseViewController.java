package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }


//    @ResponseBody
//    만약, @ResponseBody가 있으면, view resolver를 실행하지 않고 HTTP 메시지 바디에 직접 'response/hello' 문자열이 입력됨
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }


    /**
     * Void를 반환하는 경우
     * @Controller를 사용하고, HttpServletResponse, OutputStream(Writer) 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면,
     * 요청 URL을 참고해서 논리 뷰 이름으로 사용
     * - 요청 URL: '/response/hello'
     * - 실행: templates/response/hello.html
     *
     * 이와 같은 방식은 명시성이 떨어지고, 딱 맞아떨어지는 경우가 많이 없어 권장하지 x
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}

/**
 * 다시 한 번 상기시키기!
 *
 * 'HTTP 메시지'
 * @ResponseBody, HttpEntity를 사용하면 뷰 템플릿을 사용한느 것이 아니라 HTTP 메시지 바디에 직접 응답 데이터를 출력함
 * 뷰 템플릿을 사용하려면, @Controller에서 ModelAndView 혹은 String 값을 리턴
 */