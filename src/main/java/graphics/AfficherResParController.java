/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import entity.Events;
import entity.Participants;
import entity.Reservation;
import entity.ReservationEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import service.EventService;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherResParController implements Initializable {

    @FXML
    private TableView<ReservationEvent> table_event;
    @FXML
    private TableColumn<ReservationEvent,String> nom_or;
    @FXML
    private TableColumn<ReservationEvent,String> nom_e;
    @FXML
    private TableColumn<ReservationEvent,String> lie;
    @FXML
    private TableColumn<ReservationEvent, Date> dt_e;
    @FXML
    private TableColumn<ReservationEvent,String> desc;
    @FXML
    private TextField tfserach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationService rs=new ReservationService();
         Participants p1= new Participants();
         p1.setId_par(11);
       
         ArrayList<ReservationEvent> list= null; 
        try {
            list = (ArrayList<ReservationEvent>) rs.afficherReservationPar(p1);
        } catch (Exception ex) {
            Logger.getLogger(AfficherResParController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<ReservationEvent> obs=FXCollections.observableArrayList(list);
        table_event.setItems(obs);
        nom_or.setCellValueFactory(new PropertyValueFactory<>("nom_org") );
        nom_e.setCellValueFactory(new PropertyValueFactory<>("nom_event") );
        lie.setCellValueFactory(new PropertyValueFactory<>("lieu") );
        dt_e.setCellValueFactory(new PropertyValueFactory<>("dt_event") );
        desc.setCellValueFactory(new PropertyValueFactory<>("description") );
        
        // TODO
    }    

    @FXML
    private void bt_sup(ActionEvent event) {
         ReservationService cs = new ReservationService();
        
        ReservationEvent a = (ReservationEvent) table_event.getSelectionModel().getSelectedItem();
        cs.supprimerReservationEv(a);
        
        Parent PageParent = null;
        try {
            PageParent = FXMLLoader.load(getClass().getResource("/fxml/AfficherResPar.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AfficherResParController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene PageScene = new Scene(PageParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(PageScene);
        window.show();
    }

    @FXML
    private void search(KeyEvent event) {
         ReservationService rs = new ReservationService();
            String m = tfserach.getText().concat("%");
            ArrayList<ReservationEvent> e= (ArrayList<ReservationEvent>) rs.chercherEv(m);
            ObservableList<ReservationEvent> obs=FXCollections.observableArrayList(e);
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
    
}
