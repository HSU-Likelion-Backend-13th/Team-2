package hello.core.member;

public interface MemberRepository {
    // save
    void save(Member member);
    // find by ID
    Member findById(Long id);
}
