package grad.Binh.AppointmentManage.repository.user;

import grad.Binh.AppointmentManage.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String roleName);
}
