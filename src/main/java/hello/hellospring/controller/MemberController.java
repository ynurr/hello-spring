package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // memberService를 스프링이 스프링 컨테이너에 있는 MemberService 가져다가 연결해준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
