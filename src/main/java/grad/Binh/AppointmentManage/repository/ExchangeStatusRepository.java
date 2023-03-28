package grad.Binh.AppointmentManage.repository;

import grad.Binh.AppointmentManage.entity.ExchangeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeStatusRepository extends JpaRepository<ExchangeStatus, Integer> {
}
