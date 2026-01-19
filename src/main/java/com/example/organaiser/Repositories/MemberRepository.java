package com.example.organaiser.Repositories;


import com.example.organaiser.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository <Member, Integer> {
}

