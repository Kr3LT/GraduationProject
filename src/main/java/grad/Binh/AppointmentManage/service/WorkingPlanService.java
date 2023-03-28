package grad.Binh.AppointmentManage.service;

import grad.Binh.AppointmentManage.entity.WorkingPlan;
import grad.Binh.AppointmentManage.model.TimePeriod;

public interface WorkingPlanService {
    void updateWorkingPlan(WorkingPlan workingPlan);

    void addBreakToWorkingPlan(TimePeriod breakToAdd, int planId, String dayOfWeek);

    void deleteBreakFromWorkingPlan(TimePeriod breakToDelete, int planId, String dayOfWeek);

    WorkingPlan getWorkingPlanByProviderId(int providerId);
}
