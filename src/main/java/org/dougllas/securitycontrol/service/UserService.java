package org.dougllas.securitycontrol.service;

import org.dougllas.securitycontrol.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * Criado por dougllas.sousa em 14/03/2018.
 */
public interface UserService extends UserDetailsService {

    User cadastrarUsuario(User user);

    List<User> findAll();

    Optional<User> autenticarUsuario(String login, String senha);

    Optional<User> findUserById(Long id);

}