package com.example.finance.Data.DataSource.RoomDB.dataSource

import android.content.Context
import android.util.Log
import com.example.finance.Data.DataSource.RoomDB.AppDatabase
import com.example.finance.a_Domain.Repositorio.ParcelaRepository
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.model.Dados.Parcela
import com.example.finance.a_Domain.model.MetaDados.FormaPagamento
import com.example.finance.a_Domain.model.MetaDados.Periodo
import com.example.finance.a_Domain.model.MetaDados.SelecaoUsuario
import com.example.finance.a_Domain.model.MovimentacaoHolder.DTO.MovimentacaoHolderDTO

class ParcelaDataSourceRoom(context: Context): ParcelaRepository {
    private val database: AppDatabase = AppDatabase.getDatabase(context)
    private val parcelaDao = database.parcelaDao()

    override suspend fun getParcela(id: String): Resultado<Parcela?> {
        val parcela = parcelaDao.get(id)
        if(parcela != null){
            return Resultado.Sucesso(parcela.toParcela())
        }
        return Resultado.Falha(listOf("Parcela não encontrada"))

    }

    override suspend fun getParcelasPorMovimentacao(movimentacaoId: String): Resultado<List<Parcela>?> {
        val parcelas = parcelaDao.getParcelasPorMovimentacao(movimentacaoId)
        if(parcelas != null){
            return Resultado.Sucesso(parcelas.map { it.toParcela() })
        }
        return Resultado.Falha(listOf("Parcelas não encontradas"))
    }

    override suspend fun getParcelas(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        periodo: Periodo?,
        selecaoUsuario: SelecaoUsuario,
        formaPagamento: FormaPagamento?
    ): Resultado<List<Parcela>?> {
        val dataInicial = periodo?.dataInicial?.toTimeStamp()
        val dataFinal = periodo?.dataFinal?.toTimeStamp()
        val tipo = if(selecaoUsuario is SelecaoUsuario.tipoSelecionado) selecaoUsuario.tipo.name else null
        val categoriaId = if(selecaoUsuario is SelecaoUsuario.categoriaSelecionada) selecaoUsuario.categoria.id else null
        val macroCategoriaId = if(selecaoUsuario is SelecaoUsuario.macroCategoriaSelecionada) selecaoUsuario.macroCategoria.id else null
        val formaPagamentoInt = if(formaPagamento is FormaPagamento.debito) 0 else if(formaPagamento is FormaPagamento.credito) 1 else if(formaPagamento is FormaPagamento.parcelado) 2 else null
        val movimentacaoHolderId = movimentacaoHolderDTO.id

        val parcelas = parcelaDao.getParcelas(
            holderId = movimentacaoHolderId,
            dataInicial = dataInicial,
            dataFinal = dataFinal,
            tipo = tipo,
            categoriaId = categoriaId,
            macroCategoriaId = macroCategoriaId,
            formaPagamento = formaPagamentoInt
        )
        if(parcelas != null){
            return Resultado.Sucesso(parcelas.map { it!!.toParcela() })
        }
        return Resultado.Falha(listOf("Parcelas não encontradas"))
    }

    override suspend fun getValor(
        movimentacaoHolderDTO: MovimentacaoHolderDTO,
        periodo: Periodo?,
        selecaoUsuario: SelecaoUsuario,
        formaPagamento: FormaPagamento?
    ): Resultado<Double?> {
        val dataInicial = periodo?.dataInicial?.toTimeStamp()
        val dataFinal = periodo?.dataFinal?.toTimeStamp()
        val tipo = if(selecaoUsuario is SelecaoUsuario.tipoSelecionado) selecaoUsuario.tipo.name else null
        val categoriaId = if(selecaoUsuario is SelecaoUsuario.categoriaSelecionada) selecaoUsuario.categoria.id else null
        val macroCategoriaId = if(selecaoUsuario is SelecaoUsuario.macroCategoriaSelecionada) selecaoUsuario.macroCategoria.id else null
        val formaPagamentoInt = if(formaPagamento is FormaPagamento.debito) 0 else if(formaPagamento is FormaPagamento.credito) 1 else if(formaPagamento is FormaPagamento.parcelado) 2 else null
        val movimentacaoHolderId = movimentacaoHolderDTO.id

        val valores = parcelaDao.getValores(
            holderId = movimentacaoHolderId,
            dataInicial = dataInicial,
            dataFinal = dataFinal,
            tipo = tipo,
            categoriaId = categoriaId,
            macroCategoriaId = macroCategoriaId,
            formaPagamento = formaPagamentoInt
        )
        if(valores != null){
            var soma = 0.0
            for (valor in valores) {
                if (valor != null) {
                    Log.d("ParcelaDataSourceRoom", "Valor a ser somado: $valor")
                    soma += valor
                }
            }
            Log.d("ParcelaDataSourceRoom", "Resultado final: $soma")
            return Resultado.Sucesso(soma)
        }
        return Resultado.Falha(listOf("Parcelas não encontradas"))
    }


}