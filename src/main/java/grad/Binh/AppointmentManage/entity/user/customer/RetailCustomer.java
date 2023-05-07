package grad.Binh.AppointmentManage.entity.user.customer;

import grad.Binh.AppointmentManage.entity.user.Role;
import grad.Binh.AppointmentManage.model.UserForm;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "retail_customers")
@PrimaryKeyJoinColumn(name = "id_customer")
@NoArgsConstructor
public class RetailCustomer extends Customer {
    public RetailCustomer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
    }
}
