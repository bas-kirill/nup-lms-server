package cy.ac.nup.lms.domain;

import cy.ac.nup.lms.common.AggregateRoot;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("java:S2055")
public class User extends AggregateRoot<Username> implements UserDetails {

    public final FacultyCode facultyCode;
    public final Username username;
    public final String password;
    public final String authority;
    public final String fullName;
    private final Map<CourseCode, Course> courses;

    private User(FacultyCode facultyCode, Username username, String password, String authority, String fullName) {
        super(username);
        this.facultyCode = facultyCode;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.fullName = fullName;
        this.courses = new HashMap<>();
    }

    public static User create(FacultyCode facultyCode, Username username, String password, String authority,
            String fullName) {
        return new User(facultyCode, username, password, authority, fullName);
    }

    public Map<CourseCode, Course> courses() {
        return new HashMap<>(courses);
    }

    public void addCourse(Course course) {
        courses.put(course.code, course);
    }

    public void addCourses(Collection<Course> courses) {
        courses.forEach(this::addCourse);
    }

    public void removeCourse(Course course) {
        courses.remove(course.code);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username.value;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
