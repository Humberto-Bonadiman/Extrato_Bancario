package br.com.banco.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class RetornoTransferencia {
    private int id;
    private String dataTransferencia;
    private double valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private int contaId;
}
