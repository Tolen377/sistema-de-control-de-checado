/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.app_web_responsiva.controlador;

import com.mycompany.app_web_responsiva.persistencia.entidad.Empleado;
import com.mycompany.app_web_responsiva.persistencia.entidad.EmpleadoSalida;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mycompany.app_web_responsiva.persistencia.repositorio.RepositorioEmpleadoSalida;


@Component

public class ControladorEmpleadoSalida {
    @Getter 
    @Autowired private RepositorioEmpleadoSalida repositorioEmpleadoSalida;
    
    private boolean validar(EmpleadoSalida obj) throws Exception{
        try{
            if(obj.getClave().isEmpty()){throw new Exception("Debes proporcionar el nombre");}
            if(obj.getFecha().isEmpty()){throw new Exception("Proporciona un correo");}
            return true;
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public EmpleadoSalida guardar(EmpleadoSalida obj) throws Exception{
        try{
            if(validar(obj)){
                if(obj.getId() == 0) return repositorioEmpleadoSalida.save(obj);
                
                EmpleadoSalida old = repositorioEmpleadoSalida.findById(obj.getId()).get();
                    old.setClave(obj.getClave());
                    old.setFecha(obj.getFecha());
                    
                return repositorioEmpleadoSalida.save(old);
            }
            return null;
        }catch(Exception ex){
           throw ex; 
        }
    }
}
