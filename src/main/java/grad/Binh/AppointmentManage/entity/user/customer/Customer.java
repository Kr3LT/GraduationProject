package grad.Binh.AppointmentManage.entity.user.customer;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.entity.user.Role;
import grad.Binh.AppointmentManage.entity.user.User;
import grad.Binh.AppointmentManage.model.UserForm;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id_customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;

    public Customer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
    }

    public String getType() {
        if (super.hasRole("ROLE_CUSTOMER_CORPORATE")) {
            return "corporate";
        } else if (super.hasRole("ROLE_CUSTOMER_RETAIL")) {
            return "retail";
        }
        return "";
    }
}
