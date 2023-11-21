package kr.co.uxn.controller.person.dto;

import kr.co.uxn.domain.person.Person;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddPersonRequest {
    private String name;
    private String email;
    private boolean isMale;
    private String password;
    private LocalDate birth;
    private String phoneNumber;
    private String authority;

    public Person toEntity() {
        return Person.builder()
                .name(name)
                .email(email)
                .isMale(isMale)
                .password(password)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .authority(authority)
//                .isEnabled(true)
                .build();
    }
}
