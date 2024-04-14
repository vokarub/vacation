package dev.vokarub.vacation.vacation;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VacationDAO {
    private final double COEF = 29.3; //(365 дней − 14 праздничных дней в году) / 12 месяцев

    public Integer calculateVacationPayment (Integer averageSalary, Integer vacationDaysCount) {
        isValidInput(averageSalary, vacationDaysCount);
        return (int) (averageSalary / COEF) * vacationDaysCount;
    }


    public Integer calculateVacationPayment (Integer averageSalary, Integer vacationDaysCount, LocalDate vacationStartDate) {
        isValidInput(averageSalary, vacationDaysCount);
        if (vacationStartDate == null){
            return (int) (averageSalary / COEF) * vacationDaysCount;
        }

        List<LocalDate> vacationDates = getDatesBetween(vacationStartDate, vacationStartDate.plusDays(vacationDaysCount));
        int nonWorkingDays = 0;

        for (LocalDate date : vacationDates) {
            if (isHoliday(date)) {
                nonWorkingDays++;
            }
        }

        int workingDays = vacationDates.size() - nonWorkingDays;

        return (int) (averageSalary / COEF) * workingDays;
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

    void isValidInput(Integer averageSalary, Integer vacationDaysCount) {
        if (averageSalary == null || vacationDaysCount == null) {
            throw new NullPointerException();
        }
        if (averageSalary < 0 || vacationDaysCount < 0) {
            throw new IllegalArgumentException();
        }
    }

}
