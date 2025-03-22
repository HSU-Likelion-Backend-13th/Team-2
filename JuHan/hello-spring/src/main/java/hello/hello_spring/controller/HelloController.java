package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")

    public String hello(Model model) {
        // model 객체에 data라 이름으로 hello!!라는 값 추가
        model.addAttribute("data", "hello!!");
        //templates에 있는 hello.html로 가서 렌더링해라
        return "hello";
    }

    @GetMapping("hello-mvc")
    // http get방식을 사용하여 ?name=spring이라면 String name-> spring값을 받는다
    public String helloMvc(@RequestParam("name")String name, Model model) {
        model.addAttribute("name", name);
        //hell-template 파일로 이동
        return "hello-template";

    }
}
