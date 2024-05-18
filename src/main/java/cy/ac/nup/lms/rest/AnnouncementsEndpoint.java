package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.Announcement;
import cy.ac.nup.lms.usecase.access.AnnouncementExtractor;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnnouncementsEndpoint {

    private final AnnouncementExtractor announcementExtractor;

    @GetMapping("/announcements")
    public List<AnnouncementModel> announcements() {
        List<Announcement> announcements = announcementExtractor.findAll();
        return announcements.stream()
                .map(x -> x.value)
                .map(AnnouncementModel::new)
                .toList();
    }

    record AnnouncementModel(String announcement) {

    }
}
