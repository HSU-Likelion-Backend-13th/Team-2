package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
// Optional : 값이 존재할 수도 있고, 존재하지 않을 수도 있음을 명확하게 표현하는 클래스
import java.util.Optional;

public interface MemberRepository {

    // 인터페이스 : 이름만 구현해 놓고 기능은 다른 클래스에서 구현
    Member save(Member member);
    //결과가 있을 수도, 없을 수도 있기 때문에 Optional로 반환
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
