package dev.vokarub.vacation.vacation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequestMapping("/calculate")
@RestController
public class VacationController {

    private final VacationRepository vacationRepository;

    public VacationController(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    // localhost:8080/calculate?salary=45000&days=7
    @GetMapping("")
    public Integer getVacationPaymentWithoutDate(@RequestParam("salary") Integer averageSalary,
                                                 @RequestParam("days") Integer vacationDaysCount) {
        return vacationRepository.calculateVacationPayment(averageSalary, vacationDaysCount);
    }

    // localhost:8080/calculate/withdate?salary=45000&days=7&date=2024-06-06
    @GetMapping("/withdate") // не получается ловить Null исключение через required=false, декомпозиция
    public Integer getVacationPaymentWithDate(@RequestParam("salary") Integer averageSalary,
                                                 @RequestParam("days") Integer vacationDaysCount,
                                                 @RequestParam("date") String vacationStartDate){
        return vacationRepository.calculateVacationPayment(averageSalary, vacationDaysCount, LocalDate.parse(vacationStartDate));
    }

}

