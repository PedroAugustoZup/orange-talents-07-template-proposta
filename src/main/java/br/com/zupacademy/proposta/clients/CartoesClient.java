package br.com.zupacademy.proposta.clients;

import br.com.zupacademy.proposta.dto.requeste.BloqueioRequest;
import br.com.zupacademy.proposta.dto.response.ApiCartaoResponse;
import br.com.zupacademy.proposta.dto.response.BloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoesClient",url = "${API_CARTOES_URL:http://localhost:8888/api}")
public interface CartoesClient {
    @GetMapping("/cartoes")
    ApiCartaoResponse salvaNumeroCartao(@RequestParam String idProposta);

    @PostMapping("/cartoes/{id}/bloqueios")
    BloqueioResponse geraBloqueioCartao(@PathVariable("id") String id, @RequestBody BloqueioRequest request);

}
