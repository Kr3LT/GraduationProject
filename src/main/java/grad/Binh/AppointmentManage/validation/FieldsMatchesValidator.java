package grad.Binh.AppointmentManage.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchesValidator implements ConstraintValidator<FieldsMatches, Object> {
    private String field;
    private String matchingField;
    @Override
    public void initialize(FieldsMatches constraintAnnotation) {
        field = constraintAnnotation.field();
        matchingField = constraintAnnotation.matchingField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object field1Value = new BeanWrapperImpl(o)
                .getPropertyValue(field);
        Object field2Value = new BeanWrapperImpl(o)
                .getPropertyValue(matchingField);

        if (field1Value != null) {
            return field1Value.equals(field2Value);
        } else {
            return field2Value == null;
        }
    }
}
