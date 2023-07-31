package uz.abbosbek.mvvm_lesson_81.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.abbosbek.mvvm_lesson_81.databse.AppDatabase
import uz.abbosbek.mvvm_lesson_81.networking.ApiService
import uz.abbosbek.mvvm_lesson_81.utils.NetworkHelper
import java.lang.Exception

class UserViewModelFactory(
    private val appendable: AppDatabase,
    private val apiService: ApiService,
    private val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(appendable, apiService, networkHelper) as T
        }
        return throw Exception("Error")
    }
}