package grad.Binh.AppointmentManage.repository;

import grad.Binh.AppointmentManage.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {
}
