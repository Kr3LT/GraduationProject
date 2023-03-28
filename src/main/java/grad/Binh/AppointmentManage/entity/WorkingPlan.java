package grad.Binh.AppointmentManage.entity;

import grad.Binh.AppointmentManage.entity.user.provider.Provider;
import grad.Binh.AppointmentManage.model.DayPlan;
import grad.Binh.AppointmentManage.model.TimePeriod;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalTime;

@Entity
@Table(name = "working_plans")
@Getter
@Setter
public class WorkingPlan {
    @Id
    @Column(name = "id_provider")
    private int id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_provider")
    private Provider provider;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "monday")
    private DayPlan monday;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "tuesday")
    private DayPlan tuesday;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "wednesday")
    private DayPlan wednesday;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "thursday")
    private DayPlan thursday;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "friday")
    private DayPlan friday;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "saturday")
    private DayPlan saturday;

    @Type(value = JsonType.class)
    @Column(columnDefinition = "json", name = "sunday")
    private DayPlan sunday;

    public DayPlan getDay(String day) {
        switch (day) {
            case "monday":
                return monday;

            case "tuesday":
                return tuesday;

            case "wednesday":
                return wednesday;

            case "thursday":
                return thursday;

            case "friday":
                return friday;

            case "saturday":
                return saturday;

            case "sunday":
                return sunday;

            default:
                return null;
        }
    }
    public static WorkingPlan generateDefaultWorkingPlan() {
        WorkingPlan wp = new WorkingPlan();
        LocalTime defaultStartHour = LocalTime.parse("06:00");
        LocalTime defaultEndHour = LocalTime.parse("18:00");
        TimePeriod defaultWorkingPeroid = new TimePeriod(defaultStartHour, defaultEndHour);
        DayPlan defaultDayPlan = new DayPlan(defaultWorkingPeroid);
        wp.setMonday(defaultDayPlan);
        wp.setTuesday(defaultDayPlan);
        wp.setWednesday(defaultDayPlan);
        wp.setThursday(defaultDayPlan);
        wp.setFriday(defaultDayPlan);
        wp.setSaturday(defaultDayPlan);
        wp.setSunday(defaultDayPlan);
        return wp;
    }
}
