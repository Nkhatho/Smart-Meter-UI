/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Leolen
 */
public class Meter {
    
    private String meterAddress;
    private String meterNumber;
    private BigDecimal previousMeterReading;
    private BigDecimal currentMeterReading;    
    //private String UnitNumber OR standNumber
    public Meter(){}
    public Meter(Meter meter){
        meterAddress = meter.meterAddress;
        meterNumber = meter.meterNumber;
        previousMeterReading = meter.previousMeterReading;
        currentMeterReading = meter.currentMeterReading;
    }

    /**
     * @return the meterAddress
     */
    public String getMeterAddress() {
        return meterAddress;
    }

    /**
     * @param meterAddress the meterAddress to set
     */
    public void setMeterAddress(String meterAddress) {
        this.meterAddress = meterAddress;
    }

    /**
     * @return the meterNumber
     */
    public String getMeterNumber() {
        return meterNumber;
    }

    /**
     * @param meterNumber the meterNumber to set
     */
    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    /**
     * @return the previousMeterReading
     */
    public BigDecimal getPreviousMeterReading() {
        return previousMeterReading;
    }

    /**
     * @param previousMeterReading the previousMeterReading to set
     */
    public void setPreviousMeterReading(BigDecimal previousMeterReading) {
        this.previousMeterReading = previousMeterReading;
    }

    /**
     * @return the currentMeterReading
     */
    public BigDecimal getCurrentMeterReading() {
        return currentMeterReading;
    }

    /**
     * @param currentMeterReading the currentMeterReading to set
     */
    public void setCurrentMeterReading(BigDecimal currentMeterReading) {
        this.currentMeterReading = currentMeterReading;
    }
    @Override
    public String toString(){
        return String.format("Meter Address\t:%s\n"+
               "Meter Number\t:%s\n"+
                "Previous Reading:%f\n" +
                "Current Reading\t:%f\n",
        meterAddress,
        meterNumber,
        previousMeterReading,
        currentMeterReading);
    }
}
