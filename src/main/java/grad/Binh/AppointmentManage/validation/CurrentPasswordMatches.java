package grad.Binh.AppointmentManage.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CurrentPasswordMatchesValidator.class)
public @interface CurrentPasswordMatches {
    String message() default "Current Password Wrong";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};}
