package cy.ac.nup.lms.persistence;

import cy.ac.nup.lms.domain.Announcement;
import cy.ac.nup.lms.usecase.access.AnnouncementExtractor;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryAnnouncementRepository implements AnnouncementExtractor {

    private final List<Announcement> storage;

    @Override
    public List<Announcement> findAll() {
        return storage;
    }
}
