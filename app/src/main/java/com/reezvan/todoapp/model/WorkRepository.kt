package com.reezvan.todoapp.model

import kotlinx.coroutines.flow.Flow

class WorkRepository(val dao: WorkDao){

    suspend fun addAWork(workModel: WorkModel){
        dao.addWork(workEntity = workModel)
    }

    suspend fun deleteAWork(workModel: WorkModel){
        dao.deleteWork(workModel)
    }

    suspend fun updateAWork(workModel: WorkModel){
        dao.updateWork(workModel)
    }

    fun getAllWorks(): Flow<List<WorkModel>>{
        return  dao.getAllWork()
    }

    fun getAWorkById(id: Long): Flow<WorkModel> {
        return dao.getWorkById(id=id)
    }
}