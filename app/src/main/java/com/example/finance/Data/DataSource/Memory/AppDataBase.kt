package com.example.finance.Data.DataSource.Memory

import com.example.finance.Data.DataSource.Memory.dao.CategoriaDao
import com.example.finance.Data.DataSource.Memory.entity.CasaEntity
import com.example.finance.Data.DataSource.Memory.entity.MacroCategoriaEntity
import com.example.finance.Data.DataSource.Memory.entity.CategoriaEntity


object cacheDatabase {
    val categoriaDao = CategoriaDao()


    val cacheCasas: MutableList<CasaEntity> = ArrayList()
    val cacheMacroCategorias: MutableList<MacroCategoriaEntity> = ArrayList()
    val cacheCategorias: MutableList<CategoriaEntity> = ArrayList()
}