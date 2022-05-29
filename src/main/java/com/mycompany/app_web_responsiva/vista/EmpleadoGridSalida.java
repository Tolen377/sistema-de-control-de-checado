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
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import static org.mariadb.jdbc.internal.util.pool.Pools.close;

public class EmpleadoGridSalida extends Window{
    
   
    private MainUi mainUI;
    
    private TextField campoBuscar;
    private Button botonBuscar;
    private Button botonAgregar;
    private Grid<EmpleadoSalida> resultados;
    
    
    public EmpleadoGridSalida(){
        init();
    }
    
    private void init(){
        mainUI = (MainUi)UI.getCurrent();
        
        ResponsiveLayout contenido = new ResponsiveLayout();
            contenido.setWidth("100%");
        
        campoBuscar = new TextField("Buscar por clave");
            campoBuscar.setWidth("100%");
            
        botonBuscar = new Button("Buscar");
            botonBuscar.setWidth("100%");
            botonBuscar.addStyleNames(ValoTheme.BUTTON_FRIENDLY);
            botonBuscar.addClickListener(event -> {eventoBotonBuscar();});
            
            
        botonAgregar = new Button("Agregar");
            botonAgregar.setWidth("100%");
            botonAgregar.addStyleNames(ValoTheme.BUTTON_PRIMARY);
            botonAgregar.addClickListener(event -> {mainUI.addWindow(new EmpleadoSalidaWin());});
            
        

            
            
        ResponsiveRow row1 = contenido.addRow()
                .withAlignment(Alignment.BOTTOM_RIGHT);
        row1.setSpacing(ResponsiveRow.SpacingSize.SMALL,true);
        row1.addColumn().withDisplayRules(12, 6, 8, 9).withComponent(campoBuscar);
        row1.addColumn().withDisplayRules(12, 6, 4, 3).withComponent(botonBuscar);
        row1.addColumn().withDisplayRules(12, 6, 4, 3).withComponent(botonAgregar);

       
        resultados = new Grid<>();
            resultados.setWidth("100%");
            resultados.setSelectionMode(Grid.SelectionMode.SINGLE);
            resultados.addComponentColumn(this::crearBotonEditar).setWidth(50);
            resultados.addColumn(EmpleadoSalida::getId).setCaption("Id");
            resultados.addColumn(EmpleadoSalida::getClave).setCaption("Clave");
            resultados.addColumn(EmpleadoSalida::getFecha).setCaption("Fecha y hora de salida");
            
        
        ResponsiveRow row2 = contenido.addRow()
                .withAlignment(Alignment.BOTTOM_RIGHT);
        row2.setSpacing(ResponsiveRow.SpacingSize.SMALL, true);
        row2.addColumn().withDisplayRules(12, 12, 12, 12).withComponent(resultados);
            
        setContent(contenido);
        setWidth("100%");
        setCaptionAsHtml(true);
        setCaption("<h3><center>Listado de Salidas</center></h3>");
            
    }
    
    private Button crearBotonEditar(EmpleadoSalida obj){
        Button boton = new Button();
            boton.setIcon(VaadinIcons.EDIT);
            boton.addStyleNames(
                ValoTheme.BUTTON_BORDERLESS
                ,ValoTheme.BUTTON_ICON_ONLY
            );
            boton.addClickListener(event -> {
                mainUI.addWindow(new EmpleadoSalidaWin(obj.getId()));
            });
        return boton;
    }
    
    private void eventoBotonBuscar(){
        try{
            List<EmpleadoSalida> lista = mainUI
                .getControladorEmpleadoSalida()
                    .getRepositorioEmpleadoSalida()
                        .getByClaveLikeOrderByClave(campoBuscar.getValue()+"%");
            resultados.setItems(lista);
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
