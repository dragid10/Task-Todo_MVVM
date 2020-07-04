package com.alexo.task_todomvvm

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val listener: (Task) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = TaskAdapter::class.java.canonicalName
    var taskList: List<Task> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_task, parent, false)
        return TaskViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as TaskViewHolder
        val currTask = taskList[position]
        holder.bind(currTask, listener)
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val TAG = TaskViewHolder::class.java.canonicalName
        val textviewTask: TextView = itemView.findViewById(R.id.textview_task)
        val imagebuttonDeleteTask: ImageButton =
            itemView.findViewById(R.id.imageButton_delete_task)

        fun bind(task: Task, listener: (Task) -> Unit) {

            textviewTask.text = task.details
            textviewTask.setOnClickListener {
                val isCompleted = (textviewTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG) > 0
                Log.d(TAG, "Value of checked flag is: $isCompleted")
                if (isCompleted) {
//                    Uncross the item if its currently crossed through
                    textviewTask.paintFlags = textviewTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    task.isCompleted = false
                } else {
//                    Cross the item if its not currently crossed
                    textviewTask.paintFlags = textviewTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    task.isCompleted = true
                }
            }

            imagebuttonDeleteTask.setOnClickListener {
                listener(task)
            }
        }
    }

}