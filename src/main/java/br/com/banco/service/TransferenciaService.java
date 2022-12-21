package br.com.banco.service;

import br.com.banco.dto.FiltroDto;
import br.com.banco.dto.OperadorDto;
import br.com.banco.dto.PeriodoDto;
import br.com.banco.dto.TransferenciaDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService implements TransferenciaInterface {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Transferencia criar(@NotNull TransferenciaDto transferenciaDto) {
        Conta conta = contaRepository.findById(transferenciaDto.getContaId()).get();
        Transferencia transferencia = new Transferencia(
                transferenciaDto.getDataTransferencia(),
                transferenciaDto.getValor(),
                transferenciaDto.getTipo(),
                transferenciaDto.getNomeOperadorTransacao(),
                transferenciaDto.getContaId(),
                conta
        );
        conta.addAttendance(transferencia);
        transferenciaRepository.save(transferencia);
        return transferencia;
    }

    @Override
    public List<Transferencia> encontrarTransferenciasPeloIdConta(int idConta) {
        return encontrarTodosPelaContaId(idConta);
    }

    @Override
    public List<Transferencia> encontrarPeloNomeOperador(int idConta, OperadorDto operadorDto) {
        List<Transferencia> encontradosPelaContaId = encontrarTodosPelaContaId(idConta);
        return filtrarNomeOperador(encontradosPelaContaId, operadorDto.getNomeOperadorTransacao());
    }

    @Override
    public List<Transferencia> filtrarPeloPeriodoTempo(
            int idConta,
            PeriodoDto periodoDto
    ) {
        return transferenciaRepository.filterAllByPeriodOfTime(
                idConta,
                periodoDto.getDataInicial(),
                periodoDto.getDataFinal()
        );
    }

    @Override
    public List<Transferencia> filtrarPeloTempoEOperador(
            int idConta,
            FiltroDto filtroDto
    ) {
        List<Transferencia> transferencias = transferenciaRepository.filterAllByPeriodOfTime(
                idConta,
                filtroDto.getDataInicial(),
                filtroDto.getDataFinal()
        );
        return filtrarNomeOperador(transferencias, filtroDto.getNomeOperadorTransacao());
    }

    private List<Transferencia> encontrarTodosPelaContaId(int idConta) {
        return transferenciaRepository.findAllByContaId(idConta);
    }

    private @Nullable List<Transferencia> filtrarNomeOperador(
            @NotNull List<Transferencia> lista,
            String nomeOperadorTransacao
    ) {
        if (lista.size() == 0) {
            return null;
        }
        return lista.stream()
                .filter(operador -> Objects.equals(
                        operador.getNomeOperadorTransacao(),
                        nomeOperadorTransacao
                ))
                .collect(Collectors.toList());
    }
}
