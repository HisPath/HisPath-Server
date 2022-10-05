package com.server.hispath.major.presentation;


import com.server.hispath.major.domain.Major;
import com.server.hispath.major.domain.repository.MajorRepository;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorTestController {

    private final MajorRepository majorRepository;

    @GetMapping("/init")
    public ResponseEntity<Void> testInit() {

        majorRepository.save(Major.builder().name("AI컴퓨터공학심화").build());
        majorRepository.save(Major.builder().name("전자공학심화").build());
        majorRepository.save(Major.builder().name("국제지역학").build());
        majorRepository.save(Major.builder().name("국제관계학").build());
        majorRepository.save(Major.builder().name("경영학").build());
        majorRepository.save(Major.builder().name("언론정보학").build());
        majorRepository.save(Major.builder().name("법학").build());
        majorRepository.save(Major.builder().name("ICT융합전공").build());
        majorRepository.save(Major.builder().name("생명과학").build());
        majorRepository.save(Major.builder().name("경제학").build());

        return ResponseEntity.ok(null);
    }
}
