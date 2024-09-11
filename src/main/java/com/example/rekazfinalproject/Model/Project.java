package com.example.rekazfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Project title should be not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotEmpty(message = "Project description should be not null")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;

    @NotNull(message = "Project budget should be not null")
    @Column(columnDefinition = "double not null")
    private double budget;

    @NotEmpty(message = "Project type should be not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String projectType;

    @Column(columnDefinition = "datetime")
    private LocalDate creationDate;

    @Column(columnDefinition = "datetime")
    private LocalDate deadline;

    @Column(columnDefinition = "varchar(20)")
    private String status = "open" ;

    @ManyToOne
    @JsonIgnore
    private Owner owner ;

    @ManyToOne
    @JsonIgnore
    private Investor investor ;

    @OneToMany( cascade = CascadeType.ALL , mappedBy = "project" )
    private Set<Bid> bid ;

    @OneToOne( cascade = CascadeType.ALL , mappedBy = "project" )
    private Contract contract ;





}
