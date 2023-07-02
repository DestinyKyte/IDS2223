package it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.pointsProgram;

import jakarta.persistence.*;

import java.util.Date;
@Entity(name = "points")
public class Points {
    @Id
    @GeneratedValue(generator = "points_id_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "points_id_generator")
    @Column(name = "id", nullable = false)
    private Long id;

    private float pointsValue;
    @Temporal(TemporalType.DATE)
    private Date pointsExpirationDate; // potrebbe anche essere una variabile periodo espressa in numero di giorni.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPointsValue() {
        return pointsValue;
    }

    public void setPointsValue(float pointsValue) {
        this.pointsValue = pointsValue;
    }

    public Date getPointsExpirationDate() {
        return pointsExpirationDate;
    }

    public void setPointsExpirationDate(Date pointsExpirationDate) {
        this.pointsExpirationDate = pointsExpirationDate;
    }
}
