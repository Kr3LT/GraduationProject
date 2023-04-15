package grad.Binh.AppointmentManage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayPlan {
    private TimePeriod workingHours;
    private List<TimePeriod> breaks;

    public DayPlan(TimePeriod workingHours){
        this.workingHours = workingHours;
    }

    public List<TimePeriod> getTimePeriodsWithBreaksExcluded() {
        ArrayList<TimePeriod> timePeriodsWithBreaksExcluded = new ArrayList<>();
        timePeriodsWithBreaksExcluded.add(getWorkingHours());
        List<TimePeriod> breaks = getBreaks();

        if (!breaks.isEmpty()) {
            ArrayList<TimePeriod> toAdd = new ArrayList();
            for (TimePeriod break1 : breaks) {
                if (break1.getStart().isBefore(workingHours.getStart())) {
                    break1.setStart(workingHours.getStart());
                }
                if (break1.getEnd().isAfter(workingHours.getEnd())) {
                    break1.setEnd(workingHours.getEnd());
                }
                for (TimePeriod period : timePeriodsWithBreaksExcluded) {
                    if (break1.getStart().equals(period.getStart()) && break1.getEnd().isAfter(period.getStart()) && break1.getEnd().isBefore(period.getEnd())) {
                        period.setStart(break1.getEnd());
                    }
                    if (break1.getStart().isAfter(period.getStart()) && break1.getStart().isBefore(period.getEnd()) && break1.getEnd().equals(period.getEnd())) {
                        period.setEnd(break1.getStart());
                    }
                    if (break1.getStart().isAfter(period.getStart()) && break1.getEnd().isBefore(period.getEnd())) {
                        toAdd.add(new TimePeriod(period.getStart(), break1.getStart()));
                        period.setStart(break1.getEnd());
                    }
                }
            }
            timePeriodsWithBreaksExcluded.addAll(toAdd);
            Collections.sort(timePeriodsWithBreaksExcluded);
        }


        return timePeriodsWithBreaksExcluded;
    }

}
