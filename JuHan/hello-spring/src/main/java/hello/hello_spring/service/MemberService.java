package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스는 메서드 명을 기계적이지 않고 비즈니스 느낌으로 적어야 편리함
// command + shift + t -> 테스트 클래스 자동 생성 가능

@Transactional
public class MemberService {

    // final 키워드가 붙으면 더는 값을 변경할 수 없다.
    // FIX. DI(dependency injection) 형태 -> 생성자 주입
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    //같은 이름이 있는 중복 회원X
    public Long join(Member member) {

        // 1) 기본 형태
        /*Optional<Member> result = memberRepository.findByName(member.getName());
            // ifPresent 는 null이 아닌 값이 있으면 동작, Optional이 있기에 가능
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다." );
        });*/


        //2) 단순화
        /*memberRepository.findByName(member.getName())
                .ifPresent(m->{throw new IllegalStateException("이미 존재하는 회원입니다." );
                });*/


        // 3) 다른 메서드 생성
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{throw new IllegalStateException("이미 존재하는 회원입니다." );
    });
    }

    //전체 멤버 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
