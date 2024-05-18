package cy.ac.nup.lms.usecase.access;

import cy.ac.nup.lms.domain.Announcement;
import java.util.List;

public interface AnnouncementExtractor {

    List<Announcement> findAll();
}
