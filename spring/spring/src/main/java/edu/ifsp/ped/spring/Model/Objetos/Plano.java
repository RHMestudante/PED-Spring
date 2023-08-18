package edu.ifsp.ped.spring.Model.Objetos;

abstract class Plano {

    String caminho, data;

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    protected abstract void QuantasAulas(int codPlan);

    public void imprimeCaminho(){
        System.out.println(caminho);
    }
}
