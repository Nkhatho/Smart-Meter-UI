/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.controllers;

import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.inject.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.bcx.websmartmeter.SmartMeter.models.Billing;
import za.co.bcx.websmartmeter.SmartMeter.models.Debtor;
import za.co.bcx.websmartmeter.SmartMeter.models.Meter;
import za.co.bcx.websmartmeter.SmartMeter.services.DebtorService;

/**
 *
 * @author Leolen
 */
@RestController
@RequestMapping(value="/debtors")
public class DebtorController {

    @Autowired private DebtorService debtorService;
    
    //GET ALL DEBTORS
    @RequestMapping(value ="/debtors", method = RequestMethod.GET)
    @Produces()
    public ResponseEntity<List<Debtor>> getDebtors(){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<List<Debtor>> debtorResponse;
        List<Debtor> debtors = debtorService.getDebtors();
        if( debtors == null){
            debtorResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        else{
            debtorResponse = new ResponseEntity<>(debtors, responseHeaders, HttpStatus.OK);
        }
        //could also add meter and billing info to the Debtor class
        return debtorResponse;
    } 
    @RequestMapping(value ="/debtor", method = RequestMethod.GET)
    public ResponseEntity<Debtor> getDebtor(@RequestParam String idNumber){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<Debtor> debtorResponse;
        Debtor debtor = debtorService.getDebtor(idNumber);
        if( debtor == null){
            debtorResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        else{
            debtorResponse = new ResponseEntity<>(debtor, responseHeaders, HttpStatus.OK);
        }
        //could also add meter and billing info to the Debtor class
        return debtorResponse;
    }   
    
    //could also add another parameter of Meter info and Billing info
    @RequestMapping(value= "/setdebtor", method = RequestMethod.POST)
    public String setDebtor(@RequestBody Debtor debtor){
        return debtorService.setDebtor(debtor);
    }
    
    @RequestMapping(value = "/getdebtor/checkbalance", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getBalance(@RequestParam String idNumber){
        HttpHeaders responseHeaders = new HttpHeaders();
        ResponseEntity<BigDecimal> debtorResponse;
        Debtor debtor = debtorService.getDebtor(idNumber);
        if( debtor == null){
            debtorResponse = new ResponseEntity<>(responseHeaders, HttpStatus.NO_CONTENT);
        }
        else{
            debtorResponse = new ResponseEntity<>(debtor.getBalance(), responseHeaders, HttpStatus.OK);
        }
        //could also add meter and billing info to the Debtor class
        return debtorResponse;
    }
}
