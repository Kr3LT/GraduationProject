package grad.Binh.AppointmentManage.repository.user.customer;

import grad.Binh.AppointmentManage.entity.user.customer.Customer;
import grad.Binh.AppointmentManage.repository.user.BaseUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseUserRepository<Customer> {
}
