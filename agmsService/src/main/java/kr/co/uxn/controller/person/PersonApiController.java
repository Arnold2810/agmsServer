package kr.co.uxn.controller.person;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.uxn.controller.error.ErrorCode;
import kr.co.uxn.controller.person.dto.AddPersonRequest;
import kr.co.uxn.domain.person.Person;
import lombok.RequiredArgsConstructor;
import kr.co.uxn.service.person.PersonService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class PersonApiController {

    private final PersonService personService;

    @PostMapping("/user")
//    @ApiOperation(value = "유저 등록")
    public String signup(AddPersonRequest request) {

        //이미 가입된 사람인지 체크
        Person person = personService.findByEmail(request.getEmail());

        if(person == null) {
            //Test - 추가 정보
            request.setBirth(LocalDate.now());
            request.setMale(true);
            request.setPhoneNumber("010-1234-5778");

            //authority(PATIENT or STAFF)에 따라 분기
            //가입 경로가 다를 것이다.
            request.setAuthority("PATIENT");
//            request.setAuthority("STAFF");

            personService.savePerson(request);
            return "redirect:/login";
        }
        else{
//            log.info("이메일 중복");
            throw new LoginException("Duplicate Email", ErrorCode.DUPLICATE_ACCOUNT);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Exception Handler 만들어 줄것.
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}
