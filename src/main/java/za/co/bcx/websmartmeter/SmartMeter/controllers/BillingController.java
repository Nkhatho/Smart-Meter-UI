/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Leolen
 */
@Controller
@RequestMapping(value = "/billing")
public class BillingController {
    
    @RequestMapping(value="/unitsconsumed")
    @ResponseBody
    public String unitsConsumed(){
        return "Units Consumed";
    }
    
}
