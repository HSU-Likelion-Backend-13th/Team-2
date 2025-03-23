package hello.hello_spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //도메인 첫번째
    public String home() {
        return "home";
    }
}
