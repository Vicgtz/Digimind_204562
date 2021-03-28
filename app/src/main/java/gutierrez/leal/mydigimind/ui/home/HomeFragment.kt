package gutierrez.leal.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gutierrez.leal.mydigimind.R
import gutierrez.leal.mydigimind.ui.notifications.Recordatorio

class HomeFragment : Fragment() {


    private var adaptador:AdaptadorTareas? = null
    private lateinit var homeViewModel: HomeViewModel

    companion object{
        var tasks = ArrayList<Recordatorio>()
        var first = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        if(first){
            fillTasks()
            first=false
        }


        adaptador = AdaptadorTareas(root.context,tasks)
        val gridView: GridView = root.findViewById(R.id.gridview)
        gridView.adapter= adaptador

        return root
    }

    fun fillTasks(){
        tasks.add(Recordatorio("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Recordatorio("Practice 2", arrayListOf("Monday", "Sunday"), "17:40"))
        tasks.add(Recordatorio("Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Recordatorio("Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Recordatorio("Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Recordatorio("Practice 6", arrayListOf("Thursday"), "10:40"))
        tasks.add(Recordatorio("Practice 7", arrayListOf("Monday"), "12:00"))
    }

    private class AdaptadorTareas: BaseAdapter{
        var tasks = ArrayList<Recordatorio>()
        var contexto: Context? =null
        constructor(contexto: Context, tasks: ArrayList<Recordatorio>){
            this.contexto= contexto
            this.tasks= tasks
        }

        override fun getCount(): Int {
           return tasks.size
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.recordatorio, null)

            var tv_title: TextView= vista.findViewById(R.id.tv_title)
            var tv_time: TextView= vista.findViewById(R.id.tv_time)
            var tv_days: TextView= vista.findViewById(R.id.tv_day)

            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_days.setText(task.days.toString())

            return vista
        }

    }


}