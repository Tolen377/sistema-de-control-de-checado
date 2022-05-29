/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_web_responsiva.controlador;

import com.mycompany.app_web_responsiva.persistencia.entidad.Empleado;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.app_web_responsiva.persistencia.repositorio.RepositorioEmpleado;


@Component

public class ControladorEmpleado {
    @Getter 
    @Autowired private RepositorioEmpleado repositorioEmpleado;
    
    private boolean validar(Empleado obj) throws Exception{
        try{
            if(obj.getClave().isEmpty()){throw new Exception("Debes proporcionar el nombre");}
            if(obj.getFecha().isEmpty()){throw new Exception("Proporciona un correo");}
            return true;
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public Empleado guardar(Empleado obj) throws Exception{
        try{
            if(validar(obj)){
                if(obj.getId() == 0) return repositorioEmpleado.save(obj);
                
                Empleado old = repositorioEmpleado.findById(obj.getId()).get();
                    old.setClave(obj.getClave());
                    old.setFecha(obj.getFecha());
                    
                return repositorioEmpleado.save(old);
            }
            return null;
        }catch(Exception ex){
           throw ex; 
        }
    }
}
