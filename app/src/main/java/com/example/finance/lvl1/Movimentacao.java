package com.example.finance.lvl1;

import android.util.Log;

import java.util.Random;

public class Movimentacao {
    public String assunto;
    private Integer id;
    public double valor;
    public Data data;
    public Categoria categoria;

    public Movimentacao(String assunto, Data data, double valor){
        this.assunto = assunto;
        this.data = data;
        this.valor = valor;
        Random random = new Random();
        id = random.nextInt( 100000);
    }
    public void showMovimentacao() {
        Log.d("mostrar", "assunto: "+assunto);
        Log.d("mostrar", "ID: "+id);
        Log.d("mostrar", "data: "+data);
        Log.d("mostrar", "valor: "+valor);
    }

    public Boolean isGasto(){
        return categoria.isGasto();
    }






    public void setCategoria(Categoria categoria){ this.categoria = categoria; }
}
