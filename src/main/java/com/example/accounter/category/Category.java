package com.example.accounter.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @SequenceGenerator(
        name = "category_sequence",
        sequenceName = "category_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "category_sequence",
        strategy = GenerationType.SEQUENCE
    )
    Long categoryId;
    String categoryName;

    public Category(String name){
        this.categoryName=name;
    }
}


