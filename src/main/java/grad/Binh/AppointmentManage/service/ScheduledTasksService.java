package grad.Binh.AppointmentManage.service;

public interface ScheduledTasksService {
    void updateAllAppointmentsStatuses();

    void issueInvoicesForCurrentMonth();
}
