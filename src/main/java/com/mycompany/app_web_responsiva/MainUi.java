/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_web_responsiva;

import com.mycompany.app_web_responsiva.controlador.ControladorEmpleado;
import com.mycompany.app_web_responsiva.controlador.ControladorEmpleadoSalida;
import com.mycompany.app_web_responsiva.vista.EmpleadoGrid;
import com.mycompany.app_web_responsiva.vista.EmpleadoGridSalida;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Viewport;
import com.vaadin.guice.annotation.DefaultView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI
@PreserveOnRefresh

@Viewport("width=device-width, initial-scale=1")
public class MainUi extends UI{

    @Getter @Autowired private ControladorEmpleado controladorEmpleado;
    @Getter @Autowired private ControladorEmpleadoSalida controladorEmpleadoSalida;
    
    @Override
    protected void init(VaadinRequest request){
        setSizeFull();
        getUI().getPage().setTitle("Sistema de control de checado");
       
        Button entrada = new Button("Registrar entrada");
            entrada.setWidth("100%");
            entrada.addStyleNames(ValoTheme.BUTTON_PRIMARY);
            entrada.addClickListener(event -> {addWindow(new EmpleadoGrid());});
            
                
        Button salida = new Button("Registrar salida");
            salida.setWidth("100%");
            salida.addStyleNames(ValoTheme.BUTTON_PRIMARY);
            salida.addClickListener(event -> {addWindow(new EmpleadoGridSalida());});
        
        
        HorizontalLayout content = new HorizontalLayout(entrada,salida);
        setContent(content);
    }
}
