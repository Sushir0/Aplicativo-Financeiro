package com.example.finance.lvl1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Casa {
    public String nome;
    protected Integer id;
    public ArrayList<Pessoa> moradores = new ArrayList<>();
    public ArrayList<Movimentacao> gastos = new ArrayList<>();

    public Casa(String nome){
        this.nome = nome;
        Random random = new Random();
        id = random.nextInt( 100000);
        Login.addCasa(this);
    }

    protected boolean addMorador(Pessoa morador){
        return moradores.add(morador);
    }

    public boolean addGasto(Movimentacao move){
        return gastos.add(move);
    }

    private void editGasto(Movimentacao gastoOriginal, Movimentacao gastoModificado){
        Integer index = gastos.indexOf(gastoOriginal);
        gastos.set(index, gastoModificado);
    }

    private boolean excluirGasto(Movimentacao gastoASerExcluidor){
        return gastos.remove(gastoASerExcluidor);
    }

    public void showCasa(){
        Log.d("mostrar", "casa: "+nome);
        Log.d("mostrar", "ID casa: "+id);
        showMoradores();
        showGastos();
    }

    public void showMoradores(){
        for(Pessoa morador : moradores){
            morador.showPessoa();
        }
    }

    public void showGastos(){
        for (Movimentacao gasto : gastos){
            gasto.showMovimentacao();
        }
    }

    public Integer getId(){
        return id;
    }

    public Double getGastosTotais(){
        double soma = 0;
        for (Movimentacao gasto : gastos){
            soma += gasto.valor;
        }
        return soma;
    }

}