package kr.co.uxn.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;

//@Table(name = "users")//맵핑할 테이블을 지정
@DynamicInsert//nullable 인 컬럼을 DDL 에서 default 값으로 변경 가능하도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
//@Builder
public class User implements UserDetails {//UserDetails 을 상속받아 인증 객체로 시용

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "is_male", columnDefinition = "integer boolean true", nullable = false)
    private boolean isMale = true;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Builder
    public User(String name, String email, String password, boolean isMale, LocalDate birth,
                boolean isEnabled, Authority authority) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isMale = isMale;
        this.birth = birth;
        this.isEnabled = isEnabled;
        this.authority = authority;
    }

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = true;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authority authority;

    @Column(name = "email_verify_code")
    private String emailVerifyCode;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("user"));
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(()-> "ROLE_"+ authority);
        authorities.add(()-> authority.name());
        return authorities;
    }

    //사용자 id 반환(고유해야함 - email 을 사용자 ID로 사용)
    @Override
    public String getUsername() {
        return email;
    }

    //패스워드 반환
    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        //계정 잠금 확인 로직 구현
        return isEnabled;
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        //패스워드 만료 확인 로직 구현
        //예 - 패스워드 설정 후 6개월 경과 시 다시 설정하도록 하기 위해 사용

        return isEnabled;
    }

    //계정 사용 가능 여부
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
