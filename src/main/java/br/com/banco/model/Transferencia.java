package br.com.banco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataTransferencia;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false, length = 15)
    private String tipo;

    @Column(length = 50)
    private String nomeOperadorTransacao;

    @JoinColumn(name = "id_conta")
    @OneToOne(fetch = FetchType.LAZY)
    private Conta conta;
}
