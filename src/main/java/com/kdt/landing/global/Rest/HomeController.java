package com.kdt.landing.global.Rest;

import com.kdt.landing.domain.member.entity.Member;
import com.kdt.landing.domain.member.repository.MemberRepository;
import com.kdt.landing.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.jsp.PageContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Optional;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;



    //jsp index
    @GetMapping("/")
    public String home(Principal principal,Model model) {
        Optional<Member> member = Optional.of(new Member());
        if (principal != null) {
            String id = principal.getName();
            member = memberRepository.findById(Long.valueOf(id));
        }
        model.addAttribute("member", member);
        memberService.createAdminMember(); // 관리자 회원 생성 메서드 호출
        return "/index";
    }

    @GetMapping("/subSign")
    public String subSign(Model model) {
        return "main/sign/subSign";
    }

    @GetMapping("/fullStack")
    public String fullStack(Model model) {
        return "sub/fullstack";
    }
}