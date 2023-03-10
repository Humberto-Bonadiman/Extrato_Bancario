package br.com.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDto {
    private LocalDate dataTransferencia;
    private double valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private int contaId;
}
