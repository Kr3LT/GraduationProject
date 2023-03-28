package grad.Binh.AppointmentManage.service.implement;

import grad.Binh.AppointmentManage.entity.Appointment;
import grad.Binh.AppointmentManage.entity.ChatMessage;
import grad.Binh.AppointmentManage.entity.ExchangeRequest;
import grad.Binh.AppointmentManage.entity.Invoice;
import grad.Binh.AppointmentManage.service.EmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String templateName, Context templateContext, File attachment) {

    }

    @Override
    public void sendAppointmentFinishedNotification(Appointment appointment) {

    }

    @Override
    public void sendAppointmentRejectionRequestedNotification(Appointment appointment) {

    }

    @Override
    public void sendNewAppointmentScheduledNotification(Appointment appointment) {

    }

    @Override
    public void sendAppointmentCanceledByCustomerNotification(Appointment appointment) {

    }

    @Override
    public void sendAppointmentCanceledByProviderNotification(Appointment appointment) {

    }

    @Override
    public void sendInvoice(Invoice invoice) {

    }

    @Override
    public void sendAppointmentRejectionAcceptedNotification(Appointment appointment) {

    }

    @Override
    public void sendNewChatMessageNotification(ChatMessage appointment) {

    }

    @Override
    public void sendNewExchangeRequestedNotification(Appointment oldAppointment, Appointment newAppointment) {

    }

    @Override
    public void sendExchangeRequestAcceptedNotification(ExchangeRequest exchangeRequest) {

    }

    @Override
    public void sendExchangeRequestRejectedNotification(ExchangeRequest exchangeRequest) {

    }
}
