/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import entity.Events;
import entity.Participants;
import entity.Reservation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.EventService;
import service.ParticipantService;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ParticipantEventController implements Initializable {

    @FXML
    private TableView table_event;
    @FXML
    private TableColumn<?, ?> nom_or;
    @FXML
    private TableColumn<?, ?> nom_e;
    @FXML
    private TableColumn<?, ?> lie;
    @FXML
    private TableColumn<?, ?> nb;
    @FXML
    private TableColumn<?, ?> dt_e;
    private TableColumn<?, ?> h_e;
    @FXML
    private TableColumn<?, ?> px;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TextField tfserach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ////////////////Afficher///////////
          String m = "Accepte";
        EventService p= new EventService();
        ArrayList<Events> e = (ArrayList<Events>) p.cherche(m); 
        ObservableList<Events> obs=FXCollections.observableArrayList(e);
        table_event.setItems(obs);
        nom_or.setCellValueFactory(new PropertyValueFactory<>("nom_org") );
        nom_e.setCellValueFactory(new PropertyValueFactory<>("nom_event") );
        lie.setCellValueFactory(new PropertyValueFactory<>("lieu") );
        nb.setCellValueFactory(new PropertyValueFactory<>("nb_place") );
        dt_e.setCellValueFactory(new PropertyValueFactory<>("dt_event") );
    //  h_e.setCellValueFactory(new PropertyValueFactory<>("h_event") );
        px.setCellValueFactory(new PropertyValueFactory<>("prix") );
        desc.setCellValueFactory(new PropertyValueFactory<>("description") );
        
         EventService ps=new EventService();
    }    

    @FXML
    private void search(KeyEvent event) {
        
         EventService p = new EventService();
          
            String m = tfserach.getText().concat("%");
            ArrayList<Events> e= (ArrayList<Events>) p.chercher(m);
            ObservableList<Events> obs=FXCollections.observableArrayList(e);
            table_event.setItems(obs);
    }

    @FXML
    private void retour(ActionEvent event) {
         Parent PageParent = null;
        try {
            PageParent = FXMLLoader.load(getClass().getResource("/fxml/Part.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ModifEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene PageScene = new Scene(PageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }
    
     @FXML
    private void bt_participer(ActionEvent event) {
         Parent PageParent = null;
          EventService cs = new EventService();
          ReservationService res=new ReservationService();
         // m=getlogin;
        ParticipantService ps=new ParticipantService();
        Participants p=new Participants(11,"kobbi","ayman","6546","eaemail",50,"aaa");
        Events a = (Events) table_event.getSelectionModel().getSelectedItem();
        Reservation r1=new Reservation(1,a.getId_ev(),p.getId_par(),p.getNom(),p.getPrenom(),a.getNom_event());
        try {
            res.ajouterReservation(r1);
            JOptionPane.showMessageDialog(null, "participation etablie");
                     

        } catch (Exception ex) {
            Logger.getLogger(ParticipantEventController.class.getName()).log(Level.SEVERE, null, ex);
                     JOptionPane.showMessageDialog(null,ex.getMessage());

        }
        try {
            PageParent = FXMLLoader.load(getClass().getResource("/fxml/ParticipantEvent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ParticipantEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene PageScene = new Scene(PageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }
    
}
