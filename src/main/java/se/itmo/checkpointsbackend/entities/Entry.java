package se.itmo.checkpointsbackend.entities;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entries")
@NoArgsConstructor
public class Entry extends BaseEntity{
    private double x;
    private double y;
    private double r;
    private boolean inArea;
    private long workTime;

    public Entry(double x, double y, double r) {
        this.x=x;
        this.y=y;
        this.r=r;
    }
}
