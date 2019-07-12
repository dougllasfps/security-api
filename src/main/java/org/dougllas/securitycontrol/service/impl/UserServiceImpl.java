package org.dougllas.securitycontrol.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dougllas.securitycontrol.exception.auth.AuthenticationException;
import org.dougllas.securitycontrol.exception.auth.Reason;
import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.model.repository.UserRepository;
import org.dougllas.securitycontrol.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/**
 * Criado por dougllas.sousa em 14/03/2018.
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User save(User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        userRepository.save(user);
        return user;
    }

    @Override
    public User authenticate(String login, String senha) {
        User user = loadUserByUsername(login);

        if(user == null){
            throw new AuthenticationException(Reason.USER_NOT_FOUND);
        }

        boolean matches = passwordEncoder.matches(senha, user.getPassword());

        if(matches){
            return user;
        }

        throw new AuthenticationException(Reason.PASSWORD_DOESNT_MATCH);
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

	@Override
	public List<User> find(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
}