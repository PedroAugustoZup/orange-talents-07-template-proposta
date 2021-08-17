package br.com.zupacademy.proposta.repository;

import br.com.zupacademy.proposta.model.Proposta;
import br.com.zupacademy.proposta.model.enums.EstadoProposta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    List<Proposta> findByCartaoIsNullAndEstadoPropostaIs(EstadoProposta elegivel);
}
