package br.com.banco.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(
        name = "seq_generator",
        sequenceName = "SEQ_CONTA",
        initialValue = 3,
        allocationSize = 1
)
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConta;

    @Column(nullable = false, length = 50)
    private String nomeResponsavel;

    @JsonManagedReference
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transferencia> transferencia = new ArrayList<Transferencia>();

    public Conta (String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public void addAttendance(@NotNull Transferencia transferencia) {
        transferencia.setConta(this);
        this.transferencia.add(transferencia);
    }
}
