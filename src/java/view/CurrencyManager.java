/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ConverterFacade;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named("currencyManager")
@ConversationScoped
public class CurrencyManager implements Serializable{
    @EJB private ConverterFacade converter ;
    private String currency1 ;
    private String currency2;
    private String currency1value;
    private String currency2value;
    List<String> currencies = new ArrayList<>();
    private boolean error = false ;
    private String errorMsg = null ;
    
    
    @Inject
    private Conversation conversation;

    @PostConstruct
    private void init() {
        try {
            currencies = converter.init();
            startConversation();
        } catch (FileNotFoundException ex) {
            System.err.println("Error in Initializing");
        }
    }
    

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    public String getCurrency1 (){
        return currency1;
    }
    
    public String getCurrency2 (){
        return currency2;
    }
    
    public void setCurrency1(String currency1){
        this.currency1 = currency1 ;
        System.out.println(this.currency1);
    }
    
    public void setCurrency2(String currency2){
        this.currency2 = currency2 ;
        System.out.println(this.currency2);
    }
    
    public void setCurrency1value (String currency1value){
        this.currency1value = currency1value;
    }
    
    public String getCurrency1value (){
        return currency1value ;
    }
    
    public String getCurrency2value (){
        return currency2value ;
    }
    
    public List<String> getCurrencyArray(){
        return currencies ;
    }
    
    public String getErrorMsg(){
        return errorMsg ;
    }
    
    public void convert (){
        Double value1 ; 
        try {
            value1 = Double.valueOf(currency1value);
            if (value1 <0) {
                errorMsg = "Please enter positive amount" ;
                currency2value = null;
            }
            else {
                errorMsg = null;
                currency2value = Double.toString(converter.convert(currency1, currency2 , value1));
            }
        } catch (NumberFormatException e){
            errorMsg = "Please enter a numeral value" ;
            currency2value = null;
        }   
    }


    
    
    
    
}
