package kr.co.uxn.service.member;

import kr.co.uxn.domain.member.Members;
import kr.co.uxn.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Members loadUserByUsername(String email) {
        log.info("MemberDetailService loadUserByUsername : "+email);

        Members member = memberRepository.findByEmail(email);
        System.out.println("member email : " + email);

        if(member==null){
            throw new UsernameNotFoundException("Check Member's Email");
        }
        return member;
    }
}
