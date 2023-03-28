package grad.Binh.AppointmentManage.entity;

import grad.Binh.AppointmentManage.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage extends BaseEntity implements Comparable<ChatMessage>{
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "id_appointment")
    private Appointment appointment;

    @Override
    public int compareTo(ChatMessage o) {
        return this.createdAt.compareTo(o.getCreatedAt());
    }
}
