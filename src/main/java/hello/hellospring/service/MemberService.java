package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // 데이터를 저장하고 변경할 때 항상 필요
//@Service // 스프링이 실행될 때 서비스인 거 알고 스프링에 등록
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired // 이 생성자(MemberService) 호출할 때 @Autowired가 있으면 MemberRepository가 필요하구나 하고 스프링 컨테이너에 넣어준다
    public MemberService(MemberRepository memberRepository) { // DI(Dependency Injection)
        this.memberRepository = memberRepository;
    }

    /* 회원가입 */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        /*
        아래 validateDuplicateMember 메소드와 동일함
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // ifPresent:값을 가지고 있는지 확인 후 예외처리
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        validateDuplicateMember(member); // 중복 회원 검증 (메소드로 만듦)
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /*  전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
