package org.dougllas.securitycontrol.security.jwt;

import org.dougllas.securitycontrol.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Criado por dougllas.sousa em 22/01/2019.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class JwtTokenServiceTest {

    @Autowired
    JwtTokenService jwtTokenService;

    String token;

    @Before
    private void setUp() {
        User user = User.builder().id(1L).username("dougllas").name("Dougllas").password("123").build();
        token = jwtTokenService.obterToken(user);
    }

    @Test
    public void deveGerarUmTokenValido(){
        boolean tokenValido = jwtTokenService.tokenValido(token);
        assertThat(tokenValido).isTrue();
    }
}