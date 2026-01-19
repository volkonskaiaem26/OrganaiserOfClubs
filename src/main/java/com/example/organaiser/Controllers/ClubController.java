package com.example.organaiser.Controllers;


import com.example.organaiser.Entities.Club;
import com.example.organaiser.Repositories.ClubRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clubs")
public class ClubController {

    private final ClubRepository clubs;

    public ClubController(ClubRepository clubs){
        this.clubs = clubs;
    }

    @GetMapping
    public ResponseEntity<List<Club>> getClubs(){
        return ResponseEntity.ok(clubs.findAll());
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Club> getClubID(@PathVariable("ID") int ID){
        Optional<Club> IdClub = clubs.findById(ID);
        if(IdClub.isPresent()){
            return ResponseEntity.ok(IdClub.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity <Void> createClub(@RequestBody Club club){
        clubs.save(club);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{ID}")
    public ResponseEntity<Void> updateClub(@PathVariable("ID") int ID, @RequestBody Club club){
        Optional<Club> IdClub = clubs.findById(ID);
        if(IdClub.isPresent()){
            Club previousClub = IdClub.get();
            previousClub.setName(club.getName());
            previousClub.setClubDescriprion(club.getClubDescriprion());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<Void> deleteClub(@PathVariable("ID") int ID){
        clubs.deleteById(ID);
        return ResponseEntity.noContent().build();
    }

}
