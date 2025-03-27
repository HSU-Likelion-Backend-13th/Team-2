package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;
//Map, HashMap, Optional, List 등 여러 유틸리티 클래스를 쓰기 위한 것
import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// MemoryMemberRepository는 MemberRepository라는 인터페이스 구현


public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //회원 저장
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    // id로 찾기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 모든 회원 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 이름으로 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    //저장소 비우기
    public void clearStore() {
        store.clear();
    }


}
