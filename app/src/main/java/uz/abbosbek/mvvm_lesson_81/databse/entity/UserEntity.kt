package uz.abbosbek.mvvm_lesson_81.databse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id:Int,
    val login:String,
    val avatar:String
)
