package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name); // 여기 name은 url에서 받아온거
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(name = "name") String name){
        return "hello " + name; // 템플릿 엔진과의 차이점은 문자가 그대로 감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        // property approach
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
