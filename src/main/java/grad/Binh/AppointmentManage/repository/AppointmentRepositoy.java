package grad.Binh.AppointmentManage.repository;

import grad.Binh.AppointmentManage.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepositoy extends JpaRepository<Appointment, Integer> {
}
