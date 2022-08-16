package com.ocean.todoinkotlin.roomDB

import androidx.room.Dao
import androidx.room.Insert
import com.ocean.todoinkotlin.model.TodoModel

@Dao
interface TodoDao {

    @Insert()
    suspend fun insertToDo(todoModel: TodoModel): Long


}
