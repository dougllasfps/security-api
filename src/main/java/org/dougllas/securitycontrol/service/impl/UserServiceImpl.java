package org.dougllas.securitycontrol.service.impl;

import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.model.repository.UserRepository;
import org.dougllas.securitycontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Criado por dougllas.sousa em 14/03/2018.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User cadastrarUsuario(User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> autenticarUsuario(String login, String senha) {
        User user = loadUserByUsername(login);

        if(user == null){
            return Optional.empty();
        }

        boolean matches = passwordEncoder.matches(senha, user.getPassword());

        if(matches){
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable( userRepository.getOne(id) );
    }

    @Override
    @Transactional
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);
        return user.isPresent() ? user.get() : null;
    }
}