package uz.abbosbek.mvvm_lesson_81.mapper

import uz.abbosbek.mvvm_lesson_81.databse.entity.UserEntity
import uz.abbosbek.mvvm_lesson_81.models.UserData

fun UserData.mapToEntity(userData: UserData): UserEntity {
    return UserEntity(userData.id ?: 0, userData.login ?: "", userData.avatar_url ?: "")
}