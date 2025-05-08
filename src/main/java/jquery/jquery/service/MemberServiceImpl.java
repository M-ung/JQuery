package jquery.jquery.service;

import jquery.jquery.domain.Member;
import jquery.jquery.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member register(String name, String email, String password, String role) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .build();

        return memberRepository.save(member);
    }

    @Override
    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}