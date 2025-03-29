package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
        //test는 직관적으로 알아볼 수 있게 한국어로 적어도 됨
    void 회원가입() {

        //Given - 테스트 전 조건 설정
        Member member = new Member();
        member.setName("hello");

        //When - 테스트 대상 행동을 실행
        Long saveId = memberService.join(member);

        //Then - 내가 기대한대로 나왔는지 확인
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {

        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member memeber2 = new Member();
        memeber2.setName("spring");


        /*//1)
        try{
            memberService.join(memeber2);
            fail("예외가 발생해야합니다.");
        } catch (IllegalStateException e) {
            //validateDuplicateMember의 문자와 같아야 한다.
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                memberService.join(memeber2)); // 예외가 발생해야한다.

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }}

