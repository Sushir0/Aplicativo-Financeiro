package com.example.finance.lvl1;

import java.util.ArrayList;

public class Login {
    private String email;
    private String senha;
    public Pessoa pessoa;
    private static Pessoa usuarioLogado;
    private static Casa casaLogada;
    private static ArrayList<Login> listaDeLogins = new ArrayList<>();
    private static ArrayList<Casa> listaDeCasas = new ArrayList<>();

    public static Casa Cadastro (String nome, String email, String senha,  Integer idCasa){
        Login login = new Login();
        login.email = email;
        login.senha = senha;
        login.pessoa = new Pessoa(nome, buscarCasaPorID(idCasa));
        listaDeLogins.add(login);
        usuarioLogado = login.pessoa;
        casaLogada = buscarCasaPorPessoa(usuarioLogado);
        return buscarCasaPorPessoa(login.pessoa);
    }

    public static Casa Logar(String email, String senha){
        if (!verificarCredenciais(email, senha)){
            return null;
        }
        Login loginEncontrado = buscarPorCredenciais(email, senha);
        usuarioLogado = loginEncontrado.pessoa;
        casaLogada = buscarCasaPorPessoa(usuarioLogado);
        return buscarCasaPorPessoa(loginEncontrado.pessoa);
    }
    public static boolean verificarCredenciais(String email, String senha){
        for (Login login : listaDeLogins){
            if(login.email.equals(email) && login.senha.equals(senha)){
                return true;
            }
        }
        return false;
    }
    private static Login buscarPorCredenciais(String email, String senha){
        for(Login login : listaDeLogins){
            if(login.email.equals(email) && login.senha.equals(senha)){
                return login;
            }
        }
        return null;
    }

    private static Casa buscarCasaPorPessoa(Pessoa pessoa){
        for (Casa casa : listaDeCasas){
            for (Pessoa morador : casa.getMoradores()){
                if (morador.equals(pessoa)){
                    return casa;
                }
            }
        }
        return null;
    }
    private static Casa buscarCasaPorID(Integer idCasa){
        for(Casa casa : listaDeCasas){
            if(idCasa.equals(casa.getId())){
                return casa;
            }
        }
        return null;
    }

    public static boolean isAlguemLogado() {
        return usuarioLogado != null;
    }

    public static Casa getCasaLogada(){return casaLogada;}
    public static Pessoa getUsuarioLogado(){return usuarioLogado;}
    protected static boolean addCasa (Casa casa){
        return listaDeCasas.add(casa);
    }
}
