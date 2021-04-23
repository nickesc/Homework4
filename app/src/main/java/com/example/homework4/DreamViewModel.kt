package com.example.homework4

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DreamViewModel (private val repository: DreamRepository) : ViewModel(){
    val allDreams : LiveData<List<Dream>> = repository.allDreams.asLiveData()

    fun getDream(id:Int):LiveData<Dream> {
        val result = MutableLiveData<Dream>()
        viewModelScope.launch {
            result.postValue(repository.getDream(id))
        }
        return result
    }


    fun addDream(dream: Dream)=viewModelScope.launch {
        repository.addDream(dream)
    }

    fun deleteDream(id: Int)=viewModelScope.launch {
        repository.deleteDream(id)
    }

    fun updateDream(id:Int, title:String, content:String, reflection:String, emotion:String)=viewModelScope.launch {
        repository.updateDream(id,title,content,reflection,emotion)
    }
}

class DreamViewModelFactory(private val repository: DreamRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DreamViewModel::class.java)) {
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}