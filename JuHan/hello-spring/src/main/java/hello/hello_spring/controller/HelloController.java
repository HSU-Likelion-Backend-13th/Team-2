package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")

    public String hello(Model model) {
        // model 객체에 data라 이름으로 hello!!라는 값 추가
        model.addAttribute("data", "hello!!");
        //templates에 있는 hello.html로 가서 렌더링해라
        return "hello";
    }
}
