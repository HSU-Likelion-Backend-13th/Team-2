package hello.hello_spring.controller;

public class MemberForm {
    private String name; // html action url을 타고 들어온 post data가 여기로 들어옴

    public String getName() {
        return name;
    }

    public void setName(String name) { // encapsulation 돼있으니까 이걸로 세팅
        this.name = name;
    }
}
