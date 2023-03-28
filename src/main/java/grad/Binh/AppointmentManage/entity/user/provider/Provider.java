package grad.Binh.AppointmentManage.entity.user.provider;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.entity.Work;
import grad.Binh.AppointmentManage.entity.WorkingPlan;
import grad.Binh.AppointmentManage.entity.user.Role;
import grad.Binh.AppointmentManage.entity.user.User;
import grad.Binh.AppointmentManage.model.UserForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_provider")
public class Provider extends User {
    @OneToMany(mappedBy = "provider")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(name = "works_providers", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_work"))
    private List<Work> works;

    @OneToOne(mappedBy = "provider", cascade = {CascadeType.ALL})
    private WorkingPlan workingPlan;

    public Provider(UserForm userFormDTO, String encryptedPassword, Collection<Role> roles, WorkingPlan workingPlan) {
        super(userFormDTO, encryptedPassword, roles);
        this.workingPlan = workingPlan;
        workingPlan.setProvider(this);
        this.works = userFormDTO.getWorks();
    }
    @Override
    public void update(UserForm updateData) {
        super.update(updateData);
        this.works = updateData.getWorks();
    }
}
