package org.dougllas.securitycontrol.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dougllas.securitycontrol.model.dto.ModuleDTO;
import org.dougllas.securitycontrol.model.entity.Module;
import org.dougllas.securitycontrol.service.ModuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ModuleControllerTest {

    private static final String ENDPOINT = "/api/modules/";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ModuleService moduleService;

    @Test
    public void testSalvarModulo() throws Exception {
        given(moduleService.save(any(Module.class))).willReturn(Module.builder().id(1L).name("test").build());

        String json = createValidObject();
        mvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
               .content( json )
               .contentType(APPLICATION_JSON)
               .accept(APPLICATION_JSON))
               .andExpect(status().is(HttpStatus.CREATED.value()))
               .andExpect(jsonPath("$.id").value(1L))
               .andExpect(jsonPath("$.name").value("test"));
    }

    @Test
    public void deveRetornarBadRequestComMensagemDeErroQuandoObjetoNaoEstiverValidoAoSalvar()  throws Exception{
        ModuleDTO dto = new ModuleDTO();
        String json = new ObjectMapper().writeValueAsString(dto);

        mvc.perform(
                post(ENDPOINT)
                .content(json)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        )
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.messages[0]").value("Campo nome é obrigatório."))
        ;

    }

    @Test
    public void testAtualizarModulo() throws Exception {
        Module object = Module.builder().id(1L).name("test").build();
        given(moduleService.findOne(Mockito.anyLong())).willReturn(Optional.of(object));

        String json = new ObjectMapper().writeValueAsString(ModuleDTO.builder().id(1L).name("Other Module").build());

        mvc.perform(put(ENDPOINT + "/" +1)
                .content( json )
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Other Module"));
    }

    @Test
    public void deveRetornarMensagemDeErroQuandoIdInexistenteAoAtualizar()  throws Exception{
        String json = createValidObject();

        given(moduleService.findOne(Mockito.anyLong())).willReturn(Optional.empty());

        mvc.perform(
                put(ENDPOINT + "/" +1)
                        .content(json)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
        )
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.messages[0]").value(ModuleController.MSG_NOT_FOUND))
        ;

    }

    private String createValidObject() throws JsonProcessingException {
        ModuleDTO dto = ModuleDTO.builder().name("test").build();
        return new ObjectMapper().writeValueAsString(dto);
    }
}