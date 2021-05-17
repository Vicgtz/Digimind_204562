package gutierrez.leal.mydigimind.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView
import gutierrez.leal.mydigimind.R
import java.io.Serializable

class Carrito: BaseAdapter, ListAdapter {
    var recor = ArrayList<Recordatorio>()

    var context: Context? = null

    constructor(context: Context, task: ArrayList<Recordatorio>){
        this.context=context
        this.recor=recor
    }

    override fun getCount(): Int {
        return recor.size
    }

    override fun getItem(position: Int): Any {
        return recor[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var task= recor[position]
        var inflater = LayoutInflater.from(context)
        var view = inflater.inflate(R.layout.recordatorio,null)

        var tv_tilte: TextView = view.findViewById(R.id.tv_title)
        var tv_time: TextView = view.findViewById(R.id.tv_time)
        var tv_days: TextView = view.findViewById(R.id.tv_day)

        tv_tilte.setText(task.title)
        tv_time.setText(task.time)
        tv_days.setText(task.days.toString())

        return view
    }
}