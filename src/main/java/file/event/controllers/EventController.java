package file.event.controllers;

import file.event.entyties.Event;
import file.event.models.EventBindingModel;
import file.event.models.EventEditBindingModel;
import file.event.services.interfaces.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class EventController {


    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/create-event")
    public ModelAndView createEvent(Model model) {
        eventService.getEventCreate(model);
        return new ModelAndView("create-event");
    }

    @GetMapping("/list-all-events")
    public ModelAndView listEvent() {
        ModelAndView modelAndView = new ModelAndView("list-all-events");
        List<Event> eventsList = this.eventService.getListWithAllEvents();
        modelAndView.addObject("allEvents", eventsList);
        return modelAndView;
    }


    @PostMapping("/create-event")
    public ModelAndView doCreateEvent(@Valid EventBindingModel bindingModel,
                                      BindingResult bindingResult,
                                      ModelAndView modelAndView,
                                      RedirectAttributes redirectAttributes) throws ParseException {
        eventService.createEvent(bindingModel, bindingResult, modelAndView, redirectAttributes);
        return modelAndView;
    }

    @GetMapping("/edit-event")
    public ModelAndView editEvent(@RequestParam("eventId") String eventId, ModelAndView modelAndView) {
        modelAndView.setViewName("edit-event");
        EventEditBindingModel bindingModel= eventService.editEvent(eventId);
        modelAndView.addObject("currentEvent", bindingModel);
        return modelAndView;
    }

    @PostMapping("/edit-event")
    public ModelAndView doEditProject(@RequestParam("eventId") String eventId,ModelAndView modelAndView, EventEditBindingModel bindingModel) throws ParseException {
        modelAndView.setViewName("redirect:/list-all-events");
        eventService.doEditEvent(eventId, bindingModel);
        return modelAndView;
    }


    @GetMapping("/delete-event")
    public ModelAndView deleteEvent(@RequestParam("eventId") String eventId, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/list-all-events");
        this.eventService.deleteEvent(eventId);
        return modelAndView;
    }
}
