package  com.agro.shyariapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Query("SELECT * FROM todoTable")
    fun getAllTodos(): Flow<List<Todo>>

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todoTable WHERE id = :id")
    suspend fun get(id:Int):Todo?

    @Update
    suspend fun update(todo: Todo)
}