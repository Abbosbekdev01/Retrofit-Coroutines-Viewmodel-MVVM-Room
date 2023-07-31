package uz.abbosbek.mvvm_lesson_81.repository

import kotlinx.coroutines.flow.flow
import uz.abbosbek.mvvm_lesson_81.databse.dao.UserDao
import uz.abbosbek.mvvm_lesson_81.databse.entity.UserEntity
import uz.abbosbek.mvvm_lesson_81.networking.ApiService

class UserRepository(private val apiService: ApiService, private val userDao: UserDao) {

    fun getUsers() = apiService.getUsers()

    fun addUsers(list: List<UserEntity>) = flow {  emit(userDao.addUsers(list))}

    fun getDatabaseUsers() = userDao.getUsers()

    fun getUserCount() = userDao.getUserCount()
}