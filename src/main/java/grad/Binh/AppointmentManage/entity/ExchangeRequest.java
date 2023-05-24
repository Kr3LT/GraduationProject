package grad.Binh.AppointmentManage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exchanges")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRequest extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(name = "exchange_status")
    private ExchangeStatus status;

    @ManyToOne
    @JoinColumn(name = "id_appointment_requestor")
    private Appointment requestor;

    @OneToOne
    @JoinColumn(name = "id_appointment_requested")
    private Appointment requested;
}
