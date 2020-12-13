package com.pos.restlauncher.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Terminal implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private @Id String terminalId;
    private String deviceId;
    private String merchantId;
    
Terminal(){}

public Terminal(String terminalId,String merchantId,String deviceId){
    this.terminalId=terminalId;
    this.deviceId=deviceId;
}
    
}
