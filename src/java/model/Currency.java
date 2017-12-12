/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries({
    @NamedQuery(
        name = "findCurrencyByName",
        query = "SELECT curr FROM Currency curr WHERE curr.name LIKE :currencyName"
    )
    ,    
})

@Entity
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String name ;
    
    @Column
    private double convertToSek ;

    public Currency(){
        
    }
    
    public Currency (String name, double sekConversionRate){
        this.name = name;
        convertToSek = sekConversionRate ;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName (){
        return name ;
    }
    
    public void setName(String name){
        this.name = name ;
    }
    
    public double getConvertToSek (){
        return convertToSek;
    }
    
    public void setConvertToSek (double convertToSek) {
        this.convertToSek = convertToSek;
    }

    
    
}
