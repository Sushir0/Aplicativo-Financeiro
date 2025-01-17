package com.example.finance.lvl3.activitys.Configuracoes

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.app.ActivityCompat
import com.example.finance.Data.DataSource.InterfaceDataSources
import com.example.finance.Data.DataSource.Memory.CacheDataSources
import com.example.finance.Data.DataSource.RoomDB.RoomDataSources
import com.example.finance.Data.DataSource.TipoBancoDeDados
import com.example.finance.Presentation.VM.Configuracoes.SelecaoUsuario.MacroCategorias.ConfiguracaoMacroCategoriasViewModel
import com.example.finance.lvl3.telas.Configuracoes.Config_MacroCategorias
import com.example.finance.ui.theme.FinanceTheme

class Config_MacroCategoriasActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val casaId: String = intent.getSerializableExtra("casaId") as String
        val tipoBancoDeDados: TipoBancoDeDados = intent.getSerializableExtra("typeDB") as TipoBancoDeDados
        var dataSources: InterfaceDataSources = CacheDataSources

        when(tipoBancoDeDados) {
            TipoBancoDeDados.Cache -> {
                dataSources = CacheDataSources
            }
            TipoBancoDeDados.Room -> {
                dataSources = RoomDataSources(this)
            }
        }
        setContent {
            FinanceTheme {
                Surface (color = MaterialTheme.colorScheme.background){
                    Config_MacroCategorias(
                        casaId = casaId,
                        viewModel = ConfiguracaoMacroCategoriasViewModel(dataSources)
                    )
                }

            }
        }
    }
}

fun abrirConfig_MacroCategorias(
    context: Context,
    casaId: String,
    tipoBancoDeDados: TipoBancoDeDados
) {
    val intent = Intent(context, Config_MacroCategoriasActivity::class.java).apply {
        putExtra("casaId", casaId)
        putExtra("typeDB", tipoBancoDeDados)
    }
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    ActivityCompat.startActivity(context, intent, null)
}

