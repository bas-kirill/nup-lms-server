package cy.ac.nup.lms.domain;

import java.time.LocalDate;

// Course active on interval: (start date; end date)
public class Course {

    public final CourseCode code;
    public final String name;
    public final LocalDate startDate;
    public final LocalDate endDate;

    public Course(CourseCode code, String name, LocalDate startDate, LocalDate endDate) {
        this.code = code;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
