package com.example.rekazfinalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@Entity
@Setter
@Getter
public class Complaint{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String type;
    private Integer project_num;
    private String  description;
    private String file;

    private String priority_level;

}
