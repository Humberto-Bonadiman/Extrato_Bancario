package br.com.banco.transferencia;

import br.com.banco.dto.OperadorDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.utils.CriaConta;
import br.com.banco.utils.CriaTransferencia;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EncontrarTransferenciasPeloOperadorTest {

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
    @Order(8)
    @DisplayName("8 - Deve encontrar as transferências pelo operador com sucesso")
    void encontrarTransferenciaPeloOperador() throws Exception {
        Conta conta = CriaConta.criarConta();
        contaRepository.save(conta);
        Transferencia transferencia = CriaTransferencia.criaTransferencia(conta);
        transferenciaRepository.save(transferencia);
        OperadorDto operador = new OperadorDto(
                transferencia.getContaId(),
                transferencia.getNomeOperadorTransacao()
        );

        mockMvc.perform(post("/transferencia/operador")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(operador)))
                .andExpect(status().isOk());
    }
}
