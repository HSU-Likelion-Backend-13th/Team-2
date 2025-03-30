package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 현재 클래스는 MemberRepository Interface를 의존
    // MemberRepository 는 구현체를 참조 중
    // >> 구체화, 추상화 모두 사용중
    // >> OCP, DIP 위배
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
         memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
