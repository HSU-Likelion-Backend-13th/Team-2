package hello.core.Autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.bean.override.convention.TestBean;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        //@Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출안됨
        //호출안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("setNoBean1 = " + noBean1);
        }

        //org.springframwork.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("setNoBean2 = " + noBean2);
        }

        //Optional<> : 자동 주입할 대상이 없으면 Optional.empty가 입력된다.
        //Optional.empty 호출
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("setNoBean3 = " + noBean3);
        }
    }
}
