package uz.abbosbek.mvvm_lesson_81

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.abbosbek.mvvm_lesson_81.databinding.ActivityMainBinding
import uz.abbosbek.mvvm_lesson_81.databse.AppDatabase
import uz.abbosbek.mvvm_lesson_81.networking.ApiClient
import uz.abbosbek.mvvm_lesson_81.utils.NetworkHelper
import uz.abbosbek.mvvm_lesson_81.vm.Resource
import uz.abbosbek.mvvm_lesson_81.vm.UserViewModel
import uz.abbosbek.mvvm_lesson_81.vm.UserViewModelFactory
import kotlin.coroutines.CoroutineContext

//todo: View
class MainActivity : AppCompatActivity(), CoroutineScope {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: UserViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(
            this,
            UserViewModelFactory(AppDatabase.getInstance(this), ApiClient.apiService, NetworkHelper(this))
        )[UserViewModel::class.java]

        launch {
            viewModel.getStateFlow()
                .collect {
                    when (it) {
                        is Resource.Error -> {
                            Toast.makeText(this@MainActivity, it.e.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            Log.d(TAG, "onCreate: ${it.data}")
                        }
                    }
                }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}