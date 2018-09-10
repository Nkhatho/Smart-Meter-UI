/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.services;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.bcx.websmartmeter.SmartMeter.models.Debtor;
import za.co.bcx.websmartmeter.SmartMeter.models.MessageRequest;
import za.co.bcx.websmartmeter.SmartMeter.models.SystemDB;

/**
 *
 * @author Leolen
 */
@Service
public class DebtorService {
        
    @Autowired SystemDB debtor = new SystemDB();
//    @Autowired MessageRequest msg;
    
    public List<Debtor> getDebtors(){
        return debtor.getDebtors();
    }
    
    public Debtor getDebtor(@RequestParam String idNumber){
        Debtor result = null;
        
        try{
            List<Debtor> debtors = debtor.getDebtors();
            if(!debtors.isEmpty()){
                for (Debtor debt : debtors) {
                    if(debt.getIdNumber().equals(idNumber)){
                        result = new Debtor(debt);
                        break;
                    }
                }//for
                if(result == null ){
//                    msg.setMsg("Debtor does not exist!");
                    throw new Exception("Debtor does not exist!");
                }
            }
            else{
//                msg.setMsg("No debtors currently.");
                throw new Exception("No debtors currently available.");
            }

        }catch(Exception e){   
        }
        
        return result;
    }
    
    //THE RETURN OF THIS METHOD IS JUST TO SHOW WHAT HAS BEEN SET AFTER IT HAS BEEN SET
    //REMEMBER TO REMOVE THE RETURN OF THIS METHOD ONCE DONE TESTING
    
    public String setDebtor(Debtor debtor){
        List<Debtor> debtors = this.debtor.getDebtors();
        debtors.add(debtor);
        return "THE FOLLOWING DEBTOR HAS BEEN SET:\n\n" + debtors.get(debtors.size() - 1).toString();
    }

}
