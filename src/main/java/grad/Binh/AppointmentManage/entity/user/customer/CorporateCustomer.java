package grad.Binh.AppointmentManage.entity.user.customer;

import grad.Binh.AppointmentManage.entity.user.Role;
import grad.Binh.AppointmentManage.model.UserForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "corporate_customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorporateCustomer extends Customer {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;

    public CorporateCustomer(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles) {
        super(userFormDTO, encryptedPassword, roles);
        this.companyName = userFormDTO.getCompanyName();
        this.vatNumber = userFormDTO.getVatNumber();
    }

    @Override
    public void update(UserForm updateData) {
        super.update(updateData);
        this.companyName = updateData.getCompanyName();
        this.vatNumber = updateData.getVatNumber();
    }
}
