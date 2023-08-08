package control.DAO;

import modelo.ViaMaritima;

public class ViaDao extends AdaptadorDao<ViaMaritima>{
    private ViaMaritima via;
    
    public ViaDao(){
        super(ViaMaritima.class);
    }

    public ViaMaritima getVia() {
        if(via == null){
            via = new ViaMaritima();
        }
        return via;
    }

    public void setVia(ViaMaritima via) {
        this.via = via;
    }
    
    
}
