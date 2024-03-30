package dev.vokarub.vacation.vacation;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VacationRepository {
    private List<Vacation> vacations = new ArrayList<>();
    private final double COEF = 29.3; //(365 дней − 14 праздничных дней в году) / 12 месяцев

    public Double calculateVacationPayment (Double averageSalary, Integer vacationDaysCount){
        return (averageSalary / COEF) * vacationDaysCount;
    }
    public Double calculateVacationPayment (Double averageSalary, Integer vacationDaysCount, LocalDate vacationStartDate) {
        List<LocalDate> vacationDates = getDatesBetween(vacationStartDate, vacationStartDate.plusDays(vacationDaysCount));
        int nonWorkingDays = 0;

        for (LocalDate date : vacationDates) {
            if (isHoliday(date)) {
                nonWorkingDays++;
            }
        }

        int workingDays = vacationDates.size() - nonWorkingDays;

        return (averageSalary / COEF) * workingDays;
    }

    public List<LocalDate> getDatesBetween (LocalDate startDate, LocalDate endDate){
        return startDate.datesUntil(endDate)
                .collect(Collectors.toList());
    }

    private boolean isHoliday (LocalDate date){
        String[] holidays = { //Массив официальных праздников и выходных дней
                "2024-01-01",
                "2024-01-02",
                "2024-01-03",
                "2024-01-04",
                "2024-01-05",
                "2024-01-06",
                "2024-01-07",
                "2024-01-08",
                "2024-02-23",
                "2024-03-08",
                "2024-05-01",
                "2024-05-09",
                "2024-06-12",
                "2024-11-04"};
        for (String x : holidays) {
            if (date.toString().equals(x)) {
                return true;
            }
        }
        return false;
    }


    //TESTING

    public List<Vacation> getVacations() {
        return vacations;
    }

    @PostConstruct
    private void init() {
        vacations.add(new Vacation(10000.0, 14, LocalDate.of(2024, 1, 1)));
        vacations.add(new Vacation(20000.0, 14, LocalDate.of(2024, 2, 2)));
        vacations.add(new Vacation(90000.0, 14, LocalDate.of(2024, 3, 3)));
        vacations.add(new Vacation(80000.0, 14, LocalDate.of(2024, 4, 4)));
        vacations.add(new Vacation(70000.0, 14, LocalDate.of(2024, 5, 5)));
        vacations.add(new Vacation(60000.0, 14, LocalDate.of(2024, 6, 6)));
        vacations.add(new Vacation(40000.0, 14, LocalDate.of(2024, 7, 7)));

    }
}
