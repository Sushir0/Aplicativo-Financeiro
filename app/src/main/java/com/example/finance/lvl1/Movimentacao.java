package com.example.finance.lvl1;

import android.util.Log;

import java.util.Random;

public class Movimentacao {
    public String assunto;
    private Integer id;
    public double valor;
    public String data;
    public enum Tipo{
        gastoCasa,
        gastoPessoal,
        recebimentoPessoal,
    }
    private Tipo tipoMovimentacao;

    public Movimentacao(String assunto, Tipo tipoMovimentacao, String data, double valor){
        this.assunto = assunto;
        this.tipoMovimentacao = tipoMovimentacao;
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
        Log.d("mostrar", "tipo movimentacao: "+tipoMovimentacao);
    }
}
