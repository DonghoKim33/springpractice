package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store의 모든 값들을 스트림으로 변환
                .filter(member -> member.getName().equals(name))  // 각 회원의 이름이 주어진 이름과 같은지 필터링
                .findAny(); // 필터링된 값 중 하나를 반환
    }
    /**
     * findByName("Alice")를 호출하면:
     *
     * store.values()는 [Member(1L, "Alice"), Member(2L, "Bob")]를 반환합니다.
     * 스트림을 통해 각 회원의 이름을 비교합니다.
     * Alice와 이름이 일치하는 회원이 필터링됩니다.
     * 필터링된 결과에서 임의의 하나를 반환합니다. 이 경우 Optional.of(Member(1L, "Alice"))를 반환합니다.
     * findByName("Charlie")를 호출하면:
     *
     * 이름이 Charlie인 회원이 없으므로 필터링 결과는 비어있습니다.
     * 따라서 Optional.empty()를 반환합니다.
     * **/

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    public void clearStore(){

        store.clear();
    }
}
