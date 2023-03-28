package grad.Binh.AppointmentManage.repository;

import grad.Binh.AppointmentManage.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Integer> {
}
