package com.oh.rep0531.service;

import com.oh.rep0531.Entity.UserEntity;
import com.oh.rep0531.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity){
        if(userEntity ==null || userEntity.getUsername() ==null){
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();
        if(userRepository.existsByUsername(username)){
            log.warn("Username already exists{}", username);
            throw new RuntimeException("Username already exists");
        }
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String username, final String password, final PasswordEncoder encoder){
        final UserEntity originalUser = userRepository.findByUsername(username);
        if(originalUser != null &&
        encoder.matches(password,
                originalUser.getPassword())){
            return originalUser;
        }
        return null;
    }


}
