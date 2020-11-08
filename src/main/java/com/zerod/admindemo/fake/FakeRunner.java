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

/**
 * Create Fake runner to insert fake data
 */
@Component
@Service
@Slf4j
@AllArgsConstructor
public class FakeRunner implements CommandLineRunner {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //Create user level 0

        User user0 = User.builder().userId(1L)
                .email("minhquan.nguyen.fr@gmail.com")
                .password(passwordEncoder.encode("Mary2810?"))
                .username("ZeroD")
                .role(Role.LV0)
                .created(Instant.now())
                .enabled(true)
                .build();
        //Create user level 1
        User user1 = User.builder().userId(2L)
                .email("minhquan.nguyen.vi@gmail.com")
                .password(passwordEncoder.encode("Mary2810?"))
                .username("ZeroD")
                .role(Role.LV1)
                .created(Instant.now())
                .enabled(true)
                .build();
        //Create user level 2
        User user2 = User.builder().userId(2L)
                .email("minhquan.nguyen.en@gmail.com")
                .password(passwordEncoder.encode("Mary2810?"))
                .username("ZeroD")
                .role(Role.LV2)
                .created(Instant.now())
                .enabled(true)
                .build();
        repository.save(user0);
        repository.save(user1);
        repository.save(user2);

    }
}
