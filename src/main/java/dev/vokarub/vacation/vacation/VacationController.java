package dev.vokarub.vacation.vacation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VacationController {

    private final VacationRepository vacationRepository;

    public VacationController(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @GetMapping("/calculate")
    public double getVacationPayment(@RequestBody Vacation request) {

        Double averageSalary = request.getAverageSalary();
        Integer vacationDaysCount = request.getVacationDaysCount();
        LocalDate vacationStartDate = request.getVacationStartDate();

        if (vacationStartDate != null) { //если есть дата начала отпуска
            return vacationRepository.calculateVacationPayment(averageSalary, vacationDaysCount, vacationStartDate);
        } else {
            return vacationRepository.calculateVacationPayment(averageSalary, vacationDaysCount);
        }
    }

}

