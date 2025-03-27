package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("hello-string")
    // responsebody는 http에 body부에 데이터를 직접 넣어줌
    // view 단계가 없이 문자 그대로 넘어감 -> html 코드 X
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    // key - value로 구성된 json 형 (xml 방식X)
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        // getter setter(프롬퍼티 접근방식) -> javabean 규약
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




}
