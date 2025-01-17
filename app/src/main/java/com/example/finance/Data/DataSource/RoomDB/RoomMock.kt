package com.example.finance.Data.DataSource.RoomDB

import android.content.Context
import com.example.finance.Data.DataSource.RoomDB.dao.CasaDao
import com.example.finance.Data.DataSource.RoomDB.entity.CasaEntity
import com.example.finance.a_Domain.Resultado
import com.example.finance.a_Domain.VariaveisDeAmbiente.VariaveisDeAmbiente
import com.example.finance.b_useCase.CasaService
import com.example.finance.b_useCase.Categoria.CategoriaService
import com.example.finance.b_useCase.MacroCategoria.MacroCategoriaService

suspend fun RoomMock(context: Context){
    val database: AppDatabase = AppDatabase.getDatabase(context)
    val casaDao: CasaDao = database.casaDao()
    val dataSources = RoomDataSources(context)
    criarCasa(casaDao)
    criarMembros(CasaService(dataSources.casaDataSource))
    criarTodasAsMacroCategoriasComCategorias(
        MacroCategoriaService(dataSources.macroCategoriaDataSource),
        CategoriaService(dataSources.categoriaDataSource)
    )
}

private suspend fun criarCasa(casaDao: CasaDao){
    val casaEntity = CasaEntity(
        id = VariaveisDeAmbiente.casaId,
        nome = "Casa",
        cor = "",
        foto = ""
    )
    casaDao.save(casaEntity)


}


private suspend fun criarMembros(casaService: CasaService){
    val ciro = casaService.criarMembro(VariaveisDeAmbiente.casaId, "Ciro")
    val juliana = casaService.criarMembro(VariaveisDeAmbiente.casaId, "Juliana")
}

private suspend fun criarTodasAsMacroCategoriasComCategorias(
    macroCategoriaService: MacroCategoriaService,
    categoriaService: CategoriaService
) {
    // Contas Fixas Casa
    val resultadoContasFixasCasa = macroCategoriaService.criarMacroCategoria(
        nome = "Contas Fixas Casa",
        afetaCasa = true,
        afetaPessoa = false,
        isGasto = true,
        idCasa = VariaveisDeAmbiente.casaId
    )
    if (resultadoContasFixasCasa is Resultado.Sucesso) {
        listOf("Conta de água", "Conta de luz", "Condomínio", "Internet").forEach { nomeCategoria ->
            categoriaService.criarCategoria(
                nome = nomeCategoria,
                macroCategoriaId = resultadoContasFixasCasa.data
            )
        }
    }

    // Saúde
    val resultadoSaude = macroCategoriaService.criarMacroCategoria(
        nome = "Saúde",
        afetaCasa = true,
        afetaPessoa = false,
        isGasto = true,
        idCasa = VariaveisDeAmbiente.casaId
    )
    if (resultadoSaude is Resultado.Sucesso) {
        listOf("Consultas médicas", "Medicamentos", "Exames", "Plano de saúde").forEach { nomeCategoria ->
            categoriaService.criarCategoria(
                nome = nomeCategoria,
                macroCategoriaId = resultadoSaude.data
            )
        }
    }

    // Lazer
    val resultadoLazer = macroCategoriaService.criarMacroCategoria(
        nome = "Lazer",
        afetaCasa = true,
        afetaPessoa = true,
        isGasto = true,
        idCasa = VariaveisDeAmbiente.casaId
    )
    if (resultadoLazer is Resultado.Sucesso) {
        listOf("Viagens", "Cinema", "Restaurantes", "Passeios").forEach { nomeCategoria ->
            categoriaService.criarCategoria(
                nome = nomeCategoria,
                macroCategoriaId = resultadoLazer.data
            )
        }
    }

    // Educação
    val resultadoEducacao = macroCategoriaService.criarMacroCategoria(
        nome = "Educação",
        afetaCasa = true,
        afetaPessoa = true,
        isGasto = true,
        idCasa = VariaveisDeAmbiente.casaId
    )
    if (resultadoEducacao is Resultado.Sucesso) {
        listOf("Cursos online", "Materiais escolares", "Livros").forEach { nomeCategoria ->
            categoriaService.criarCategoria(
                nome = nomeCategoria,
                macroCategoriaId = resultadoEducacao.data
            )
        }
    }

    // Recebimentos
    val resultadoRecebimentos = macroCategoriaService.criarMacroCategoria(
        nome = "Recebimentos",
        afetaCasa = false,
        afetaPessoa = true,
        isGasto = false,
        idCasa = VariaveisDeAmbiente.casaId
    )
    if (resultadoRecebimentos is Resultado.Sucesso) {
        listOf("Salário", "Décimo Terceiro", "Extra", "Outros recebimentos").forEach { nomeCategoria ->
            categoriaService.criarCategoria(
                nome = nomeCategoria,
                macroCategoriaId = resultadoRecebimentos.data
            )
        }
    }

    // Transporte
    val resultadoTransporte = macroCategoriaService.criarMacroCategoria(
        nome = "Transporte",
        afetaCasa = true,
        afetaPessoa = true,
        isGasto = true,
        idCasa = VariaveisDeAmbiente.casaId
    )
    if (resultadoTransporte is Resultado.Sucesso) {
        listOf("Combustível", "Manutenção do carro", "Seguro", "Transporte público", "Uber").forEach { nomeCategoria ->
            categoriaService.criarCategoria(
                nome = nomeCategoria,
                macroCategoriaId = resultadoTransporte.data
            )
        }
    }
}
