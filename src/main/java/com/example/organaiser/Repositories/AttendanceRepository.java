package com.example.organaiser.Repositories;

import com.example.organaiser.Entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    Attendance getAttendanceEventOfMember (int eventID, int memberID);

    List<Attendance> getEventAttendance(int eventID);
}
