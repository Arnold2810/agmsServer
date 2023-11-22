package kr.co.uxn.service.member;

import kr.co.uxn.Constants;
import kr.co.uxn.controller.member.dto.AddMemberReqDto;
import kr.co.uxn.domain.member.Members;
import kr.co.uxn.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional//트랜잭션 원칙을 지키도록 보장(트랜잭션 - 모든 과정은 독립적, 하나라도 실패 시 모두 취소 됨)
    public Members findByEmail(String email){
        return memberRepository.findByEmail(email);

//        return memberRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException((email)));
    }

    @Transactional
    public Members saveUser(Members member) {

        return memberRepository.save(member);
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteUser(Members member) {
        //테이블 의존성을 고려하여 순서대로 삭제 필요

        String authority = member.getAuthority();
        String tmp2 = Constants.AUTHORITY.PATIENT.getValue();
        //PATIENT 인 경우
        if(authority.equals(Constants.AUTHORITY.PATIENT.getValue())){
            //patient activity 삭제

            //device value 삭제

            //staff mapping 삭제

            //event note 삭제

            //invitation list 삭제

            //patient 삭제

            //member 삭제

            memberRepository.delete(member);
            memberRepository.flush();
        }

        //STAFF 인 경우
        else if(authority.equals(Constants.AUTHORITY.STAFF.getValue())){
            //staff activity 삭제

            //invitation list 삭제

            //patient mapping 삭제

            //hospital mapping 삭제

            //staff 삭제

            memberRepository.delete(member);
            memberRepository.flush();
        }

        //ADMIN 인 경우
        else if(authority.equals(Constants.AUTHORITY.ADMIN.getValue())){
            log.info("admin member delete");
        }

        else{
            log.info("member delete error");
        }

    }
}
