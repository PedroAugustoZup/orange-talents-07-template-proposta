package br.com.zupacademy.proposta.repository;

import br.com.zupacademy.proposta.model.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
