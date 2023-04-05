package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration // 스프링 빈으로 관리된다
public class SpringConfig {

    private DataSource dataSource; // 스프링이 제공해준다(스프링부트가 application.properties 보고 스프링이 자체적으로 빈 생성, 데이터베이스와 연결할 수 있는 dataSource를 만들어준다)

    @Autowired
    public SpringConfig(DataSource dataSource) { // 만들어준 dataSource를 주입 (DI)
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository(); // 인터페이스는 new 안 되므로 구현체 사용
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
