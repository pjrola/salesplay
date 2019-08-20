package com.auth.service.util.validate;

import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueEmail {
    public String message() default "There is already user with this username!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}