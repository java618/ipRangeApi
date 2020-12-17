package com.example.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class IpRangeModel {
    private String lowerIp;

    private String upperIp;

    public IpRangeModel() {
    }

    public String getLowerIp() {
        return lowerIp;
    }

    public void setLowerIp(String lowerIp) {
        this.lowerIp = lowerIp;
    }

    public String getUpperIp() {
        return upperIp;
    }

    public void setUpperIp(String upperIp) {
        this.upperIp = upperIp;
    }
}
