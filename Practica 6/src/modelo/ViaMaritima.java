package modelo;

public class ViaMaritima {
    private Integer origen;
    private Integer destino;
    private Double distancia;

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public ViaMaritima() {
    }

    public ViaMaritima(int origen, int destino, Double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return distancia.toString();
    }
    
    
    
}
