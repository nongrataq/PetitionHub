    package com.example.petitionhub.entities.PetitionEntity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Entity
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class PetitionEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, name = "title")
        private String title;

        @Column(nullable = false, name = "description")
        private String description;
<<<<<<<< HEAD:src/main/java/com/example/petitionhub/entities/PetitionEntity/Petition.java












========
>>>>>>>> origin/main:src/main/java/com/example/petitionhub/entities/PetitionEntity.java
    }
