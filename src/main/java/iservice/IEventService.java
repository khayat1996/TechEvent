/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iservice;

import entity.Events;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IEventService {
    public void creerEvent(Events event);
    public void modifEvent(Events event);
    public void supprEvent(Events event);
    public Events rechercheEventByID(int id);;
    public List<Events> affichierEvent();
    
}