package file.event.services.interfaces;

import file.event.entyties.Event;
import file.event.models.EventBindingModel;
import file.event.models.EventEditBindingModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.List;

public interface EventService {
    void create(EventBindingModel bindingModel) throws ParseException;

    void getEventCreate(Model model);

    void deleteEvent(String id);

    List<Event> getListWithAllEvents();

    void createEvent(EventBindingModel bindingModel, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes) throws ParseException;

    EventEditBindingModel editEvent(String eventId);

    void doEditEvent(String projectId, EventEditBindingModel bindingModel) throws ParseException;
}
