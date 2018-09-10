/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.websmartmeter.SmartMeter.models;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 *
 * @author Leolen
 */
@Component
@ApplicationScope
public class MessageRequest implements Serializable{
    private String msg;

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
