package file.event.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class EventBindingModel {
    @NotEmpty(message = "The name of the Event cannot be empty!")
    private String name;

    @NotEmpty(message = "The name of the Location cannot be empty!")
    private String location;

    @NotEmpty(message = "Date cannot be empty!")
    private String startDate;

    @NotEmpty(message = "Date cannot be empty!")
    private String endDate;

    public EventBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
