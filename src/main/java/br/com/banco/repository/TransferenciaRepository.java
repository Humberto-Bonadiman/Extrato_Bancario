package br.com.banco.repository;

import br.com.banco.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
    List<Transferencia> findAllByContaId(int contaId);

    @Query(value = "SELECT t FROM Transferencia t " +
            "WHERE t.contaId = ?1 " +
            "AND t.dataTransferencia BETWEEN ?2 AND ?3")
    List<Transferencia> filterAllByPeriodOfTime(
            int contaId,
            LocalDate dataInicial,
            LocalDate dataFinal
    );
}
