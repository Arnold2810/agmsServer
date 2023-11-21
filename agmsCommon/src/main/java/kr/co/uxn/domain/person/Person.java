package kr.co.uxn.domain.person;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.OffsetDateTime;

//@Table(name = "users")//맵핑할 테이블을 지정
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert//nullable 인 컬럼을 DDL 에서 default 값으로 변경 가능하도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
//@Builder
public class Person {//implements UserDetails {//UserDetails 을 상속받아 인증 객체로 시용

    @Builder
    public Person(String name, String email, String password, boolean isMale,
                  LocalDate birth, String phoneNumber, String authority) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isMale = isMale;
        this.birth = birth;
        this.authority = authority;
        this.phoneNumber = phoneNumber;
        this.isEnabled = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

//    @Email
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "is_male", columnDefinition = "boolean default true", nullable = false)
    private boolean isMale = true;

    @Column(name = "birth")
    private LocalDate birth;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled = true;

    @Column(name = "email_verify_code")
    private String emailVerifyCode;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "expired_at")
    private LocalDate expiredAt;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;//localtime + timezone

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("user"));
//    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(()-> "ROLE_"+ authority);
//        authorities.add(()-> authority.name());
//        return authorities;
//    }
//
//    //사용자 id 반환(고유해야함 - email 을 사용자 ID로 사용)
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    //패스워드 반환
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    //계정 만료 여부 반환
//    @Override
//    public boolean isAccountNonExpired() {
//        return isEnabled;
//    }
//
//    //계정 잠금 여부 반환
//    @Override
//    public boolean isAccountNonLocked() {
//        //계정 잠금 확인 로직 구현
//        return isEnabled;
//    }
//
//    //패스워드의 만료 여부 반환
//    @Override
//    public boolean isCredentialsNonExpired() {
//        //패스워드 만료 확인 로직 구현
//        //예 - 패스워드 설정 후 6개월 경과 시 다시 설정하도록 하기 위해 사용
//
//        return isEnabled;
//    }
//
//    //계정 사용 가능 여부
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
}
