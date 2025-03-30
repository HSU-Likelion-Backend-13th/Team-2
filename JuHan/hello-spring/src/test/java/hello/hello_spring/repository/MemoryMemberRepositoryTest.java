// 회원 리포지토리가 내가 원하는 방식대로 작동하는지 검증하는 테스트 케이스
// JUnit이라는 프레임워크 사용
/*
    모든 테스트의 순서는 보장이 안됨, 메서드 별로 다 따로 동작하게 설계
    하나의 테스트가 끝날 때마다 데이터를 클리어 해주어야 함
    테스트는 서로 순서와 관계없이, 의존 관계없이 설계되어야한다.
*/
// 순서를 뒤집어서 진행하는 TDD 방식도 있음

package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 콜뱅 메서드, 밑에 테스트 메서드가 끝날 때마다 실행 됨
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // 실무에서는 오류테스트 케이스 통과 못하면 다음 단계 못 넘어감
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result: " + (result == member));
        //Assertions.assertEquals(member, result);
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findById() {
        // member 1, member 2에 sping 1 spring 2라는 회원 가입
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        // spring 1을 찾는 것
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //회원 2명인지..
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
