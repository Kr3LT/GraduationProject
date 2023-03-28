package grad.Binh.AppointmentManage.repository;

import grad.Binh.AppointmentManage.entity.WorkingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingPlanRepository extends JpaRepository<WorkingPlan, Integer> {
}
