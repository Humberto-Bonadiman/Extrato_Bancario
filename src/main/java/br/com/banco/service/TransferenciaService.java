package br.com.banco.service;

import br.com.banco.dto.*;
import br.com.banco.exception.messages.ContaNaoEncontradaException;
import br.com.banco.exception.messages.TransferenciaNaoEncontradaException;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransferenciaService implements TransferenciaInterface {

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @Autowired
    ContaRepository contaRepository;

    @Override
    public Transferencia criar(@NotNull TransferenciaDto transferenciaDto) {
        Conta conta = encontrarContaPeloId(transferenciaDto.getContaId());
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
    public Transferencia encontrarTransferenciasPeloId(int id) {
        Optional<Transferencia> transferencia = transferenciaRepository.findById(id);
        if (transferencia.isEmpty()) {
            throw new TransferenciaNaoEncontradaException();
        }
        return transferencia.get();
    }

    @Override
    public List<Transferencia> encontrarTransferenciasPeloIdConta(int idConta) {
        return encontrarTodosPelaContaId(idConta);
    }

    @Override
    public List<Transferencia> encontrarPeloNomeOperador(OperadorDto operadorDto) {
        List<Transferencia> encontradosPelaContaId = encontrarTodosPelaContaId(operadorDto.getIdConta());
        return filtrarNomeOperador(encontradosPelaContaId, operadorDto.getNomeOperadorTransacao());
    }

    @Override
    public List<Transferencia> filtrarPeloPeriodoTempo(
            PeriodoDto periodoDto
    ) {
        return transferenciaRepository.filterAllByPeriodOfTime(
                periodoDto.getIdConta(),
                periodoDto.getDataInicial(),
                periodoDto.getDataFinal()
        );
    }

    @Override
    public List<Transferencia> filtrarPeloTempoEOperador(
            FiltroCompletoDto filtroDto
    ) {
        List<Transferencia> transferencias = transferenciaRepository.filterAllByPeriodOfTime(
                filtroDto.getIdConta(),
                filtroDto.getDataInicial(),
                filtroDto.getDataFinal()
        );
        return filtrarNomeOperador(transferencias, filtroDto.getNomeOperadorTransacao());
    }

    @Override
    public void deletarTransferencia(int id) {
        transferenciaRepository.deleteById(id);
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

    private @NotNull Conta encontrarContaPeloId(int id) {
        Optional<Conta> contaValida = contaRepository.findById(id);
        if (contaValida.isEmpty()) {
            throw new ContaNaoEncontradaException();
        }
        return contaValida.get();
    }
}
