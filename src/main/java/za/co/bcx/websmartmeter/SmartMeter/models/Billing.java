package za.co.bcx.websmartmeter.SmartMeter.models;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Leolen
 */
public class Billing implements Serializable{
    private BigDecimal unitsConsumed;
    private BigDecimal amountDue;
    private Debtor debtorDetails;
    private Meter meterDetails;
    
    public Billing(){
        unitsConsumed = BigDecimal.valueOf(0);
        amountDue = BigDecimal.valueOf(0);
        debtorDetails = new Debtor();
        meterDetails = new Meter();
    }
    
    public Billing(Billing billing){
        unitsConsumed = billing.unitsConsumed;
        amountDue = billing.amountDue;
        this.debtorDetails = new Debtor(billing.getDebtorDetails());
        this.meterDetails = new Meter(billing.getMeterDetails());
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

    /**
     * @return the debtorDetails
     */
    public Debtor getDebtorDetails() {
        return debtorDetails;
    }

    /**
     * @param debtorDetails the debtorDetails to set
     */
    public void setDebtorDetails(Debtor debtorDetails) {
        this.debtorDetails = new Debtor(debtorDetails);
    }

    /**
     * @return the meterDetails
     */
    public Meter getMeterDetails() {
        return meterDetails;
    }

    /**
     * @param meterDetails the meterDetails to set
     */
    public void setMeterDetails(Meter meterDetails) {
        this.meterDetails = meterDetails;
    }

}
