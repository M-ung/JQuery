package jquery.jquery.service;

import jquery.jquery.domain.Member;

public interface MemberService {
    Member register(String name, String email, String password, String role);
    Member login(String email, String password);
}