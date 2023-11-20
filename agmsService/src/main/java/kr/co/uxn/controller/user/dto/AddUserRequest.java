package kr.co.uxn.controller.user.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.co.uxn.domain.user.User;
import kr.co.uxn.domain.user.Authority;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserRequest {
    private String name;
    private String email;
    private String password;

    private boolean isMale;
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .isMale(isMale)
                .birth(birth)
                .isEnabled(true)
                .authority(authority)
                .build();
    }
}
