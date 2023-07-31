package uz.abbosbek.mvvm_lesson_81.networking

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import uz.abbosbek.mvvm_lesson_81.models.UserData

interface ApiService {

    @GET("users")
    fun getUsers():Flow<List<UserData>>
}