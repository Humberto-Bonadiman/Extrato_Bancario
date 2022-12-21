package br.com.banco.transferencia;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.utils.CriaConta;
import br.com.banco.utils.CriaTransferencia;
import br.com.banco.utils.RetornoTransferencia;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CriarTransferenciaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @BeforeEach
    public void setup() {
        contaRepository.deleteAll();
        transferenciaRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        contaRepository.deleteAll();
        transferenciaRepository.deleteAll();
    }

    @Test
    @Order(5)
    @DisplayName("5 - Deve criar uma transferência com sucesso")
    void criarTransferencia() throws Exception {
        Conta conta = CriaConta.criarConta();
        contaRepository.save(conta);
        Transferencia transferencia = CriaTransferencia.criaTransferencia(conta);
        RetornoTransferencia retorno = CriaTransferencia.retorno(transferencia);
        mockMvc.perform(post("/transferencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(retorno)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
