package com.nugroho.spring.api.applications.validations.rules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.stereotype.Component;

@Documented
@Constraint(validatedBy = { CheckInValueString.class, CheckInValueInt.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InValues {

    public enum ForType {
        STRING, LONG
    }

    String[] values() default {};

    ForType forType() default ForType.STRING;

    String message() default "Not valid input";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

@Component
class CheckInValueString implements ConstraintValidator<InValues, String> {

    private InValues checkValues;

    @Override
    public void initialize(InValues checkInValueString) {
        checkValues = checkInValueString;
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext ctx) {
        if (checkValues.forType() != InValues.ForType.STRING) {
            return true;
        } else if (code == null) {
            return false;
        }

        return List.of(checkValues.values()).contains(code);
    }

}

@Component
class CheckInValueInt implements ConstraintValidator<InValues, Long> {

    private InValues checkValues;

    @Override
    public void initialize(InValues checkInValueInt) {
        checkValues = checkInValueInt;
    }

    @Override
    public boolean isValid(Long code, ConstraintValidatorContext ctx) {
        if (checkValues.forType() != InValues.ForType.LONG) {
            return true;
        }

        return List.of(checkValues.values()).stream().filter(data -> {
            return Long.valueOf(data).equals(code);
        }).count() > 0;
    }

}