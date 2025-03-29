package hello.core.repository;

import hello.core.domain.Member;

public interface MemberRepository {
    //새로원 회원 DB 저장
    void save(Member member);


    // memberId로 DB에서 회원 조회
    Member findById(Long id);
}
