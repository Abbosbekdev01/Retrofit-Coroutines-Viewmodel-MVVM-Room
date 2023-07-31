package uz.abbosbek.mvvm_lesson_81.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.abbosbek.mvvm_lesson_81.databse.entity.UserEntity


@Dao
interface UserDao {
    @Insert
    fun addUser(userEntity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(list: List<UserEntity>)

    @Query("select * from  userentity")
    fun getUsers(): List<UserEntity>

    @Query("select count(*) from userentity")
    fun getUserCount(): Int
}