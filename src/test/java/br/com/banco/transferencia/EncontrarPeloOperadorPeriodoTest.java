package br.com.banco.transferencia;

import br.com.banco.dto.FiltroCompletoDto;
import br.com.banco.dto.OperadorDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.utils.CriaConta;
import br.com.banco.utils.CriaTransferencia;
import br.com.banco.utils.RetornoFiltroCompleto;
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
public class EncontrarPeloOperadorPeriodoTest {

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
    @Order(10)
    @DisplayName("10 - Deve encontrar as transferências pelo operador e período com sucesso")
    void encontrarTransferenciaPeloOperadorPeriodo() throws Exception {
        Conta conta = CriaConta.criarConta();
        contaRepository.save(conta);
        Transferencia transferencia = CriaTransferencia.criaTransferencia(conta);
        transferenciaRepository.save(transferencia);
        FiltroCompletoDto filtroCompleto = new FiltroCompletoDto(
                transferencia.getContaId(),
                transferencia.getDataTransferencia(),
                transferencia.getDataTransferencia(),
                transferencia.getNomeOperadorTransacao()
        );
        RetornoFiltroCompleto retorno = CriaTransferencia.filtroTest(filtroCompleto);

        mockMvc.perform(post("/transferencia/operador/periodo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(retorno)))
                .andExpect(status().isOk());
    }
}
