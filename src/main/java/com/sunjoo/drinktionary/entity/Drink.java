package com.sunjoo.drinktionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Drink {

    @Id
    @Column(name = "drink_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drink_name")
    private String name;
    @Column(name = "ABV")
    private double dosu;
    @Column(name = "drink_kind")
    @Enumerated(value = EnumType.STRING)
    private DrinkType type;
    @Column(name = "price")
    private int price;
    @Column(name = "sugar_content")
    private double sugar;
    @Column(name = "liquid_capacity")
    private int capacity;
    @Column(name = "image")
    private String image;
    @Column(name = "sentiment")
    @Enumerated(value = EnumType.STRING)
    private Sentiment sentiment;
}
