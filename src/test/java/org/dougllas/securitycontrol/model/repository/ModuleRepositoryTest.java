package org.dougllas.securitycontrol.model.repository;

import org.dougllas.securitycontrol.model.entity.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ModuleRepositoryTest {

    @Autowired
    ModuleRepository moduleRepository;

    @Test
    public void deveSalvarUmModulo(){
        Module m = new Module();
        m.setName("Some module");

        moduleRepository.save(m);
        assertNotNull(m.getId());
    }
}