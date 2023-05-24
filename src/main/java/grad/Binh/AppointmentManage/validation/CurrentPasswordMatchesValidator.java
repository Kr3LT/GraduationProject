package grad.Binh.AppointmentManage.validation;

import grad.Binh.AppointmentManage.entity.user.User;
import grad.Binh.AppointmentManage.model.ChangePasswordForm;
import grad.Binh.AppointmentManage.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CurrentPasswordMatchesValidator implements ConstraintValidator<CurrentPasswordMatches, Object> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public void initialize(CurrentPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        ChangePasswordForm passwordForm = (ChangePasswordForm) o;
        boolean isValid = false;
        User user = userService.getUserById(passwordForm.getId());
        if (passwordEncoder.matches(passwordForm.getCurrentPassword(), user.getPassword())) {
            isValid = true;
        }
        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("currentPassword").addConstraintViolation();
        }
        return isValid;

    }
}
