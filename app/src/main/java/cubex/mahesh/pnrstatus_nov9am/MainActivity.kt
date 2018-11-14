package cubex.mahesh.pnrstatus_nov9am

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import cubex.mahesh.pnrstatus_nov9am.beans.PNRStatusBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStatus.setOnClickListener {

            var r = Retrofit.Builder().
                    addConverterFactory(GsonConverterFactory.create()).
                    baseUrl("https://api.railwayapi.com/").
                    build()
            var api = r.create(PnrStatusAPI::class.java)
            var call = api.getPnrStatus(et1.text.toString())
            call.enqueue(object:Callback<PNRStatusBean>{
                override fun onFailure(call: Call<PNRStatusBean>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
                override fun onResponse(call: Call<PNRStatusBean>,
                                        response: Response<PNRStatusBean>) {

                    var bean = response.body()
                    var temp_list = mutableListOf<String>()
                    temp_list.add("Name: ${bean!!.train.name}")
                    temp_list.add("Number: ${bean!!.train.number}")
                    temp_list.add("DOJ: ${bean!!.doj}")
                    temp_list.add("No Of Psgrs: ${bean!!.total_passengers}")
                    temp_list.add("From Station: ${bean!!.from_station.name}")
                    temp_list.add("To Station: ${bean!!.to_station.name}")
                    for(psgr in bean.passengers)
                    {
                    temp_list.add("Cur Status : ${psgr.current_status}")
                    temp_list.add("Book Status : ${psgr.booking_status}")
                    }
                    var adapater = ArrayAdapter<String>(
                        this@MainActivity,
                    android.R.layout.simple_list_item_single_choice,temp_list)
                    lview.adapter = adapater

                }
            })
        }

    }
}
