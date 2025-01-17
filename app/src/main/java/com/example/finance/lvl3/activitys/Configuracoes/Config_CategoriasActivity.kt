package com.example.finance.lvl3.activitys.Configuracoes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.app.ActivityCompat
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.Data.DataSource.Memory.CacheDataSources
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Data.DataSource.TipoBancoDeDados
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.Categorias.ConfiguracaoCategoriasViewModel
import com.example.finance.a_Domain.model.Categorizacao.MacroCategoria
import com.example.finance.lvl3.telas.Configuracoes.Config_Categorias
import com.example.finance.ui.theme.FinanceTheme

class Config_CategoriasActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val macroCategoriaId: String = intent.getSerializableExtra("macroCategoriaId") as String
        val tipoBancoDeDados: TipoBancoDeDados = intent.getSerializableExtra("typeDB") as TipoBancoDeDados
        var dataSource: InterfaceDataSources = CacheDataSources

        when(tipoBancoDeDados) {
            TipoBancoDeDados.Cache -> {
                dataSource = CacheDataSources
            }

            TipoBancoDeDados.Room -> {
                dataSource = RoomDataSources(this)
            }
        }

        setContent {
            FinanceTheme {
                Surface (color = MaterialTheme.colorScheme.background){
                    Config_Categorias(
                        macroCategoriaId = macroCategoriaId,
                        viewModel = ConfiguracaoCategoriasViewModel(dataSource)
                    )


                }

            }
        }
    }

}

fun abrirConfig_Categorias(
    Context: Context,
    macroCategoriaId: String,
    tipoBancoDeDados: TipoBancoDeDados
) {
    val intent = Intent(Context, Config_CategoriasActivity::class.java).apply {
        putExtra("macroCategoriaId", macroCategoriaId)
        putExtra("typeDB", tipoBancoDeDados)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    ActivityCompat.startActivity(Context, intent, null)


}

