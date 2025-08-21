package com.reezvan.todoapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reezvan.todoapp.WorkGraph
import com.reezvan.todoapp.model.WorkModel
import com.reezvan.todoapp.model.WorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: WorkRepository= WorkGraph.workRepo): ViewModel(){

    lateinit var getAllWork: Flow<List<WorkModel>>

    init {
        viewModelScope.launch {
            getAllWork=repository.getAllWorks()
        }
    }

    fun deleteWork(work: WorkModel){

        viewModelScope.launch {
            repository.deleteAWork(work)
        }
    }

    fun addWork(workName:String,deadline:String,priority: String,description: String,createdTime: String){
        viewModelScope.launch {
            val work= WorkModel(
                workName = workName,
                deadline = deadline,
                priority = priority,
                description = description,
                createdTime = createdTime
            )
            repository.addAWork(workModel = work)
        }
    }

    fun updateWork(work: WorkModel){
        viewModelScope.launch {
            repository.updateAWork(work)
        }
    }

    fun getAWorkById(id: Long): Flow<WorkModel>{
        lateinit var work: Flow<WorkModel>
     viewModelScope.launch {
         work= repository.getAWorkById(id=id)
     }
        return work
    }

}