package br.com.banco.conta;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class CriarContaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ContaRepository contaRepository;

    @BeforeEach
     public void setup() {
         contaRepository.deleteAll();
     }

    @AfterEach
    public void afterEach() {
        contaRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("1 - Deve criar uma conta com sucesso")
    void registerPersonSuccessfully() throws Exception {
        final Conta conta = new Conta("Júlio Teste da Silva");
        contaRepository.save(conta);
        mockMvc.perform(post("/conta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(conta)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
