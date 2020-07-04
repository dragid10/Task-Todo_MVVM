package com.alexo.task_todomvvm

/*
@Entity(tableName = "Task")
data class Task(@PrimaryKey(autoGenerate = true) val id : Int = 0,
                @ColumnInfo(name = "title") val title: String,
                @ColumnInfo(name = "details") val details : String,
                @ColumnInfo(name = "done") val done : Boolean)*/

data class Task(val id : Int? = 0, val details : String, var isCompleted: Boolean = false, var isDeleted : Boolean = false) {
    override fun toString(): String {
        return """
            
            ID: $id
            Completed: $isCompleted
            Deleted: $isDeleted
            Details: $details
            
        """.trimIndent()
    }
}
