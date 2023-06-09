package grad.Binh.AppointmentManage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimePeriod implements Comparable<TimePeriod>{
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime start;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime end;

    @Override
    public int compareTo(TimePeriod o) {
        return this.getStart().compareTo(o.getStart());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePeriod period = (TimePeriod) o;
        return this.start.equals(period.getStart()) &&

                this.end.equals(period.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
