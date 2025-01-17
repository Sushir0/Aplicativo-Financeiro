package com.example.finance.lvl3.activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.example.finance.lvl3.telas.Configuracoes.Configuracao
import com.example.finance.ui.theme.FinanceTheme

class ConfiguracaoActivity : ComponentActivity() {
    val configuracao = Configuracao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    configuracao.content()
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ConfiguracaoPreview() {
    val configuracao = Configuracao()
    FinanceTheme {
        configuracao.content()
    }
}

fun abrirConfiguracao(mContext: Context) {
    val intent = Intent(mContext, ConfiguracaoActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    ActivityCompat.startActivity(mContext, intent, null)
}