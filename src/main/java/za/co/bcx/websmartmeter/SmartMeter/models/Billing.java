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
public class Billing {
    private BigDecimal unitsConsumed;
    private BigDecimal amountDue;
    public Billing(){}
    public Billing(Billing billing){
        unitsConsumed = billing.unitsConsumed;
        amountDue = billing.amountDue;
    }

    /**
     * @return the unitsConsumed
     */
    public BigDecimal getUnitsConsumed() {
        return unitsConsumed;
    }

    /**
     * @param unitsConsumed the unitsConsumed to set
     */
    public void setUnitsConsumed(BigDecimal unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    /**
     * @return the amountDue
     */
    public BigDecimal getAmountDue() {
        return amountDue;
    }

    /**
     * @param amountDue the amountDue to set
     */
    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }
    
    @Override
    public String toString(){
        return String.format("Consumption\t\t:%f\n" +
                "Amount Due\t\t:%f", unitsConsumed, amountDue);
    }

}
