package com.example.idolmastercalendar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.idolmastercalendar.network.RemoteClient.createRetrofit
import com.example.idolmastercalendar.network.api.WimiApi
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils
import kotlinx.android.synthetic.main.dialog_review.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewDialog: DialogFragment() {

    companion object {
        fun getInstance(): ReviewDialog = ReviewDialog()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveButton.setOnClickListener {
            saveReview()
        }
    }

    private fun saveReview() {
        val wimiApi = createRetrofit(true).create(WimiApi::class.java)

        val call = wimiApi.saveReview("lani", textView33.text.toString(), "맥도날드", textView32.text.toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                dismiss()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}
        })
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = DynamicUnitUtils.convertDpToPixels(350f)
        params.height = DynamicUnitUtils.convertDpToPixels(350f)
        dialog!!.window!!.attributes = params as android.view.WindowManager.LayoutParams
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}