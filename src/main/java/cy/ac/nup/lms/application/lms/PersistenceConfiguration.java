package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.domain.Announcement;
import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.persistence.InMemoryAnnouncementRepository;
import cy.ac.nup.lms.persistence.InMemoryCourseRepository;
import cy.ac.nup.lms.persistence.InMemoryUserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public Set<Course> courses() { // @formatter:off
        return Set.of(
                new Course(CourseCode.from("CS100"), "Programming Principles II", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 6, 1)),
                new Course(CourseCode.from("CS200"), "Functional Programming", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 11, 1))
        );
    } // @formatter:on

    @Bean
    public Set<User> users(Set<Course> courses) { // @formatter:off
        // https://stackoverflow.com/questions/56762121/configure-nooppasswordencoder-in-spring
        return Set.of(
                new User(Username.from("admin"), "{noop}123", "ROLE_ADMIN", "Admin Admin", Set.of()),
                new User(Username.from("kiryuxa"), "{noop}321", "ROLE_STUDENT", "Kiryuxa Bas", courses),
                new User(Username.from("compsci"), "{noop}123", "ROLE_FACULTY", "Computer Science Department", courses)
        );
    } // @formatter:on

    @Bean
    public InMemoryUserRepository userRepository(Set<User> users) {
        Map<Username, User> storage = users.stream().collect(Collectors.toMap(User::username, Function.identity()));
        return new InMemoryUserRepository(storage);
    }

    @Bean
    public InMemoryAnnouncementRepository announcementRepository() {
        List<Announcement> announcements = List.of(
                Announcement.from("Congratulations for ACM ICPC semi-finalists!")
        );
        return new InMemoryAnnouncementRepository(announcements);
    }

    @Bean
    public InMemoryCourseRepository courseRepository(Set<Course> courses) {
        Map<CourseCode, Course> storage = courses.stream().collect(Collectors.toMap(Course::code, Function.identity()));
        return new InMemoryCourseRepository(storage);
    }
}
