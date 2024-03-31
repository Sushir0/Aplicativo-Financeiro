package com.example.finance.lvl1

import androidx.compose.ui.graphics.Color
import com.example.finance.R

class Perfil (isCasa : Boolean = false){
    var fotoURL = if (!isCasa){
        GeradorDeImagem.obterImagem()
    }else{
        R.drawable.imagem_casa_padrao
    }
       private set

    var corPerfil = GeradorDeCor.obterCor()
        private set
}

object GeradorDeCor {
    private val cores = listOf(
        Color.Red,
        Color.Green,
        Color.Blue,
        Color.Cyan,
        Color.Magenta,
        Color.Yellow,
        Color.Gray
    )
    fun obterCor(): Color {
        return cores.random()
    }
}

object GeradorDeImagem {
    private val imagens = listOf(
        R.drawable.artistico1,
        R.drawable.artistico2,
        R.drawable.artistico3,
        R.drawable.astronauta1,
        R.drawable.cyber1,
        R.drawable.cyber2,
        R.drawable.cyber3,
        R.drawable.cyber4,
        R.drawable.patinho1,
        R.drawable.patinho2,
        R.drawable.patinho3,
        R.drawable.patinho4,
        R.drawable.pixel1,
        R.drawable.pixel2,
        R.drawable.pixel3,
        R.drawable.pixel4,
        R.drawable.ursinho1,
        R.drawable.ursinho2,
    )


    fun obterImagem(): Int {
        return imagens.random()
    }
}