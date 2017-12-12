/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import integration.CurrencyConverterDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import model.Currency;


@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class ConverterFacade {
    @EJB CurrencyConverterDAO currencyDB ;
    
    public List<String> init() throws FileNotFoundException{
        File file = new File("C:\\Users\\Sarah\\Documents\\NetBeansProjects\\CurrencyConverter\\src\\java\\integration\\currencies.txt");
        Scanner input = new Scanner(file);
        List<String> currencies = new ArrayList<>();
        while(input.hasNext()) {
            String name = input.next();
            //System.err.println(name);
            currencies.add(name);
            String value = input.next();
            //System.err.println(value);
            createCurrency(name , Double.parseDouble(value));
        }
        input.close();
        return currencies ;
    }
    
    private void createCurrency(String name, double sekConversionRate) {
        Currency curr = new Currency(name, sekConversionRate);
        currencyDB.storeCurrency(curr);
    }
    
    private Currency findCurrency(String name) {
        return currencyDB.findCurrencyByName(name);
    }
    
    
    public double convert(String currency1, String currency2, double value) {
        Currency curr1 = findCurrency (currency1);
        Currency curr2 = findCurrency (currency2);
        return (value / curr1.getConvertToSek()) * curr2.getConvertToSek() ;
    }
    
    
    
}
