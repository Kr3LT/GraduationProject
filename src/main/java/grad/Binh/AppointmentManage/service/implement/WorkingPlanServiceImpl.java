package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.WorkingPlan;
import grad.Binh.AppointmentManage.model.TimePeriod;
import grad.Binh.AppointmentManage.service.WorkingPlanService;
import org.springframework.stereotype.Service;

@Service
public class WorkingPlanServiceImpl implements WorkingPlanService {
    @Override
    public void updateWorkingPlan(WorkingPlan workingPlan) {

    }

    @Override
    public void addBreakToWorkingPlan(TimePeriod breakToAdd, int planId, String dayOfWeek) {

    }

    @Override
    public void deleteBreakFromWorkingPlan(TimePeriod breakToDelete, int planId, String dayOfWeek) {

    }

    @Override
    public WorkingPlan getWorkingPlanByProviderId(int providerId) {
        return null;
    }
}
