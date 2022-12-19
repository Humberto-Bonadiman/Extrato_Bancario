package br.com.banco.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConta;

    @Column(nullable = false, length = 50)
    private String nomeResponsavel;

    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Transferencia transferencia;

    public Conta (String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }
}
