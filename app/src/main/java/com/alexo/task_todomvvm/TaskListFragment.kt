package com.alexo.task_todomvvm

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListFragment : Fragment() {
    private val TAG = TaskListFragment::class.java.canonicalName

    companion object {
        @JvmStatic
        fun newInstance() = TaskListFragment()
    }


    lateinit var viewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var edittextTask: EditText
    private lateinit var buttonAddTask: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment()).get(TaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val parentView = inflater.inflate(R.layout.fragment_task_list, container, false)
        // Inflate the layout for this fragment
        edittextTask = parentView.findViewById(R.id.editText_task_detail)
        buttonAddTask = parentView.findViewById(R.id.imageButton_add_task)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()


//        Initialize recycler adapter
        initRecyclerview(view)

//        Initialize listeners
        initListeners()
    }

    private fun initObservers() {
        viewModel.taskList.observe(viewLifecycleOwner, Observer { tasks ->
            taskAdapter.taskList = tasks.filter { !it.isDeleted }
        })
    }

    private fun initRecyclerview(view: View) {
        taskAdapter = TaskAdapter{viewModel.deleteTask(it)}
        val taskListLayout: RecyclerView = view.findViewById(R.id.recyclerview_task_list)
        val linearLayoutManager = LinearLayoutManager(context)
        taskListLayout.layoutManager = linearLayoutManager
        taskListLayout.adapter = taskAdapter
    }

    private fun initListeners() {
        buttonAddTask.setOnClickListener { button ->
            enterTask()
        }

        edittextTask.setOnEditorActionListener { view: View?, keyCode: Int, event: KeyEvent? ->
            if (keyCode == EditorInfo.IME_ACTION_GO) {
                enterTask()
                true
            }
            false
        }
    }

    private fun enterTask() {
        val task = edittextTask.text.toString()
        viewModel.addTask(task)
        edittextTask.text.clear()
        edittextTask.clearFocus()
//        Log.d(TAG, "CLICKED BUTTON, DO STUFF")
    }
}