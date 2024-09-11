package com.example.rekazfinalproject.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Commercial Register must not be null")
    @Positive(message = "Commercial Register must be a positive number")
    private int commercialRegister;

    @NotEmpty(message = "Scope of Work must not be blank")
    @Size(min = 5, max = 100, message = "Scope of Work must be between 5 and 100 characters")
    private String scopeOfWork;

    @PositiveOrZero(message = "Number of Projects must be zero or positive")
    private int numOfProject;

    private LocalDate createdAt;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

}
