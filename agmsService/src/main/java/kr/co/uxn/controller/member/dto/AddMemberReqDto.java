package kr.co.uxn.controller.member.dto;

import kr.co.uxn.domain.member.Members;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMemberReqDto {
    private String name;
    private String email;
    private boolean isMale;
    private String password;
    private LocalDate birth;
    private String phoneNumber;
    private boolean isEnabled;
    private String authority;

    public Members toEntity() {
        return Members.builder()
                .name(name)
                .email(email)
                .isMale(isMale)
                .password(password)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .authority(authority)
                .isEnabled(isEnabled)
                .build();
    }
}
