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
public class Debtor implements Serializable{
    private String name;
    private String idNumber;
    private BigDecimal balance;
    
    private Billing billingInfo;
    private Meter meterInfo;
//    
//    public Debtor(String id, String name, long balance,
//            Billing billing, Meter meter){
//        this.id = id;
//        this.name = name;
//        this.balance = balance;
//        billingInfo = new Billing(billing);
//        meterInfo = new Meter(meter);
//    }
    
        
    public Debtor(Debtor debtor){
        idNumber = debtor.idNumber;
        name = debtor.name;
        balance = debtor.balance;
        billingInfo = new Billing(debtor.getBillingInfo());
        meterInfo = new Meter(debtor.getMeterInfo());
    }
    public Debtor(){}
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the id to set
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Override
    public String toString(){
        return String.format("ID Number\t\t:%s\n" +
               "Name\t\t\t:%s\n" + 
               "Balance\t\t\t:%f\n" +
               "%s\n" + 
               "%s\n", 
                idNumber,
                name,
                balance, 
                getBillingInfo().toString(),
                getMeterInfo()); 
    }

    /**
     * @return the billingInfo
     */
    public Billing getBillingInfo() {
        return new Billing(billingInfo);
    }

    /**
     * @param billingInfo the billingInfo to set
     */
    public void setBillingInfo(Billing billingInfo) {
        this.billingInfo = new Billing(billingInfo);
    }

    /**
     * @return the meterInfo
     */
    public Meter getMeterInfo() {
        return new Meter (meterInfo);
    }

    /**
     * @param meterInfo the meterInfo to set
     */
    public void setMeterInfo(Meter meterInfo) {
        this.meterInfo = new Meter(meterInfo);
    }

    
    
}
