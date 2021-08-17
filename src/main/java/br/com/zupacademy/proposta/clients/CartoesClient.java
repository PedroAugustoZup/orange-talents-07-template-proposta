package br.com.zupacademy.proposta.clients;

import br.com.zupacademy.proposta.dto.response.ApiCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoesClient",url = "http://localhost:8888/api")
public interface CartoesClient {
    @GetMapping("/cartoes")
    ApiCartaoResponse salvaNumeroCartao(@RequestParam String idProposta);
}
