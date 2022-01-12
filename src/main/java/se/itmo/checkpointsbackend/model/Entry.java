package se.itmo.checkpointsbackend.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entries")
@Data
public class Entry extends BaseEntity{

    private double x;
    private double y;
    private double r;
    private boolean inArea;
    private long workTime;
}
