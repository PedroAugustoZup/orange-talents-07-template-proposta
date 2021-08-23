package br.com.zupacademy.proposta.config.validator.bean;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ReportAsSingleViolation
@Constraint(validatedBy = LocalDateVerifyValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateVerify {
    String message() default "A data não pode ser antiga";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
