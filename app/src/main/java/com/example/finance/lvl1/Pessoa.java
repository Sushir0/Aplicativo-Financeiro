package com.example.finance.lvl1;

import android.util.Log;

import com.example.finance.R;

import java.util.ArrayList;
import java.util.Random;

public class Pessoa {

    private String nome;
    private Integer id;
    private ArrayList<Movimentacao> movimentacoes = new ArrayList<>();
    private Perfil perfil = new Perfil();

    public String getNome() {
        return nome;
    }

    public ArrayList<Movimentacao> getGastos() {
        ArrayList<Movimentacao> gastos = new ArrayList<>();
        for (Movimentacao movimentacao : movimentacoes){
            if(movimentacao.isGasto()){
                gastos.add(movimentacao);
            }
        }
        return gastos;

    }

    public ArrayList<Movimentacao> getRecebimentos() {
        ArrayList<Movimentacao> recebimentos = new ArrayList<>();
        for(Movimentacao movimentacao : movimentacoes){
            if (!movimentacao.isGasto()){
                recebimentos.add(movimentacao);
            }
        }
        return recebimentos;

    }

    public Pessoa(String nome, Casa casa){
        this.nome = nome;
        Random random = new Random();
        id = random.nextInt( 100000);
        casa.addMorador(this);
    }




    public void showPessoa() {
        Log.d("mostrar", "nome: "+nome);
        Log.d("mostrar", "ID: "+id);
        showGastos();
        showRecebimentos();
    }

    public void showGastos(){
        ArrayList<Movimentacao> gastos = getGastos();
        for (Movimentacao gasto : gastos){
            gasto.showMovimentacao();
        }
    }

    public void showRecebimentos(){
        ArrayList<Movimentacao> recebimentos = getRecebimentos();
        for (Movimentacao recebimento : recebimentos){
            recebimento.showMovimentacao();
        }
    }

    public Double getGastosTotais(){
        double soma = 0;
        ArrayList<Movimentacao> gastos = getGastos();
        for (Movimentacao gasto : gastos){
            soma += gasto.valor;
        }
        return soma;
    }

    public Perfil getPerfil(){ return perfil; }


}
