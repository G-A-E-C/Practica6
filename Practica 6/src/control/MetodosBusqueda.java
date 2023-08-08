/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import control.ed.grafo.Adycencia;
import control.ed.grafo.GrafoEtiquetadoD;
import control.ed.lista.ListaEnlazada;
import java.util.Objects;
import modelo.Puerto;
import modelo.ViaMaritima;

/**
 *
 * @author gabrielalejandroespinzacoronel
 */
public class MetodosBusqueda {

    public static ListaEnlazada<Integer> floydWarshall(Integer origen, Integer destino, GrafoEtiquetadoD<Puerto> grafo) throws Exception {

        ListaEnlazada<Integer> respuesta = new ListaEnlazada<>();

        Integer numVertices = grafo.numVertices();

        Double[][] adj = adyacencia(grafo);

        Integer[][] ruta = new Integer[numVertices + 1][numVertices + 1];

        Integer[][] camino = new Integer[numVertices + 1][numVertices + 1];

        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                camino[i][j] = j;
                ruta[i][j] = i;
                if (i == j) {
                    camino[i][j] = 0;
                    ruta[i][j] = 0;
                }
            }
        }

        for (int k = 1; k <= numVertices; k++) {
            for (int i = 1; i <= numVertices; i++) {
                for (int j = 1; j <= numVertices; j++) {
                    if (adj[i][k] + adj[k][j] < adj[i][j]) {
                        adj[i][j] = adj[i][k] + adj[k][j];
                        camino[i][j] = ruta[k][j];
                    }
                }
            }
        }

        Integer i = origen;
        Integer j = destino;

        while (i != j) {
            respuesta.insertar(i);
            i = camino[i][j];
        }

        respuesta.insertar(i);

        return respuesta;
    }

    public static ListaEnlazada<Integer> bellmanFord(Integer origen, Integer destino, GrafoEtiquetadoD<Puerto> grafo) {
        Integer numVertices = grafo.numVertices();
        Integer numAristas = grafo.numAristas();
        ViaMaritima[] pesos = pesos(numVertices, numAristas, grafo);
        ListaEnlazada<Integer> respuesta = new ListaEnlazada<>();
        double[] distancias = new double[numVertices];
        String[] camino = new String[numVertices];

        for (int i = 0; i < numVertices; ++i) {
            distancias[i] = Double.MAX_VALUE;
            camino[i] = "Inf";
        }

        distancias[origen - 1] = 0;
        camino[origen - 1] = String.valueOf(origen);

        for (int i = 1; i < numVertices; ++i) {
            for (int j = 0; j < numAristas; ++j) {
                Integer ori = pesos[j].getOrigen() - 1;
                Integer dest = pesos[j].getDestino() - 1;
                Double peso = pesos[j].getDistancia();
                if (distancias[ori] != Double.MAX_VALUE && distancias[ori] + peso < distancias[dest]) {
                    distancias[dest] = distancias[ori] + peso;
                    camino[dest] = String.valueOf(ori + 1);
                }
            }
        }

        for (int j = 0; j < numVertices; ++j) {
                Integer ori = pesos[j].getOrigen() - 1;
                Integer dest = pesos[j].getDestino() - 1;
                Double peso = pesos[j].getDistancia();
            if (distancias[ori] != Double.MAX_VALUE && distancias[ori] + peso < distancias[dest]) {
                System.out.println("NO se puede metir la distancia porque hay un ciclo en el grafo");
                return null;
            }
        }

        if (camino[destino - 1].equals("Inf")) {
            System.out.println("No existe un camino");
            return null;
        }

        respuesta.insertar(destino);

        do {

            destino = Integer.valueOf(camino[destino - 1]);

            respuesta.insertarInicio(destino);

        } while (!Objects.equals(destino, origen));

        return respuesta;
    }

    private static ViaMaritima[] pesos(Integer vertices, Integer artistas, GrafoEtiquetadoD<Puerto> grafo) {

        ViaMaritima[] pesos = new ViaMaritima[artistas];

        Integer contador = 0;

        for (int i = 1; i <= vertices; i++) {
            contador = agregar(i, contador, pesos, grafo);
        }

        return pesos;
    }

    public static Double[][] adyacencia(GrafoEtiquetadoD<Puerto> grafo) throws Exception {

        Integer numVertices = grafo.numVertices();

        Double[][] adj = new Double[numVertices + 1][numVertices + 1];

        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                if (i == j) {
                    adj[i][j] = 0.0;
                } else {
                    if (!grafo.existeArista(i, j)) {
                        adj[i][j] = Double.MAX_VALUE;
                    } else {
                        adj[i][j] = grafo.pesoArista(i, j);
                    }
                }
            }
        }
        return adj;
    }

    private static int agregar(int origen, int destino, ViaMaritima[] pesos, GrafoEtiquetadoD<Puerto> grafo) {

        if (grafo.listaAdycencia[origen].isEmpty()) {
            return destino;
        }

        Adycencia[] adyacencias = grafo.listaAdycencia[origen].toArray();

        for (Adycencia ady : adyacencias) {
            pesos[destino] = new ViaMaritima(origen, ady.getDestino(), ady.getPeso());
            destino++;
        }

        return destino;
    }

}
