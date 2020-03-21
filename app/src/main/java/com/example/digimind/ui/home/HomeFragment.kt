package com.example.digimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.digimind.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.task_board.view.*

class HomeFragment : Fragment() {
    private var adaptador: AdaptadorTareas? = null
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        if (first){
            fillTask()
            first = false
        }

        adaptador = AdaptadorTareas(root.context,tasks)
        root.gridview.adapter = adaptador

        return root
    }

    fun fillTask(){
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"),"17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday","Sunday"),"17:45"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"),"17:30"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"),"17:30"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"),"17:30"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"),"17:30"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"),"17:30"))
    }

    private class AdaptadorTareas:BaseAdapter{
        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, tasks:ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var tasks = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_board,null)

            vista.tv_title.setText(tasks.title)
            vista.tv_time.setText(tasks.time)
            vista.tv_days.setText(tasks.days.toString())

            return vista
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return tasks.size
        }
    }
}
