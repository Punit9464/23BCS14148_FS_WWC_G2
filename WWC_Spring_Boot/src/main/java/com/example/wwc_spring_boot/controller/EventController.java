package com.example.wwc_spring_boot.controller;

import com.example.wwc_spring_boot.model.Event;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/events")

public class EventController {
    private List<Event> events = new ArrayList<>();

    @GetMapping
    public List<Event> getEvents() {
        return events;
    }

    @PostMapping
    public String addEvent(Event event) {
        events.add(event);
        System.out.println(event.getName() + " " + event.getLocation());
        return "Event Added Successfully";
    }

    @GetMapping("/{id}")
    public Event getEvent(@RequestParam String id) {
        return events.get(Integer.parseInt(id));
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@RequestParam String id) {
        events.remove(Integer.parseInt(id));
        return "Event Deleted Successfully";
    }

    @PutMapping("/{id}")
    public String updateEvent(String id, Event event) {
        events.set(Integer.parseInt(id), event);
        return "Event Updated Successfully";
    }
}
