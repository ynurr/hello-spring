package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

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
        return store.values().stream() // stream 객체 생성
                .filter(member -> member.getName().equals(name)) // filter로 가공하여 member 객체에서 getName() 이름을 가져온 후, name과 동일한 것 탐색
                .findAny(); // 어떤 것이든 하나라도 찾아서 반환
                            // map에서 루프 돌면서 하나 찾으면 반환, 없으면 Optional에 null 포함돼서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
