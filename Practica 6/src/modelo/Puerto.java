/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author gabrielalejandroespinzacoronel
 */
public class Puerto {
    private Integer idPuerto;
    private String nombrePuerto;

    public int getIdPuerto() {
        return idPuerto;
    }

    public void setIdPuerto(int idPuerto) {
        this.idPuerto = idPuerto;
    }

    public String getNombrePuerto() {
        return nombrePuerto;
    }

    public void setNombrePuerto(String nombrePuerto) {
        this.nombrePuerto = nombrePuerto;
    }

    @Override
    public String toString() {
        return nombrePuerto +"("+ idPuerto +")";
    }

    
    
}
