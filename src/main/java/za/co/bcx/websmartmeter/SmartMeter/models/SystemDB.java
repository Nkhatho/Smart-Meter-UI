/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.models;

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
    private ArrayList<Debtor> debtors = new ArrayList<>();
    
    public ArrayList<Debtor> getDebtors(){
        return debtors;
    }

    /**
     * @param debtor the debtor to set
     */
    public void setDebtor(Debtor debtor) {
        this.debtors.add(debtor);
    }
}
