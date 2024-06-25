package com.sunjoo.drinktionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drink_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "star_rating")
    private Double starRating;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", updatable = false)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink drink;

    @PrePersist
    protected void onCreate() {
        this.createAt = new Date();
    }

    public void updateContent(final String content) {
        this.content = content;
    }

    public void updateRating(final double rating) {
        this.starRating = rating;
    }

    public void updateDate(final Date date) {
        this.createAt = date;
    }



}
