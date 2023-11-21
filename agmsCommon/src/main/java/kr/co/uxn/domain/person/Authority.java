package kr.co.uxn.domain.person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    PATIENT("ROLE_PATIENT", "수검자"),
    STAFF("ROLE_STAFF", "병원관계자"),
    ADMIN("ROLE_ADMIN", "관리자");
//    GUEST("ROLE_GUEST", "비회원");


    private final String key;

    private final String value;
}
