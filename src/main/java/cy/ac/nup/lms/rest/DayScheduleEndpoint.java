package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.GetDaySchedule;
import java.security.Principal;
import java.time.LocalDate;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DayScheduleEndpoint {

    private final GetDaySchedule getDaySchedule;

    @GetMapping("/schedule/{year}/{month}/{day}")
    public Object getSchedule(
            Principal principal,
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day) {
        Username username = Username.from(principal.getName());
        return getDaySchedule.execute(username, LocalDate.of(year, month, day))
                .fold(error -> error.message, Function.identity());
    }
}
