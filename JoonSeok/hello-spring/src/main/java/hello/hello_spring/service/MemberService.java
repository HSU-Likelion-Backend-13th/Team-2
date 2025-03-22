package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // Dependency Injection - 종속성 주입
    }

    /*
     *  회원가입
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원 제한
        validateDuplicateMamber(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMamber(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }
    /*
     * 전체 회원 조회 - service. So, business적 표현을 쓰는게 좋다 / repository는 개발자답게 써도 됨
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
