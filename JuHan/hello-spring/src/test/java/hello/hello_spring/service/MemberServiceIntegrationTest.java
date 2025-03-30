package hello.hello_spring.service;
// db와 연동하고 이런 걸 통합테스트라고 함
// 단위 단위 쪼개서 하는 순수테스트가 좋은 테스트일 확률이 높음
import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 테스트 중 DB에 저장된 데이터는 실제처럼 작동하지만,
// 테스트가 끝나면 자동으로 롤백되어 DB에 반영되지 않고 사라짐.
@Transactional
class MemberServiceIntegrationTest {
// 테스트는 제일 끝단에 있는 것이기 때문에 테스트 코드를 만들 때는 제일 편한 방식 사용
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

/*    //메모리 db에 있는 데이터 다음 테스트에 영향없이 지우는 것 , transctional이 있으므로 필요없어진 코드
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }*/

    @Test
        //test는 직관적으로 알아볼 수 있게 한국어로 적어도 됨
    void 회원가입() {

        //Given - 테스트 전 조건 설정
        Member member = new Member();
        member.setName("spring");

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



        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                memberService.join(memeber2)); // 예외가 발생해야한다.

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }}

