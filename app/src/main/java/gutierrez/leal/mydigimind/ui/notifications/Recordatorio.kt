package gutierrez.leal.mydigimind.ui.notifications

import java.io.Serializable

data class Recordatorio(var title: String,
                        var days: ArrayList<String>,
                        var time: String) : Serializable
