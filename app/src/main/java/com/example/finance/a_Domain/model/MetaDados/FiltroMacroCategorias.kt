import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class FiltrosMacroCategoria(
    isGasto: Boolean? = null,
    afetaPessoa: Boolean? = null,
    afetaCasa: Boolean? = null,
    isAtivo: Boolean? = null
) {
    var isGasto by mutableStateOf(isGasto)
    var afetaPessoa by mutableStateOf(afetaPessoa)
    var afetaCasa by mutableStateOf(afetaCasa)
    var isAtivo by mutableStateOf(isAtivo)

    fun nextAfetaPessoa() {
        afetaPessoa = when (afetaPessoa) {
            true -> false
            false -> null
            null -> true
        }
    }

    fun nextIsGasto() {
        isGasto = when (isGasto) {
            true -> false
            false -> null
            null -> true
        }
    }

    fun nextAfetaCasa() {
        afetaCasa = when (afetaCasa) {
            true -> false
            false -> null
            null -> true
        }
    }

    fun nextIsAtivo() {
        isAtivo = when (isAtivo) {
            true -> false
            false -> null
            null -> true
        }
    }
}
