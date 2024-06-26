package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.rest.ActiveUsersEndpoint;
import cy.ac.nup.lms.rest.AdminEndpoint;
import cy.ac.nup.lms.rest.AnnouncementsEndpoint;
import cy.ac.nup.lms.rest.CourseDetailsEndpoint;
import cy.ac.nup.lms.rest.CourseStudentsEndpoint;
import cy.ac.nup.lms.rest.CoursesEndpoint;
import cy.ac.nup.lms.rest.DayScheduleEndpoint;
import cy.ac.nup.lms.rest.LoginEndpoint;
import cy.ac.nup.lms.rest.MeEndpoint;
import cy.ac.nup.lms.rest.ProfileEndpoint;
import cy.ac.nup.lms.rest.StudentEndpoint;
import cy.ac.nup.lms.rest.UserInfoEndpoint;
import cy.ac.nup.lms.rest.UsersEndpoint;
import cy.ac.nup.lms.usecase.AddStudentToCourse;
import cy.ac.nup.lms.usecase.GetCourse;
import cy.ac.nup.lms.usecase.GetDaySchedule;
import cy.ac.nup.lms.usecase.GetUserInfo;
import cy.ac.nup.lms.usecase.Login;
import cy.ac.nup.lms.usecase.MeExtractor;
import cy.ac.nup.lms.usecase.RemoveStudentFromCourse;
import cy.ac.nup.lms.usecase.ShowProfile;
import cy.ac.nup.lms.usecase.access.AnnouncementExtractor;
import cy.ac.nup.lms.usecase.access.CourseExtractor;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;

@Configuration
public class RestConfiguration {

    @Bean
    public LoginEndpoint loginEndpoint(Login login) {
        return new LoginEndpoint(login);
    }

    @Bean
    public AdminEndpoint adminEndpoint() {
        return new AdminEndpoint();
    }

    @Bean
    public StudentEndpoint userEndpoint() {
        return new StudentEndpoint();
    }

    @Bean
    public MeEndpoint meEndpoint(MeExtractor meExtractor) {
        return new MeEndpoint(meExtractor);
    }

    @Bean
    public AnnouncementsEndpoint announcementsEndpoint(AnnouncementExtractor announcementExtractor) {
        return new AnnouncementsEndpoint(announcementExtractor);
    }

    @Bean
    public DayScheduleEndpoint dayScheduleEndpoint(GetDaySchedule getDaySchedule) {
        return new DayScheduleEndpoint(getDaySchedule);
    }

    @Bean
    public ProfileEndpoint profileEndpoint(ShowProfile showProfile) {
        return new ProfileEndpoint(showProfile);
    }

    @Bean
    public ActiveUsersEndpoint activeUsersEndpoint(SessionRegistry sessionRegistry) {
        return new ActiveUsersEndpoint(sessionRegistry);
    }

    @Bean
    public CoursesEndpoint coursesEndpoint(CourseExtractor courseExtractor) {
        return new CoursesEndpoint(courseExtractor);
    }

    @Bean
    public CourseStudentsEndpoint courseStudentsEndpoint(
            AddStudentToCourse addStudentToCourse,
            UserExtractor userExtractor,
            RemoveStudentFromCourse removeStudentFromCourse) {
        return new CourseStudentsEndpoint(addStudentToCourse, userExtractor, removeStudentFromCourse);
    }

    @Bean
    public UserInfoEndpoint userInfoEndpoint(GetUserInfo getUserInfo) {
        return new UserInfoEndpoint(getUserInfo);
    }

    @Bean
    public CourseDetailsEndpoint courseDetailsEndpoint(GetCourse getCourse) {
        return new CourseDetailsEndpoint(getCourse);
    }

    @Bean
    public UsersEndpoint usersEndpoint(UserExtractor userExtractor) {
        return new UsersEndpoint(userExtractor);
    }
}
