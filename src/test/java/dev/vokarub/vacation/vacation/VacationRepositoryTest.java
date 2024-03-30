package dev.vokarub.vacation.vacation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VacationRepositoryTest {

    VacationRepository vacationRepository;
    @BeforeEach
    void setUp() {
        vacationRepository = new VacationRepository();
    }
    @Test
    void testCalculateVacationPaymentWithoutDate() {
        assertEquals(34120, vacationRepository.calculateVacationPayment(100000, 10), "Результат должен быть 34120");
    }
    @Test
    void testCalculateVacationPaymentWithDate() {
        assertEquals(20472, vacationRepository.calculateVacationPayment(100000,
                14, LocalDate.of(2022, 1, 1)), "Результат должен быть 20472");
    }

    @Test
    public void shouldGetDatesBetween() {
        LocalDate startDate = LocalDate.of(2023, 6, 1);
        LocalDate endDate = LocalDate.of(2023, 6, 10);

        List<LocalDate> dates = vacationRepository.getDatesBetween(startDate, endDate);

        assertEquals(dates.size(), 10);
        assertEquals(dates.get(0), startDate);
        assertEquals(dates.get(9), endDate);
    }
}