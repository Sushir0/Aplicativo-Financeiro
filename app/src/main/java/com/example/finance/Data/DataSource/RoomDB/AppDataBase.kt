package com.example.finance.Data.DataSource.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finance.Data.DataSource.RoomDB.dao.CasaDao
import com.example.finance.Data.DataSource.RoomDB.dao.CategoriaDao
import com.example.finance.Data.DataSource.RoomDB.dao.MacroCategoriaDao
import com.example.finance.Data.DataSource.RoomDB.dao.MovimentacaoDao
import com.example.finance.Data.DataSource.RoomDB.dao.ParcelaDao
import com.example.finance.Data.DataSource.RoomDB.dao.PessoaDao
import com.example.finance.Data.DataSource.RoomDB.entity.CasaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.CategoriaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.MacroCategoriaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.MovimentacaoEntity
import com.example.finance.Data.DataSource.RoomDB.entity.ParcelaEntity
import com.example.finance.Data.DataSource.RoomDB.entity.ParcelaEntityGet
import com.example.finance.Data.DataSource.RoomDB.entity.PessoaEntity

@Database(entities = [
    MovimentacaoEntity::class,
    ParcelaEntity::class,
    CategoriaEntity::class,
    CasaEntity::class,
    MacroCategoriaEntity::class,
    PessoaEntity::class],
    views = [
        ParcelaEntityGet::class
            ],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movimentacaoDao(): MovimentacaoDao
    abstract fun parcelaDao(): ParcelaDao
    abstract fun categoriaDao(): CategoriaDao
    abstract fun macroCategoriaDao(): MacroCategoriaDao
    abstract fun casaDao(): CasaDao
    abstract fun pessoaDao(): PessoaDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "finance_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}