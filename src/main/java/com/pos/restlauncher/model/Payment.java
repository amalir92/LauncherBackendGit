package com.pos.restlauncher.model;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Payment implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long paymentId;
    private String paymentReferenceId;
    private String merchantId;
    private String terminalId;
    private float paymentAmount;
    private Date nextPaymentDate;
   
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType = PaymentType.IPG;

    Payment(){}

    public Payment(String merchantId,String terminalId,Date nextPaymentDate,float paymentAmount,PaymentType paymentType,String paymentReferenceId){
        this.merchantId=merchantId;
        this.terminalId=terminalId;
        this.nextPaymentDate=nextPaymentDate;
        this.paymentAmount=paymentAmount;
        this.paymentType=paymentType;
        this.paymentReferenceId=paymentReferenceId;
    }
}
