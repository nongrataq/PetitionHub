    package com.example.petitionhub.entities;

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
    public class Petition {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "title")
        private String title;
        @Column(name = "description")
        private String description;





    }
