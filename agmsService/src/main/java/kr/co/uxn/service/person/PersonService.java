package kr.co.uxn.service.person;

import kr.co.uxn.controller.person.dto.AddPersonRequest;
import kr.co.uxn.domain.person.Person;
import kr.co.uxn.domain.person.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Person findByEmail(String email){
        return personRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
    public Person savePerson(AddPersonRequest dto) {

//        return personRepository.save(dto.toEntity());

        return personRepository.save(Person.builder()
                .name(dto.getName())
                .email(dto.getEmail())
//                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .isMale(dto.isMale())
                .password(dto.getPassword())
                .birth(dto.getBirth())
                .authority(dto.getAuthority())
                .build());
    }
}
