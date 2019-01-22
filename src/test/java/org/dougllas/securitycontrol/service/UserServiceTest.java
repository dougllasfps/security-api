package org.dougllas.securitycontrol.service;

import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.model.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Test
    public void testCadastrarUsuario(){
        User user = criarUsuario();
        String passCodificado = "321";

        given(userRepository.save(Mockito.any(User.class))).willReturn(user);
        given(passwordEncoder.encode(any(String.class))).willReturn(passCodificado);

        User usuarioCadastrado = userService.cadastrarUsuario(user);

        then(usuarioCadastrado.getPassword())
                .as("Password deve estar codificado")
                .isEqualTo(passCodificado);

    }

    @Test
    public void testAutenticarUsuario(){
        User user = criarUsuario();

        given(userRepository.findByUsername(anyString())).willReturn(Optional.of(user));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);

        Optional<User> optionalUser = userService.autenticarUsuario(user.getUsername(), user.getPassword());

        then(optionalUser.get())
                .as("Verificar se retornou um usuario autenticado")
                .isEqualTo(user);
    }

    private User criarUsuario() {
        return User.builder().id(1L).username("dougllas").name("Dougllas").password("123").build();
    }
}