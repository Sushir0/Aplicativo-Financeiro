package com.example.finance.lvl1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Pessoa {

    private String nome;
    private Integer id;
    private ArrayList<Movimentacao> gastos = new ArrayList<>();
    private ArrayList<Movimentacao> recebimentos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public ArrayList<Movimentacao> getGastos() {
        return gastos;
    }

    public ArrayList<Movimentacao> getRecebimentos() {
        return recebimentos;
    }

    public Pessoa(String nome, Casa casa){
        this.nome = nome;
        Random random = new Random();
        id = random.nextInt( 100000);
        casa.addMorador(this);
    }

    public boolean addGasto(Movimentacao move){
        return gastos.add(move);
    }

    public boolean excluirGasto(Movimentacao move){
        return gastos.remove(move);
    }

    public void editarGasto(Movimentacao gastoOriginal, Movimentacao gastoModificado){
        Integer index = gastos.indexOf(gastoOriginal);
        gastos.set(index, gastoModificado);
    }

    public boolean addRecebimento(Movimentacao move){
        return recebimentos.add(move);
    }

    public boolean excluirRecebimento(Movimentacao move){
        return recebimentos.remove(move);
    }

    public void editarRecebimento(Movimentacao recebimentoOriginal, Movimentacao recebimentoModificado){
        Integer index = recebimentos.indexOf(recebimentoOriginal);
        recebimentos.set(index, recebimentoModificado);
    }

    public void showPessoa() {
        Log.d("mostrar", "nome: "+nome);
        Log.d("mostrar", "ID: "+id);
        showGastos();
        showRecebimentos();
    }

    public void showGastos(){
        for (Movimentacao gasto : gastos){
            gasto.showMovimentacao();
        }
    }

    public void showRecebimentos(){
        for (Movimentacao recebimento : recebimentos){
            recebimento.showMovimentacao();
        }
    }

    public Double getGastosTotais(){
        double soma = 0;
        for (Movimentacao gasto : gastos){
            soma += gasto.valor;
        }
        return soma;
    }
}
