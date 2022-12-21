package br.com.banco.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@SequenceGenerator(
    name = "seq_generator",
    sequenceName = "SEQ_TRANSFERENCIA",
    initialValue = 7,
    allocationSize = 1
 )
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate dataTransferencia;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false, length = 15)
    private String tipo;

    @Column(length = 50)
    private String nomeOperadorTransacao;

    @Column(nullable = false, name = "conta_id")
    private int contaId;

    @JsonBackReference
    @JoinColumn(name = "conta")
    @ManyToOne(fetch = FetchType.EAGER)
    private Conta conta;

    public Transferencia(
            LocalDate dataTransferencia,
            double valor,
            String tipo,
            String nomeOperadorTransacao,
            int contaId
    ) {
        this.dataTransferencia = dataTransferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.contaId = contaId;
    }

    public Transferencia(
            LocalDate dataTransferencia,
            double valor,
            String tipo,
            String nomeOperadorTransacao,
            int contaId,
            Conta conta
    ) {
        this.dataTransferencia = dataTransferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.contaId = contaId;
        this.conta = conta;
    }
}
