package uz.abbosbek.mvvm_lesson_81.vm

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T : Any>(val data: T) : Resource<T>()
    class Error<T : Any>(val e: Throwable) : Resource<T>()
}
