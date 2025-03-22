package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


// 통합 테스트 - 컨테이너까지 로딩해야 한다면 잘못됐을 가능성이 있음.
// 물론 해야할 때도 있지만서도 단위테스트로 끝낼만하면 단위로 해보자.
@SpringBootTest
@Transactional // 기똥찬 기능. "테스트 케이스에" 붙었을 때만 DB에 커밋 안하게 함.
class MemberServiceIntergrationTest {
    // test는 한글로 적어도 됨 !
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() { // 보통은 테스트용 로컬 Or 원격 DB를 운용한다.
        // given data
        Member member = new Member();
        member.setName("spring");

        // when run
        Long saveId = memberService.join(member);

        // then this result
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}
