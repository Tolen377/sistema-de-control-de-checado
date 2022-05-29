/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_web_responsiva.vista;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.mycompany.app_web_responsiva.MainUi;
import com.mycompany.app_web_responsiva.persistencia.entidad.Empleado;
import com.mycompany.app_web_responsiva.persistencia.entidad.EmpleadoSalida;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.mariadb.jdbc.internal.util.pool.Pools.close;


public class EmpleadoSalidaWin extends Window{
    
    private MainUi mainUI;
    
    private long id;
    private TextField clave;
    private TextField fecha;
    
    private Button aceptar;
    private Button cancelar;
    
    
    public EmpleadoSalidaWin(){
        init();
    }
    
    public EmpleadoSalidaWin(long idObj){
        init();
        loadData(idObj);
    }
    
    private void init(){
        mainUI = (MainUi)UI.getCurrent();
        ResponsiveLayout contenido = new ResponsiveLayout();
            contenido.setWidth("100%");
        
        DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        
        id = 0;
        clave = new TextField("Ingrese su clave de empleado");
            clave.setWidth("100%");
        
        fecha = new TextField("Fecha");
            fecha.setWidth("100%");
            fecha.setValue(dtf.format(LocalDateTime.now()));
            fecha.setReadOnly(true);
            
        ResponsiveRow row1 = contenido.addRow()
                .withAlignment(Alignment.BOTTOM_RIGHT);
        row1.setSpacing(ResponsiveRow.SpacingSize.SMALL,true);
        row1.addColumn().withDisplayRules(12, 9, 9, 10).withComponent(clave);
        row1.addColumn().withDisplayRules(12, 9, 9, 10).withComponent(fecha);
        
        
        aceptar = new Button("Aceptar");
            aceptar.setWidth("100%");
            aceptar.addStyleNames(ValoTheme.BUTTON_FRIENDLY);
            aceptar.addClickListener(event -> {eventoBotonAceptar();});
            
        cancelar = new Button("Cancelar");
            cancelar.addStyleNames(ValoTheme.BUTTON_FRIENDLY);
            cancelar.setWidth("100%");
            cancelar.addClickListener(event -> {close();});
        ResponsiveRow row2 = contenido.addRow()
                .withAlignment(Alignment.BOTTOM_RIGHT);
        row2.setSpacing(ResponsiveRow.SpacingSize.SMALL,true);
        row2.addColumn().withDisplayRules(12, 4, 4, 3).withComponent(aceptar);
        row2.addColumn().withDisplayRules(12, 4, 4, 3).withComponent(cancelar);
       
        VerticalLayout content = new VerticalLayout();
            content.setWidth("100%");
            content.setMargin(true);
            content.setSpacing(true);
            content.addComponent(contenido);
        
        setContent(contenido);
        setModal(true);
        setResizable(false);
        setCaptionAsHtml(true);
        setCaption("<h3></center>Datos de Salida</center></h3>");
        center();
        
    }
    
    private void loadData(long idObj){
        try{
            EmpleadoSalida obj = mainUI.getControladorEmpleadoSalida()
                    .getRepositorioEmpleadoSalida().getOne(idObj);
            id = obj.getId();
            clave.setValue(obj.getClave());
            fecha.setValue(obj.getFecha());
            
        }catch(Exception ex){
            System.out.println(ex);
            close();
        }
    }
    
    private void eventoBotonAceptar(){
            try{
                EmpleadoSalida obj = new EmpleadoSalida();
                
                    obj.setId(id);
                    obj.setClave(clave.getValue());
                    obj.setFecha(fecha.getValue());
                obj = mainUI.getControladorEmpleadoSalida().guardar(obj);
                
                if(obj != null){
                    Notification notification = new Notification(
                    "ok"
                    ,"Datos fuardados correctamente"
                    ,Notification.Type.HUMANIZED_MESSAGE
                    );
                    notification.show(mainUI.getPage());
                        close();
                }
            }catch(Exception ex){
                System.out.println(ex);
                Notification notification = new Notification(
                    "Error:"
                    ,ex.getMessage()
                    ,Notification.Type.ERROR_MESSAGE
            );
                notification.show(mainUI.getPage());

        }
    }
}
