/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.bcx.websmartmeter.SmartMeter.models.Debtor;

/**
 *
 * @author Leolen
 */
@Controller
@RequestMapping(value="/meters")
public class MeterController {
    
    @RequestMapping(value="/capturereading", method = RequestMethod.POST)
    public String setReading(@RequestBody Debtor debtor){
        return "Meter Detail";
    }
}
