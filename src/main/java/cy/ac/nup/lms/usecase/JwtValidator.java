package cy.ac.nup.lms.usecase;

public interface JwtValidator {

    boolean execute(String jwtRaw);
}
