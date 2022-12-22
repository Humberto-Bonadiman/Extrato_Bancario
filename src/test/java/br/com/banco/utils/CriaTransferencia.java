package br.com.banco.utils;

import br.com.banco.dto.FiltroCompletoDto;
import br.com.banco.dto.PeriodoDto;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CriaTransferencia {
    public static @NotNull Transferencia criaTransferencia(@NotNull Conta conta) {
        LocalDate localDate = LocalDate.of(2022, 01, 13);
        Transferencia transferencia = new Transferencia(
                localDate,
                4000,
                "ENTRADA",
                "Teste do Teste",
                conta.getIdConta(),
                conta
        );
        return transferencia;
    }

    public static RetornoTransferencia retorno(Transferencia transferencia) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = transferencia.getDataTransferencia().format(formatter);
        RetornoTransferencia retorno = new RetornoTransferencia(
                transferencia.getId(),
                formattedString,
                transferencia.getValor(),
                transferencia.getTipo(),
                transferencia.getNomeOperadorTransacao(),
                transferencia.getContaId()
        );
        return  retorno;
    }

    public static RetornoPeriodo periodoTest(PeriodoDto periodoDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataInicial = periodoDto.getDataInicial().format(formatter);
        String dataFinal = periodoDto.getDataFinal().format(formatter);
        RetornoPeriodo retorno = new RetornoPeriodo(
                periodoDto.getIdConta(),
                dataInicial,
                dataFinal
        );
        return retorno;
    }

    public static RetornoFiltroCompleto filtroTest(FiltroCompletoDto filtro) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataInicial = filtro.getDataInicial().format(formatter);
        String dataFinal = filtro.getDataFinal().format(formatter);
        RetornoFiltroCompleto retorno = new RetornoFiltroCompleto(
                filtro.getIdConta(),
                dataInicial,
                dataFinal,
                filtro.getNomeOperadorTransacao()
        );
        return retorno;
    }
}
