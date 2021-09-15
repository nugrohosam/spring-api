package com.nugroho.spring.api.applications.validations.rules;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

@Documented
@Constraint(validatedBy = CheckAllValue.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllNotBlankWhen {
    String field() default "";

    String expectation() default "";

    String[] forFields() default {};

    String message() default "Not registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        AllNotBlankWhen[] value();
    }
}

@Component
class CheckAllValue implements ConstraintValidator<AllNotBlankWhen, Object> {

    private AllNotBlankWhen notBlankWhenTrue;

    @Override
    public void initialize(AllNotBlankWhen notBlank) {
        notBlankWhenTrue = notBlank;
    }

    @Override
    public boolean isValid(Object data, ConstraintValidatorContext ctx) {

        try {
            String fieldValue = new BeanWrapperImpl(data).getPropertyValue(notBlankWhenTrue.field()).toString();
            if (fieldValue == notBlankWhenTrue.expectation()) {
                for (String field : notBlankWhenTrue.forFields()) {
                    String value = new BeanWrapperImpl(data).getPropertyValue(field).toString();
                    if (value.isBlank()) {
                        return false;
                    }
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}