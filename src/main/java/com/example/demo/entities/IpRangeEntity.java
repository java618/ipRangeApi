package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity

public class IpRangeEntity {

    @Id
    @GeneratedValue
    private  long id;

    private long lowerRange;

    private long upperRange;

    public IpRangeEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLowerRange() {
        return lowerRange;
    }

    public void setLowerRange(long lowerRange) {
        this.lowerRange = lowerRange;
    }

    public long getUpperRange() {
        return upperRange;
    }

    public void setUpperRange(long upperRange) {
        this.upperRange = upperRange;
    }
}
