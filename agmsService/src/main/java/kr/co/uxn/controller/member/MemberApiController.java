package kr.co.uxn.controller.member;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.uxn.Constants;
import kr.co.uxn.controller.error.ErrorCode;
import kr.co.uxn.controller.error.LoginException;
import kr.co.uxn.controller.member.dto.AddMemberReqDto;
import kr.co.uxn.domain.member.Members;
import kr.co.uxn.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.*;

@Tag(name = "UserAPI Controller", description = "사용자 등록 API입니다.")
//@RequestMapping("/api")//url
@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/user")
//    public ResponseEntity<Integer> signup(AddMemberReqDto reqDto) {
    public String signup(AddMemberReqDto reqDto) {

        //이미 가입된 사람인지 체크
        Members isMember = memberService.findByEmail(reqDto.getEmail());

        //Test용 임시
        reqDto.setMale(true);
        reqDto.setAuthority("PATIENT");//Test용 임시
//            reqDto.setAuthority("STAFF");//Test용 임시
        reqDto.setEnabled(true);

        //신규 회원 Case
        if(isMember == null) {
            //가입 경로에 따라 PATIENT 인지 STAFF 인지 구분
            String authority = reqDto.getAuthority();//PATIENT or STAFF?

            //사용자 추가
            Members member = Members
                    .builder()
                    .name(reqDto.getName())
                    .email(reqDto.getEmail())
//                    .password(reqDto.getPassword())
                    .password(bCryptPasswordEncoder.encode(reqDto.getPassword()))
                    .isMale(reqDto.isMale())
                    .birth(reqDto.getBirth())
                    .phoneNumber(reqDto.getPhoneNumber())
                    .isEnabled(true)
                    .authority(reqDto.getAuthority())
                    .build();

            memberService.saveUser(member);
            return "redirect:/login";
//            return ResponseEntity.ok(member.getId());
        }

        //가입 이력이 있는 경우, 재 가입 가능 상태인지 확인
        else{
            //가입 후 인증을 안 한 상태로 인증 시간이 지난 경우 기존 계정 삭제 후 재 가입 가능
            OffsetDateTime offsetDateTime = isMember.getCreatedAt();
            LocalDateTime localDateTime = offsetDateTime
                    .atZoneSameInstant(ZoneId.systemDefault())
                    .toLocalDateTime()
                    .plusMinutes(Constants.TIME_MIN_30);

            LocalDateTime now = LocalDateTime.now();

            if(!isMember.getIsVerified() || now.isAfter(localDateTime)){
                log.info("가입 이력이 있지만 인증 유효시간(30분)이 지난 상태로 기존 정보 삭제 후 재 가입");
                memberService.deleteUser(isMember);
                //사용자 추가
                Members member = Members
                        .builder()
                        .name(reqDto.getName())
                        .email(reqDto.getEmail())
                        .password(reqDto.getPassword())
//                    .password(passwordEncoder.encode(reqDto.getPassword()))
                        .isMale(reqDto.isMale())
                        .birth(reqDto.getBirth())
                        .phoneNumber(reqDto.getPhoneNumber())
                        .isEnabled(true)
                        .authority(reqDto.getAuthority())
                        .build();

                memberService.saveUser(member);
                return "redirect:/login";
//                return ResponseEntity.ok(member.getId());
            }
            log.warn("이미 가입된 이메일(중복), 회원 탈퇴 후 가입 가능");
            throw new LoginException("Duplicate Email", ErrorCode.DUPLICATE_ACCOUNT);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Exception Handler 만들어 줄것.
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
