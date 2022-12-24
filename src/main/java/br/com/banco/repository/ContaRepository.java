package br.com.banco.repository;

import br.com.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Optional<Conta> findByNomeResponsavel(String nomeResponsavel);
}
