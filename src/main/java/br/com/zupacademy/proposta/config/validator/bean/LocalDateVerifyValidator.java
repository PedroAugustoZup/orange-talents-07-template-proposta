package br.com.zupacademy.proposta.config.validator.bean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class LocalDateVerifyValidator implements ConstraintValidator<LocalDateVerify, LocalDate> {
    @Override
    public void initialize(LocalDateVerify constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return !localDate.isBefore(LocalDate.now());
    }
}
