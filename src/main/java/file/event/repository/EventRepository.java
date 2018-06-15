package file.event.repository;

import file.event.entyties.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
    Event findFirstById(String id);
}
