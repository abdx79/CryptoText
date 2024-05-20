package com.abdx79.cryptotext

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime


class DBTypeConverter{

    @TypeConverter
    fun dateToString(date: LocalDateTime): String {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(string: String): LocalDateTime {
        return LocalDateTime.parse(string)
    }

}


object Constants {
    const val DATABASE_NAME = "app_database"
    const val TBN_CRYPTO_ENTITY = "crypto_entity"
}


@Entity(tableName = Constants.TBN_CRYPTO_ENTITY)
data class CryptoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val rawText: String,
    val cypherText: String,
    val key: String
)

@Dao
interface AppDatabaseProvider{

    @Query("SELECT * FROM ${Constants.TBN_CRYPTO_ENTITY}")
    suspend fun getAllCryptoEntities(): List<CryptoEntity>

    @Insert
    suspend fun addCryptoEntity(cryptoEntity: CryptoEntity)

    @Query("DELETE FROM ${Constants.TBN_CRYPTO_ENTITY} where id = :id")
    suspend fun clearCryptoEntities(id: Int)
}

@Database(entities = [CryptoEntity::class], version = 1)
@TypeConverters(DBTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseProvider(): AppDatabaseProvider
}
