package com.pos.restlauncher.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

public class Merchant implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private @Id String merchantId;

  private String terminalId;

  private String deviceId;

  private int startYear;

  private int startMonth;

  private int startDate;

  private Date duration;

  private boolean isActive;

  Merchant() {}

  public Merchant(String merchantId, int startYear,int startMonth,int startDate,Date duration,boolean isActive) {

    this.merchantId= merchantId;
    this.startYear = startYear;
    this.startMonth=startMonth;
    this.startDate=startDate;
    this.duration=duration;
    this.isActive=isActive;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Merchant))
      return false;
    Merchant Merchant = (Merchant) o;
    return Objects.equals(this.merchantId, Merchant.merchantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.merchantId);
  }

  @Override
  public String toString() {
    return "Merchant{" + "id=" + this.merchantId + ", isActive='" + this.isActive + '\'' + '}';
  }

    
}
