package gutierrez.leal.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import gutierrez.leal.mydigimind.R
import gutierrez.leal.mydigimind.ui.home.HomeFragment
import gutierrez.leal.mydigimind.ui.notifications.Recordatorio
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var db: FirebaseFirestore
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

       val btn_time: Button= root.findViewById(R.id.btn_time)
        btn_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener= TimePickerDialog.OnTimeSetListener{TimePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text= SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE), true).show()


        }

        val btn_save = root.findViewById(R.id.btn_save) as Button
        val et_titulo = root.findViewById(R.id.et_task) as EditText
        val chMonday = root.findViewById(R.id.checkMonday) as CheckBox
        val chTuesday = root.findViewById(R.id.checkTuesday) as CheckBox
        val chWednesday = root.findViewById(R.id.checkWednesday) as CheckBox
        val chThursday = root.findViewById(R.id.checkThursday) as CheckBox
        val chFriday = root.findViewById(R.id.checkFriday) as CheckBox
        val chSaturday = root.findViewById(R.id.checkSaturday) as CheckBox
        val chSunday = root.findViewById(R.id.checkSunday) as CheckBox

        btn_save.setOnClickListener {
            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()

            if(chMonday.isChecked){
                days.add("Monday")
            }
            if(chTuesday.isChecked){
                days.add("Tuesday")
            }
            if(chWednesday.isChecked){
                days.add("Wednesday")
            }
            if(chThursday.isChecked){
                days.add("Thursday")
            }
            if(chFriday.isChecked){
                days.add("Friday")
            }
            if(chSaturday.isChecked){
                days.add("Saturday")
            }
            if(chSunday.isChecked){
                days.add("Sunday")
            }



            var task = Recordatorio(title,days,time)

            sendDataFirebase(title,time,days)
            HomeFragment.tasks.add(task)

            Toast.makeText(root.context,"New task added",Toast.LENGTH_SHORT).show()

        }

        return root
    }

    private fun sendDataFirebase(title:String,time:String,days: ArrayList<String>){
        db = FirebaseFirestore.getInstance();
        var data = hashMapOf(
            "actividad" to title,
            "tiempo" to time,
            "lu" to false,
            "ma" to false,
            "mi" to false,
            "ju" to false,
            "vi" to false,
            "sa" to false,
            "do" to false
        )

        days.forEach {
            if(it == "Monday"){
                data["lu"]=true
            }
            if(it == "Tuesday"){
                data["ma"]=true
            }
            if(it == "Wednesday"){
                data["mi"]=true
            }
            if(it == "Thursday"){
                data["ju"]=true
            }
            if(it == "Friday"){
                data["vi"]=true
            }
            if(it == "Saturday"){
                data["sa"]=true
            }
            if(it == "Sunday"){
                data["do"]=true
            }
        }

        db.collection("actividades")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d( "${documentReference.id}","Actividad Agregado!")
            }
            .addOnFailureListener { e ->
                Log.w( "Error adding document", e)
            }

    }
}