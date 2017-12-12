/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import model.Currency;



@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class CurrencyConverterDAO {
    @PersistenceContext(unitName = "CurrencyConverterPU")
    private EntityManager em ;
    
    public Currency findCurrencyByName(String name) {
        try {
            Currency curr = em.createNamedQuery("findCurrencyByName", Currency.class).
                        setParameter("currencyName", name).getSingleResult();
            return curr;
        } catch (NoResultException e){
            return null ;
        }
    }
    
    
    public void storeCurrency(Currency curr) {
            Currency curr1 = findCurrencyByName (curr.getName());
            if (curr1 != null)
                em.remove(curr1);
            em.persist(curr);
    }
}
