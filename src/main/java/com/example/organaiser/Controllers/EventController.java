package com.example.organaiser.Controllers;

import com.example.organaiser.Entities.Attendance;
import com.example.organaiser.Entities.Event;
import com.example.organaiser.Repositories.AttendanceRepository;
import com.example.organaiser.Repositories.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final EventRepository events;
    private final AttendanceRepository attendances;

    public EventController(EventRepository clubs, AttendanceRepository attendances){
        this.events = clubs;
        this.attendances = attendances;
    }

    @GetMapping("/{eventID}/attendance")
    public ResponseEntity<List<Attendance>> getEventAttendance(@PathVariable int eventID){
        List <Attendance> eventAttendance = attendances.getEventAttendance(eventID);
        return ResponseEntity.ok(eventAttendance);
    }


    @PostMapping
    public ResponseEntity <Void> createEvent(@RequestBody Event event){
        events.save(event);
        return ResponseEntity.accepted().build();
    }


    @PostMapping("{eventID}/invite")
    public ResponseEntity<Void> inviteOnEvent(@PathVariable int eventID,
                                              @RequestParam int memberID){
        Optional<Event> IdEvent = events.findById(eventID);
        if(IdEvent.isPresent()){
            Attendance attendance = new Attendance(memberID, eventID, "приглашен");
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("{eventID}/attendance")
    public ResponseEntity<Void> setAttendance(@PathVariable int eventID,
                                              @RequestParam int memberID,
                                              @RequestParam String status){
        Optional<Event> IdEvent = events.findById(eventID);
        if(IdEvent.isPresent()){
            Attendance attendance = attendances.getAttendanceEventOfMember(eventID, memberID);
            if(attendance!=null){
                attendance.setStatus(status);
                attendances.save(attendance);
            }
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
