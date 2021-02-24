package com.gsoft.argentina.probandolivedataroommvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import com.gsoft.argentina.probandolivedataroommvvm.datasource.EntidadDao

@Database(entities = [Entidad::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun entidadDao(): EntidadDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "entidades"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }

    }

}
