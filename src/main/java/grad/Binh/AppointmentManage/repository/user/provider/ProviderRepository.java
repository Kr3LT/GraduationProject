package grad.Binh.AppointmentManage.repository.user.provider;

import grad.Binh.AppointmentManage.entity.Work;
import grad.Binh.AppointmentManage.entity.user.provider.Provider;
import grad.Binh.AppointmentManage.repository.user.BaseUserRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProviderRepository extends BaseUserRepository<Provider> {
    List<Provider> findByWorks(Work work);

    @Query("select distinct p from Provider p inner join p.works w where w.targetCustomer = 'retail'")
    List<Provider> findAllWithRetailWorks();

    @Query("select distinct p from Provider p inner join p.works w where w.targetCustomer = 'corporate'")
    List<Provider> findAllWithCorporateWorks();
}
