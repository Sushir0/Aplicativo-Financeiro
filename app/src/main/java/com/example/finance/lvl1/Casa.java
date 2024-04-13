package com.example.finance.lvl1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Casa {
    public String nome;
    protected Integer id;
    public ArrayList<Pessoa> moradores = new ArrayList<>();
    public ArrayList<Movimentacao> movimentacoes = new ArrayList<>();
    private Perfil perfil = new Perfil(true);

    public Casa(String nome){
        this.nome = nome;
        Random random = new Random();
        id = random.nextInt( 100000);
        Login.addCasa(this);
    }

    protected boolean addMorador(Pessoa morador){
        return moradores.add(morador);
    }

    public boolean addMovimentacao(Movimentacao movimentacao){

        return movimentacoes.add(movimentacao);

    }

    private void editMovimentacao(Movimentacao movimentacaoOriginal, Movimentacao movimentacaoModificada){
        Integer index = movimentacoes.indexOf(movimentacaoOriginal);
        movimentacoes.set(index, movimentacaoModificada);
    }

    private boolean excluirMovimentacao(Movimentacao movimentacaoASerExcluida){
        return movimentacoes.remove(movimentacaoASerExcluida);
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
        ArrayList<Movimentacao> gastos = getGastos();
        for (Movimentacao gasto : movimentacoes){
            gasto.showMovimentacao();
        }
    }

    public ArrayList<Movimentacao> getGastos() {
        ArrayList<Movimentacao> gastos = new ArrayList<>();
        for (Movimentacao movimentacao : movimentacoes){
            if(movimentacao.categoria.isGasto()){
                gastos.add(movimentacao);
            }
        }
        return gastos;
    }

    public Integer getId(){
        return id;
    }

    public Double getGastosTotais(){
        double soma = 0;
        for (Movimentacao movimentacao : movimentacoes){
            if(movimentacao.isGasto()) {
                soma += movimentacao.valor;
            }
        }
        return soma;
    }

    public Double getRecebimentosTotais(){
        double soma = 0;
        for (Movimentacao movimentacao : movimentacoes){
            if (!movimentacao.isGasto()){
                soma += movimentacao.valor;
            }
        }
        return soma;
    }

    public Double getSaldo(){
        return getRecebimentosTotais() - getGastosTotais();
    }


    public Perfil getPerfil() {
        return perfil;
    }
}