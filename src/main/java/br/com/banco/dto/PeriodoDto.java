package br.com.banco.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PeriodoDto {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
}
