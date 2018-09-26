/*
 *Practica Servidor Socket.io comunicación con Java
 */
package com.mycompany.practicaconexion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.event.*;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author J. Rodrigo Ordóñez Pacheco
 */
public class PruebaController implements Initializable {

    @FXML
    private Label avisoServidor;
    @FXML
    private Button btnmensaje;
    @FXML
    private TextField txtfmensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            Socket socket = IO.socket("http://localhost:5000");
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener(){
                @Override
                public void call(Object... os){
                    System.out.println("Conectado...");
                }
            }).on("pruebaConexion", new Emitter.Listener(){
                @Override
                public void call(Object... os){
                    avisoServidor.setText(os[0].toString());
                }
            });
        
            
        
        btnmensaje.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            String mensaje = txtfmensaje.getText();
            socket.emit("pruebaConexion", mensaje);
        }
        });
        
        } catch (URISyntaxException ex) {
            Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

