// 메모리 회원 저장소
// DB에 저장하는 것이 아닌 test용 메모리 저장소 -> 서버를 종료하면 날아가는 저장소
package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    // HashMap은 동시성 이슈가 있음
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
