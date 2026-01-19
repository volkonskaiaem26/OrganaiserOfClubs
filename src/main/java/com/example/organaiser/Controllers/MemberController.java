package com.example.organaiser.Controllers;

import com.example.organaiser.Entities.Member;
import com.example.organaiser.Repositories.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/members")
public class MemberController {

    private final MemberRepository members;

    public MemberController(MemberRepository clubs){
        this.members = clubs;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(){
        return ResponseEntity.ok(members.findAll());
    }

    @GetMapping("/{ID}")
    public ResponseEntity<Member> getMemberID(@PathVariable("ID") int ID){
        Optional<Member> IdMember = members.findById(ID);
        if(IdMember.isPresent()){
            return ResponseEntity.ok(IdMember.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity <Void> createMember(@RequestBody Member member){
        members.save(member);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{ID}")
    public ResponseEntity<Void> updateMember(@PathVariable("ID") int ID, @RequestBody Member member){
        Optional<Member> IdMember = members.findById(ID);
        if(IdMember.isPresent()){
            Member previousMember = IdMember.get();
            previousMember.setName(member.getName());
            previousMember.setClassroom(member.getClassroom());
            previousMember.setEmail(member.getEmail());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{ID}")
    public ResponseEntity<Void> deleteMember(@PathVariable("ID") int ID){
        members.deleteById(ID);
        return ResponseEntity.noContent().build();
    }
}
