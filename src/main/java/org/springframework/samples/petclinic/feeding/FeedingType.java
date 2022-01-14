package org.springframework.samples.petclinic.feeding;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feeding_types")
public class FeedingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @NotEmpty(message = "Please enter a name")
    @Size(min = 5, max = 30)
    @Column(name = "name", unique = true)
    String name;

    @NotEmpty(message = "Empty description is not allowed") 
    String description;

    // @NotEmpty(message = "Please enter a type")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    PetType petType;
}
