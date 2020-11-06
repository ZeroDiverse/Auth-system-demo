package com.zerod.admindemo.fake;

import com.zerod.admindemo.models.Role;
import com.zerod.admindemo.models.User;
import com.zerod.admindemo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Component
@Service
@Slf4j
@AllArgsConstructor
public class FakeRunner implements CommandLineRunner {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder().userId(1L)
                .email("minhquan.nguyen.fr@gmail.com")
                .password(passwordEncoder.encode("Mary2810?"))
                .username("ZeroD")
                .role(Role.BOARD_OF_DIRECTOR)
                .created(Instant.now())
                .enabled(true)
                .build();
        System.out.println("Called");
        repository.save(user);
    }
}
