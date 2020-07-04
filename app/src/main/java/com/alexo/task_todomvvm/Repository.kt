package com.alexo.task_todomvvm

import androidx.lifecycle.LiveData

/*
@Dao
interface Repository {
    @Insert
    fun insert(task: Task)

    @Query("SELECT * from Task order by done desc")
    fun getAll(): LiveData<List<Task>>

    @Delete
    fun delete(task: Task)

    @Query("DELETE FROM Task")
    fun deleteAll(task: Task)

    @Update
    fun update(task: Task)
}*/

interface Repository {
    fun insert(task: Task)

    fun getAll(): LiveData<List<Task>>

    fun delete(task: Task)

    fun deleteAll(task: Task)

    fun update(task: Task)
}
