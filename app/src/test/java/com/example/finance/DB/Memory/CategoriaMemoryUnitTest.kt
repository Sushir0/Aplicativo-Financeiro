package com.example.finance.DB.Memory

import com.example.finance.Data.DataSource.Memory.cacheDatabase
import com.example.finance.Data.DataSource.Memory.dataSource.CategoriaDataSourceMemory
import com.example.finance.Data.DataSource.Memory.entity.CategoriaEntity
import com.example.finance.Data.DataSource.Memory.entity.MacroCategoriaEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoriaMemoryUnitTest {
    val categoriaDao = cacheDatabase.categoriaDao

    val macroCategoriaMock = MacroCategoriaEntity(
        id = "macroCategoriaId",
        nome = "Macro Categoria 1",
        afetaCasa = true,
        afetaPessoa = true,
        isGasto = true,
        casaId = "casaId"
    )

    @Before
    fun setup() {
        cacheDatabase.cacheCategorias.clear()
        cacheDatabase.cacheMacroCategorias.clear()
    }

    @Test
    fun `save deve adicionar uma categoria ao cache`() {
        //Arrange
        val categoriaEntity = CategoriaEntity(
            id = "1",
            nome = "Categoria 1",
            isActivate = true,
            macroCategoriaId = "macroCategoriaId"
        )

        //Act
        categoriaDao.save(categoriaEntity)

        //Assert
        Assert.assertEquals(cacheDatabase.cacheCategorias.size, 1)
        Assert.assertEquals(cacheDatabase.cacheCategorias[0], categoriaEntity)
    }

    @Test
    fun `get deve retornar uma categoria pelo ID`() {
        //Arrange
        val id = "1"
        val categoriaEntity = CategoriaEntity(
            id = id,
            nome = "Categoria 1",
            isActivate = true,
            macroCategoriaId = "macroCategoriaId"
        )
        categoriaDao.save(categoriaEntity)

        //Act
        val categoria = categoriaDao.get(id)

        //Assert
        Assert.assertEquals(categoria, categoriaEntity)
    }

    @Test
    fun `delete deve remover uma categoria do cache pelo ID`() {
        //Arrange
        val idToDelete = "1"
        val categoriaEntity1 = CategoriaEntity(
            id = idToDelete,
            nome = "Categoria 1",
            isActivate = true,
            macroCategoriaId = "macroCategoriaId"
        )
        val categoriaEntity2 = CategoriaEntity(
            id = "2",
            nome = "Categoria 2",
            isActivate = false,
            macroCategoriaId = "macroCategoriaId"
        )
        categoriaDao.save(categoriaEntity1)
        categoriaDao.save(categoriaEntity2)

        //Act
        categoriaDao.delete(idToDelete)

        //Assert
        Assert.assertEquals(cacheDatabase.cacheCategorias.size, 1)
        Assert.assertFalse(cacheDatabase.cacheCategorias.contains(categoriaEntity1))
        Assert.assertTrue(cacheDatabase.cacheCategorias.contains(categoriaEntity2))
    }

    @Test
    fun `editarNome deve atualizar o nome de uma categoria`() {
        //Arrange
        val idToEdit = "1"
        val novoNome = "Novo Nome da Categoria"
        val categoriaEntity = CategoriaEntity(
            id = idToEdit,
            nome = "Categoria Antiga",
            isActivate = true,
            macroCategoriaId = "macroCategoriaId"
        )
        categoriaDao.save(categoriaEntity)

        //Act
        categoriaDao.editarNome(idToEdit, novoNome)

        //Assert
        val categoriaAtualizada = categoriaDao.get(idToEdit)
        Assert.assertEquals(novoNome, categoriaAtualizada?.nome)
    }

    @Test
    fun `toogleAtivo deve inverter o status ativo de uma categoria`() {
        //Arrange
        val idToToggle = "1"
        val categoriaEntity = CategoriaEntity(
            id = idToToggle,
            nome = "Categoria 1",
            isActivate = true,
            macroCategoriaId = "macroCategoriaId"
        )
        categoriaDao.save(categoriaEntity)

        //Act
        categoriaDao.toogleAtivo(idToToggle)

        //Assert
        val categoriaAtualizada = categoriaDao.get(idToToggle)
        Assert.assertFalse(categoriaAtualizada?.isActivate ?: true)

        //Act (toggle novamente para testar a inversão)
        categoriaDao.toogleAtivo(idToToggle)

        //Assert
        val categoriaAtualizadaNovamente = categoriaDao.get(idToToggle)
        Assert.assertTrue(categoriaAtualizadaNovamente?.isActivate ?: false)
    }

    @Test
    fun `getCategoriasFromMacro deve retornar categorias associadas a uma macro categoria`() {
        //Arrange
        val macroCategoriaId = "macro1"
        val categoriaEntity1 = CategoriaEntity(
            id = "1",
            nome = "Categoria 1",
            isActivate = true,
            macroCategoriaId = macroCategoriaId
        )
        val categoriaEntity2 = CategoriaEntity(
            id = "2",
            nome = "Categoria 2",
            isActivate = false,
            macroCategoriaId = macroCategoriaId
        )
        val categoriaEntity3 = CategoriaEntity(
            id = "3",
            nome = "Categoria 3",
            isActivate = true,
            macroCategoriaId = "macro2"
        )
        categoriaDao.save(categoriaEntity1)
        categoriaDao.save(categoriaEntity2)
        categoriaDao.save(categoriaEntity3)

        //Act
        val categorias = categoriaDao.getCategoriasFromMacro(macroCategoriaId)

        //Assert
        Assert.assertEquals(2, categorias.size)
        Assert.assertTrue(categorias.contains(categoriaEntity1))
        Assert.assertTrue(categorias.contains(categoriaEntity2))
        Assert.assertFalse(categorias.contains(categoriaEntity3))
    }

    @Test
    fun `getFiltradas deve retornar categorias filtradas por critérios`() {
        //Arrange
        cacheDatabase.cacheMacroCategorias.add(
            MacroCategoriaEntity(id = "macro1", nome = "Macro 1", afetaCasa = true, afetaPessoa = false, isGasto = true, casaId = "casa1")
        )
        cacheDatabase.cacheMacroCategorias.add(
            MacroCategoriaEntity(id = "macro2", nome = "Macro 2", afetaCasa = false, afetaPessoa = true, isGasto = false, casaId = "casa1")
        )
        cacheDatabase.cacheMacroCategorias.add(
            MacroCategoriaEntity(id = "macro3", nome = "Macro 3", afetaCasa = true, afetaPessoa = true, isGasto = true, casaId = "casa2")
        )

        categoriaDao.save(CategoriaEntity(id = "cat1", nome = "Cat 1", isActivate = true, macroCategoriaId = "macro1"))
        categoriaDao.save(CategoriaEntity(id = "cat2", nome = "Cat 2", isActivate = false, macroCategoriaId = "macro1"))
        categoriaDao.save(CategoriaEntity(id = "cat3", nome = "Cat 3", isActivate = true, macroCategoriaId = "macro2"))
        categoriaDao.save(CategoriaEntity(id = "cat4", nome = "Cat 4", isActivate = false, macroCategoriaId = "macro3"))

        //Act
        val filtradas1 = categoriaDao.getFiltradas("casa1", true, null, null)
        val filtradas2 = categoriaDao.getFiltradas("casa1", null, false, null)
        val filtradas3 = categoriaDao.getFiltradas("casa1", null, null, true)
        val filtradas4 = categoriaDao.getFiltradas("casa2", true, true, true)

        //Assert
        Assert.assertEquals(2, filtradas1.size)
        Assert.assertTrue(filtradas1.any { it.id == "cat1" })
        Assert.assertTrue(filtradas1.any { it.id == "cat2" })

        Assert.assertEquals(2, filtradas2.size)
        Assert.assertTrue(filtradas2.any { it.id == "cat1" })
        Assert.assertTrue(filtradas2.any { it.id == "cat2" })

        Assert.assertEquals(2, filtradas3.size)
        Assert.assertTrue(filtradas3.any { it.id == "cat1" })
        Assert.assertTrue(filtradas3.any { it.id == "cat2" })

        Assert.assertEquals(1, filtradas4.size)
        Assert.assertTrue(filtradas4.any { it.id == "cat4" })
    }
}