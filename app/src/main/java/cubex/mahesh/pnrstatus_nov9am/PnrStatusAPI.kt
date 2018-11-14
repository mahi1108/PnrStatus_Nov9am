package cubex.mahesh.pnrstatus_nov9am

import cubex.mahesh.pnrstatus_nov9am.beans.PNRStatusBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PnrStatusAPI
{
    @GET("v2/pnr-status/pnr/{pno}/apikey/0rfz4cfpdo/")
    fun getPnrStatus(@Path("pno") pno:String):
            Call<PNRStatusBean>
}