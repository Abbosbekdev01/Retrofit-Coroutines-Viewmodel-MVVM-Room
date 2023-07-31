package uz.abbosbek.mvvm_lesson_81.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import uz.abbosbek.mvvm_lesson_81.databse.AppDatabase
import uz.abbosbek.mvvm_lesson_81.databse.entity.UserEntity
import uz.abbosbek.mvvm_lesson_81.mapper.mapToEntity
import uz.abbosbek.mvvm_lesson_81.networking.ApiService
import uz.abbosbek.mvvm_lesson_81.repository.UserRepository
import uz.abbosbek.mvvm_lesson_81.utils.NetworkHelper

//todo: ViewModel
class UserViewModel(
    appDatabase: AppDatabase,
    apiService: ApiService,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val userRepository = UserRepository(apiService, appDatabase.userDao())
    private val stateFlow = MutableStateFlow<Resource<List<UserEntity>>>(Resource.Loading())

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnection()) {
                userRepository.getUsers()
                    .catch {
                        stateFlow.emit(Resource.Error(it))
                    }.flatMapConcat {
                        val list = ArrayList<UserEntity>()
                        it.forEach {
                            list.add(it.mapToEntity(it))
                        }
                        userRepository.addUsers(list)
                    }.collect {
                        stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                    }
            } else {
                if (userRepository.getUserCount() > 0) {
                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                } else
                    stateFlow.emit(Resource.Error(Throwable("Internet not connection")))
            }
        }
    }

    fun getStateFlow(): StateFlow<Resource<List<UserEntity>>> {
        return stateFlow
    }
}