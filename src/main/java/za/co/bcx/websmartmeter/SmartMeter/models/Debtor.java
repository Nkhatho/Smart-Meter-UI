package za.co.bcx.websmartmeter.SmartMeter.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Leolen
 */
public class Debtor implements Serializable{
    private String idNumber;
    private String name;
    private BigDecimal balance;
    private Meter meter;
   
    public Debtor(Debtor debtor){
        idNumber = debtor.idNumber;
        name = debtor.name;
        balance = debtor.balance;
        meter = new Meter(debtor.getMeter());
    }
    public Debtor(){
        idNumber = "";
        name = "";
        balance = BigDecimal.valueOf(0);
        meter = new Meter();
    }
    
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
     * @return the idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
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
    
//    @Override
//    public String toString(){
//        return String.format("ID Number\t\t:%s\n" +
//               "Name\t\t\t:%s\n" + 
//               "Balance\t\t\t:%f\n" +
//               "%s\n" + 
//               "%s\n", 
//                idNumber,
//                name,
//                balance); 
//    }

    /**
     * @return the meter
     */
    public Meter getMeter() {
        return meter;
    }

    /**
     * @param meter the meter to set
     */
    public void setMeter(Meter meter) {
        this.meter = meter;
    }
 
}
