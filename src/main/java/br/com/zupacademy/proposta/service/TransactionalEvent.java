package br.com.zupacademy.proposta.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalEvent {
    @Transactional
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
