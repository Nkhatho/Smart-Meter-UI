package za.co.bcx.websmartmeter.SmartMeter.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 *
 * @author Leolen
 */
@Component
@ApplicationScope
@Repository
public class SystemDB {
    private ArrayList<Billing> billingData = new ArrayList<>();
    private boolean skip;
    
    /**
     * 
     * @return List of all debtors
     */
    public ArrayList<Debtor> getDebtorsDetails(){
        ArrayList<Debtor> debtors = new ArrayList<>();
        
        for(Billing bill : getBillingData()){
            debtors.add(bill.getDebtorDetails());
        }
        
        return debtors;
    }
    
    /**
     * 
     * @param id - Debtor ID number
     * @return The requested debtor
     */
    public Debtor getDebtor(String id){
   
        Debtor result = null;
        if(!billingData.isEmpty()){
            for(Billing bill : getBillingData()){
                result = new Debtor(bill.getDebtorDetails());
                if(result.getIdNumber().equals(id))
                    break;
            }
        }
        return result;
    }
    
    /**
     * 
     * @param debtor - Debtor details
     * @return true if debtor details has been set and is in list
     */
    public boolean setDebtorDetails(Debtor debtor) {
        
        Billing bill = new Billing();
        bill.setUnitsConsumed(BigDecimal.valueOf(0));
        bill.setAmountDue(BigDecimal.valueOf(0));
        bill.setDebtorDetails(debtor);
        bill.setMeterDetails(debtor.getMeter());
        this.getBillingData().add(bill);
        
        return this.getBillingData().contains(bill);
    }

    /**
     * @return the billingData
     */
    public ArrayList<Billing> getBillingData() {
        return billingData;
    }

    /**
     * @param billingData the billingData to set
     */
    public void setBillingData(ArrayList<Billing> billingData) {
        this.billingData = billingData;
    }
//    public void save() {        
//        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + user.getFirstname());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }

  
}
