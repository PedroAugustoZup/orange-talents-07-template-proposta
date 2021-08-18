package br.com.zupacademy.proposta.repository;

import br.com.zupacademy.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
