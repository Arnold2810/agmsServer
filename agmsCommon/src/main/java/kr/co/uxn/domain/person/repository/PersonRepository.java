package kr.co.uxn.domain.person.repository;

import kr.co.uxn.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
}

