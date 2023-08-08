/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.DAO;

import modelo.Puerto;

/**
 *
 * @author gabrielalejandroespinzacoronel
 */
public class PuertoDao extends AdaptadorDao<Puerto>{
    private Puerto puerto;
    public PuertoDao(){
        super(Puerto.class);
    }

    public Puerto getPuerto() {
        if(puerto == null){
            puerto = new Puerto();
        }
        return puerto;
    }
    public void setPuerto(Puerto puerto) {
        this.puerto = puerto;
    }
}
