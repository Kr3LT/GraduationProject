package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.*;
import grad.Binh.AppointmentManage.entity.user.User;
import grad.Binh.AppointmentManage.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void newNotification(String title, String message, String url, User user) {

    }

    @Override
    public void markAsRead(int notificationId, int userId) {

    }

    @Override
    public void markAllAsRead(int userId) {

    }

    @Override
    public Notification getNotificationById(int notificationId) {
        return null;
    }

    @Override
    public List<Notification> getAll(int userId) {
        return null;
    }

    @Override
    public List<Notification> getUnreadNotifications(int userId) {
        return null;
    }

    @Override
    public void newAppointmentFinishedNotification(Appointment appointment, boolean sendEmail) {

    }

    @Override
    public void newAppointmentRejectionRequestedNotification(Appointment appointment, boolean sendEmail) {

    }

    @Override
    public void newNewAppointmentScheduledNotification(Appointment appointment, boolean sendEmail) {

    }

    @Override
    public void newAppointmentCanceledByCustomerNotification(Appointment appointment, boolean sendEmail) {

    }

    @Override
    public void newAppointmentCanceledByProviderNotification(Appointment appointment, boolean sendEmail) {

    }

    @Override
    public void newAppointmentRejectionAcceptedNotification(Appointment appointment, boolean sendEmail) {

    }

    @Override
    public void newChatMessageNotification(ChatMessage chatMessage, boolean sendEmail) {

    }

    @Override
    public void newInvoice(Invoice invoice, boolean sendEmail) {

    }

    @Override
    public void newExchangeRequestedNotification(Appointment oldAppointment, Appointment newAppointment, boolean sendEmail) {

    }

    @Override
    public void newExchangeAcceptedNotification(ExchangeRequest exchangeRequest, boolean sendEmail) {

    }

    @Override
    public void newExchangeRejectedNotification(ExchangeRequest exchangeRequest, boolean sendEmail) {

    }
}
