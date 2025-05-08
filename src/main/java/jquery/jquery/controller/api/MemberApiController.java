package jquery.jquery.controller.api;

import jakarta.servlet.http.HttpSession;
import jquery.jquery.domain.Member;
import jquery.jquery.dto.MemberLoginDto;
import jquery.jquery.dto.MemberRegisterDto;
import jquery.jquery.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/register")
    public Member register(@RequestBody MemberRegisterDto dto) {
        return memberService.register(dto.getName(), dto.getEmail(), dto.getPassword(), dto.getRole());
    }

    @PostMapping("/api/login")
    public String login(@RequestBody MemberLoginDto dto, HttpSession session) {
        Member member = memberService.login(dto.getEmail(), dto.getPassword());
        if (member == null) {
            return "FAIL";
        }
        session.setAttribute("loginMember", member);
        return "SUCCESS";
    }

    @PostMapping("/api/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "SUCCESS";
    }
}