package br.com.banco.transferencia;

import br.com.banco.dto.OperadorDto;
import br.com.banco.dto.PeriodoDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.utils.CriaConta;
import br.com.banco.utils.CriaTransferencia;
import br.com.banco.utils.RetornoPeriodo;
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
public class EncontrarTransferenciaPeloPeriodoTest {

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
    @Order(9)
    @DisplayName("9 - Deve encontrar as transferências pelo período com sucesso")
    void encontrarTransferenciaPeloOPeriodo() throws Exception {
        Conta conta = CriaConta.criarConta();
        contaRepository.save(conta);
        Transferencia transferencia = CriaTransferencia.criaTransferencia(conta);
        transferenciaRepository.save(transferencia);
        PeriodoDto periodo = new PeriodoDto(
                transferencia.getContaId(),
                transferencia.getDataTransferencia(),
                transferencia.getDataTransferencia()
        );
        RetornoPeriodo retorno = CriaTransferencia.periodoTest(periodo);

        mockMvc.perform(post("/transferencia/periodo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(retorno)))
                .andExpect(status().isOk());
    }
}
