package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음
 * -> 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // ststic 으로 생성했기 때문에 MemberRepository 가 아무리 new 로 많아도 딱 하나만 생성된다. ... 싱글톤
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    //싱글톤을 만들 때에는 private 으로 생성자를 막아야 한다. = 아무나 생성하지 못하도록 한다.
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        //새로운 arraylist 로 만들어서 반환하는 이유 ?
        //=> store 자체를 보호 즉, 값들만 추출하기 위함 (수정하면 값이 바뀔 수 있기 때문에 이를 방지하기 위함)
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
