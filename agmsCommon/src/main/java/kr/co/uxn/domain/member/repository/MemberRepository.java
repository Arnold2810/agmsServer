package kr.co.uxn.domain.member.repository;

import kr.co.uxn.domain.member.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Integer> {
    Members findByEmail(String email);
}

