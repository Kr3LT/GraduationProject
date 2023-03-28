package grad.Binh.AppointmentManage.model;

import grad.Binh.AppointmentManage.validation.CurrentPasswordMatches;
import grad.Binh.AppointmentManage.validation.FieldsMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldsMatches(field = "password", matchingField = "matchingPassword")
@CurrentPasswordMatches()
public class ChangePasswordForm {
    @NotNull
    private int id;

    @Size(min = 5, max = 10, message = "Password should have 5-15 letters")
    @NotBlank()
    private String password;

    @Size(min = 5, max = 10, message = "Password should have 5-15 letters")
    @NotBlank()
    private String matchingPassword;

    private String currentPassword;
}
