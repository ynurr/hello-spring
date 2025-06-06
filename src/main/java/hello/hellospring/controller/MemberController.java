package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired // memberService를 스프링이 스프링 컨테이너에 있는 MemberService 가져다가 연결해준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) { // Model : 데이터를 담는 그릇 역할, map 구조로 저장됨// key와 val
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // model.addAttribute("변수명", 값)
        return "members/memberList";
    }
}
