package org.springframework.samples.petclinic.feeding;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
            
    @NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "start_date")
    LocalDate startDate;

    @NotNull
    @PositiveOrZero
    @Column(name = "weeks_duration")
    double weeksDuration;
    
    @ManyToOne(cascade = CascadeType.ALL)
    Pet pet;   
}
