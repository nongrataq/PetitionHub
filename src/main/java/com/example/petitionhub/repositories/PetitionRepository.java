package com.example.petitionhub.repositories;

import com.example.petitionhub.entities.Petition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetitionRepository extends JpaRepository<Petition, Long> {

}
