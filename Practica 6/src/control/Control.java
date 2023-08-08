/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import control.DAO.PuertoDao;
import control.DAO.ViaDao;
import control.ed.grafo.GrafoEtiquetadoD;
import control.ed.lista.ListaEnlazada;
import control.ed.lista.exception.PosicionException;
import control.ed.lista.exception.VacioException;
import control.ed.grafo.exception.GrafoSizeExeption;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.Puerto;
import modelo.ViaMaritima;

/**
 *
 * @author gabrielalejandroespinzacoronel
 */
public class Control {

    private PuertoDao pDao = new PuertoDao();
    private ViaDao vDao = new ViaDao();
    private ListaEnlazada<Puerto> puertos = new ListaEnlazada<>();
    private GrafoEtiquetadoD<Puerto> grafoPuerto;

    public Control() {
        etiquetarGrafo();
    }

    public void etiquetarGrafo() {
        puertos = pDao.listar();
        ListaEnlazada<ViaMaritima> vias = vDao.listar();
        grafoPuerto = new GrafoEtiquetadoD<>(puertos.size());

        for (int i = 1; i <= puertos.size(); i++) {
            try {
                grafoPuerto.etiquetarVertice(i, puertos.get(i - 1));
            } catch (VacioException | PosicionException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        for (int i = 0; i < vias.size(); i++) {
            try {
                ViaMaritima via = vias.get(i);
                grafoPuerto.insertar(via.getOrigen(), via.getDestino(), via.getDistancia());
            } catch (VacioException | PosicionException | GrafoSizeExeption ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    public void cargarPuertos(JComboBox cb) {
        cb.removeAllItems();
        Puerto[] puertos = pDao.listar().toArray();

        for (Puerto p : puertos) {
            cb.addItem(p.getNombrePuerto());
        }

    }

    public void guardarPuerto(String nombre) throws IOException {
        pDao.getPuerto().setIdPuerto(pDao.generarId());
        pDao.getPuerto().setNombrePuerto(nombre);
        pDao.guardar(pDao.getPuerto());
        etiquetarGrafo();
    }

    public void guardarViasMaritimas(Integer origen, Integer destino, Double peso) throws IOException {
        vDao.getVia().setOrigen(origen);
        vDao.getVia().setDestino(destino);
        vDao.getVia().setDistancia(peso);
        vDao.guardar(vDao.getVia());
        etiquetarGrafo();
    }

    public GrafoEtiquetadoD<Puerto> metodoFloyd(Integer origen, Integer destino) throws Exception {
        long startTime = System.currentTimeMillis();
        ListaEnlazada<Integer> camino = MetodosBusqueda.floydWarshall(origen, destino, grafoPuerto);
        long finalTime = System.currentTimeMillis() - startTime;

        JOptionPane.showMessageDialog(null, "Tiempo: " + finalTime + " ms");
        return crearGrafo(camino);
    }

    public GrafoEtiquetadoD<Puerto> metodoBellman(Integer origen, Integer destino) throws Exception {
        long startTime = System.currentTimeMillis();
        ListaEnlazada<Integer> camino = MetodosBusqueda.floydWarshall(origen, destino, grafoPuerto);
        long finalTime = System.currentTimeMillis() - startTime;

        JOptionPane.showMessageDialog(null, "Tiempo: " + finalTime + " ms");
        return crearGrafo(camino);
    }

    private GrafoEtiquetadoD<Puerto> crearGrafo(ListaEnlazada<Integer> camino) throws GrafoSizeExeption {
        GrafoEtiquetadoD<Puerto> grafoCamino = new GrafoEtiquetadoD<>(camino.size());
        Integer[] arreglo = camino.toArray();

        for (int i = 0; i < arreglo.length; i++) {
            grafoCamino.etiquetarVertice(i + 1, grafoPuerto.getEtiqueta(arreglo[i]));
        }

        for (int i = 1; i <= arreglo.length; i++) {
            if (i == arreglo.length) {
                break;
            }
            grafoCamino.insertar(i, i + 1, grafoPuerto.pesoArista(arreglo[i - 1], arreglo[i]));
        }

        return grafoCamino;
    }

    public PuertoDao getpDao() {
        return pDao;
    }

    public void setpDao(PuertoDao pDao) {
        this.pDao = pDao;
    }

    public ViaDao getvDao() {
        return vDao;
    }

    public void setvDao(ViaDao vDao) {
        this.vDao = vDao;
    }

    public ListaEnlazada<Puerto> getPuertos() {
        return puertos;
    }

    public void setPuertos(ListaEnlazada<Puerto> puertos) {
        this.puertos = puertos;
    }

    public GrafoEtiquetadoD<Puerto> getGrafoPuerto() {
        return grafoPuerto;
    }

    public void setGrafoPuerto(GrafoEtiquetadoD<Puerto> grafoPuerto) {
        this.grafoPuerto = grafoPuerto;
    }

}
