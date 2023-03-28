package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Work;
import grad.Binh.AppointmentManage.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkServiceImpl implements WorkService {
    @Override
    public void createNewWork(Work work) {

    }

    @Override
    public Work getWorkById(int workId) {
        return null;
    }

    @Override
    public List<Work> getAllWorks() {
        return null;
    }

    @Override
    public List<Work> getWorksByProviderId(int providerId) {
        return null;
    }

    @Override
    public List<Work> getRetailCustomerWorks() {
        return null;
    }

    @Override
    public List<Work> getCorporateCustomerWorks() {
        return null;
    }

    @Override
    public List<Work> getWorksForRetailCustomerByProviderId(int providerId) {
        return null;
    }

    @Override
    public List<Work> getWorksForCorporateCustomerByProviderId(int providerId) {
        return null;
    }

    @Override
    public void updateWork(Work work) {

    }

    @Override
    public void deleteWorkById(int workId) {

    }

    @Override
    public boolean isWorkForCustomer(int workId, int customerId) {
        return false;
    }
}
