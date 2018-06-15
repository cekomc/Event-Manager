package file.event.models;

import javax.validation.constraints.NotEmpty;

public class EventEditBindingModel {
    @NotEmpty
    private String id;

    @NotEmpty(message = "The name of the Event cannot be empty!")
    private String name;

    @NotEmpty(message = "The name of the Location cannot be empty!")
    private String location;

    private String startDate;

    private String endDate;

    public EventEditBindingModel() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
