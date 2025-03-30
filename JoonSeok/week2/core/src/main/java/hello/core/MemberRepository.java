package hello.core;

import hello.core.member.Member;

public interface MemberRepository {
    // save
    void save(Member member);
    // find by ID
    Member findById(Long id);
}
