package br.com.zupacademy.proposta.clients;

import br.com.zupacademy.proposta.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "analiseFinanceiraClient",url = "http://localhost:9999/api")
public interface AnaliseFinanceiraClient {
    @PostMapping("/solicitacao")
    ApiResponse analisa(Map<String, Object> request);
}
