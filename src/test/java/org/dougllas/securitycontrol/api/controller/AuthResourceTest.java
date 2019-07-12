package org.dougllas.securitycontrol.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Criado por dougllas.sousa em 22/01/2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AuthResourceTest {

    @MockBean
    UserService userService;

    @Autowired
    private MockMvc mvc;

    User user;

    static final String ENDPOINT = "/api/users/";

    @Before
    public void setUp(){
        user = User.builder().id(1L).username("dougllas").name("Dougllas").password("123").build();
    }

    @Test
    public void deveRetornarUsuarioLogadoComToken() throws Exception {

        given(userService.authenticate(anyString(), anyString())).willReturn(user);

        mvc.perform(
            post(ENDPOINT + "/auth")
                .param("username", user.getUsername())
                .param("password", user.getPassword())

        ).andExpect(status().isOk())

         .andExpect(jsonPath("$.token").isNotEmpty())
         .andExpect(jsonPath("$.name").value(user.getName()));

    }

    @Test
    public void deveRetornarErroDeAutorizacaoQuandoUsuarioForInvalido() throws Exception {

        given(userService.authenticate(anyString(), anyString())).willReturn(null);

        mvc.perform(
                post(ENDPOINT + "/auth")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())

        ).andExpect(status().isUnauthorized())

                .andExpect(jsonPath("$.messages[0]").value("Usuário e/ou senha não conferem."));

    }

    @Test
    public void deveLancarErroAoCadastrarUsuarioSemUserNameOUSenha() throws Exception {

        User emptyUser = new User();

        mvc.perform(
                post(ENDPOINT + "/signup")
                    .content(new ObjectMapper().writeValueAsString(emptyUser))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest())
                .andExpect( jsonPath("$.messages[0]").value("Campo senha é obrigatório."))
                .andExpect( jsonPath("$.messages[1]").value("Campo login é obrigatório."))
        ;

    }

    @Test
    public void deveCadastrarUsuario() throws Exception {
        given(userService.save(any(User.class))).willReturn(user);

        mvc.perform(
                post(ENDPOINT + "/signup")
                        .content(new ObjectMapper().writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
         .andExpect(jsonPath("$.name").value(user.getName()))
         .andExpect(jsonPath("$.token").isNotEmpty());
    }
}