package grad.Binh.AppointmentManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentStatus extends JpaRepository<AppointmentStatus, Integer> {
}
