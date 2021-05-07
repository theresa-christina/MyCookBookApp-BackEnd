package com.passionproject.cookbook.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String recipeName;
    private Integer numberServings;

    // ElementCollection can be used to define a one-to-many relationship to an Embeddable object or a Basic value
    @ElementCollection
    private List<String> ingredientsList;

    @ElementCollection
    private List<String> preparationSteps;

    @OneToMany
    private List<Note> notes;

    @OneToMany
    private List<Photo> photos;

    @ManyToOne
    private User user;

}
