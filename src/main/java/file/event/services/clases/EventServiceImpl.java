package file.event.services.clases;

import file.event.entyties.Event;
import file.event.models.EventBindingModel;
import file.event.models.EventEditBindingModel;
import file.event.repository.EventRepository;
import file.event.services.interfaces.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper mapper;
    private String message;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(EventBindingModel bindingModel) throws ParseException {
        Event event = new Event();
        event.setName(bindingModel.getName());
        event.setLocation(bindingModel.getLocation());
        getDate(bindingModel, event);
        this.eventRepository.save(event);
    }

    private void getDate(EventBindingModel bindingModel, Event event) throws ParseException {
        String startDate = bindingModel.getStartDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date result = formatter.parse(startDate);
        event.setStartDate(result);
        String endDate = bindingModel.getEndDate();
        Date endResult = formatter.parse(endDate);

        event.setEndDate(endResult);
    }

    private void getEditedDate(EventEditBindingModel bindingModel, Event event) throws ParseException {
        String startDate = bindingModel.getStartDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date result = formatter.parse(startDate);
        event.setStartDate(result);
        String endDate = bindingModel.getEndDate();
        Date endResult = formatter.parse(endDate);

        event.setEndDate(endResult);
    }

    public void getEventCreate(Model model) {
        if (!model.containsAttribute("eventInput")) {
            model.addAttribute("eventInput",
                    new EventBindingModel());
        }
    }

    @Override
    public List<Event> getListWithAllEvents() {
        return this.eventRepository.findAll();
    }

    @Override
    public void createEvent(EventBindingModel bindingModel, BindingResult bindingResult, ModelAndView modelAndView, RedirectAttributes redirectAttributes) throws ParseException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventInput", bindingResult);
            redirectAttributes.addFlashAttribute("eventInput", bindingModel);

            modelAndView.setViewName("redirect:/create-event");
        } else {
            modelAndView.setViewName("redirect:/list-all-events");
            this.create(bindingModel);
            message = "Worker successful registered";
        }

    }

    @Override
    public EventEditBindingModel editEvent(@RequestParam("eventId") String eventId) {
        Event event = this.eventRepository.findFirstById(eventId);
        EventEditBindingModel bindingModel = new EventEditBindingModel();
        bindingModel.setId(event.getId());
        bindingModel.setName(event.getName());
        bindingModel.setLocation(event.getLocation());
        bindingModel.setStartDate(event.getStartDate().toString());
        bindingModel.setEndDate(event.getEndDate().toString());
        return bindingModel;
    }

    @Override
    public void doEditEvent(@RequestParam("eventId") String eventId, EventEditBindingModel bindingModel) throws ParseException {
        Event event = this.eventRepository.findFirstById(eventId);
        event.setName(bindingModel.getName());
        event.setLocation(bindingModel.getLocation());
        getEditedDate(bindingModel, event);
        this.eventRepository.save(event);
    }

    @Override
    public void deleteEvent(String id) {
        Event event = this.eventRepository.findFirstById(id);
        this.eventRepository.delete(event);
    }
}
