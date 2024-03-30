package dev.vokarub.vacation.vacation;

import java.time.LocalDate;
import java.util.Objects;

public class Vacation {
    private Double averageSalary;
    private Integer vacationDaysCount;
    private LocalDate vacationStartDate;


    public Vacation(Double averageSalary, Integer vacationDaysCount, LocalDate vacationStartDate) {
        this.averageSalary = averageSalary;
        this.vacationDaysCount = vacationDaysCount;
        this.vacationStartDate = vacationStartDate; //опционально?
    }

//    public class VacationCalculatorResponse {
//
//        private Double vacationAmount;
//
//        public void setVacationAmount(Double vacationAmount) {
//            this.vacationAmount = vacationAmount;
//        }
//        public Double getVacationAmount() {
//            return vacationAmount;
//    }

    public Double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Double averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Integer getVacationDaysCount() {
        return vacationDaysCount;
    }

    public void setVacationDaysCount(Integer vacationDaysCount) {
        this.vacationDaysCount = vacationDaysCount;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacation that = (Vacation) o;
        return Objects.equals(averageSalary, that.averageSalary) && Objects.equals(vacationDaysCount, that.vacationDaysCount) && Objects.equals(vacationStartDate, that.vacationStartDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageSalary, vacationDaysCount, vacationStartDate);
    }

    @Override
    public String toString() {
        return "VacationCalculator{" +
                "averageSalary=" + averageSalary +
                ", vacationDaysCount=" + vacationDaysCount +
                ", vacationStartDate=" + vacationStartDate +
                '}';
    }
}
