package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject


class fillupmain : AppCompatActivity() {

    private var editTextFULLNAME: EditText? = null
    private var editTextEMAIL: EditText? = null
    private var editTextPASSWORD: EditText? = null
    private var editTextaddress: EditText? = null
    private var editTextsrcode: EditText? = null
    private var editTextcontact: EditText? = null
    private var editTextguardianname: EditText? = null
    private var editTextguardiancontact: EditText? = null

    lateinit var btnsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fillupmain)

        val btnsignup = findViewById<Button>(R.id.btnsignup)

        btnsignup.setOnClickListener {
            editTextFULLNAME = findViewById<TextView>(R.id.FULLNAME) as EditText?
            editTextEMAIL = findViewById<TextView>(R.id.EMAIL) as EditText?
            editTextPASSWORD = findViewById<TextView>(R.id.PASSWORD) as EditText?


            val FULLNAME = editTextFULLNAME?.text.toString()
            val EMAIL = editTextEMAIL?.text.toString()
            val PASSWORD = editTextPASSWORD?.text.toString()

           /* Toast.makeText(
                applicationContext,
                "$fullname\n$email\n$password",
                Toast.LENGTH_LONG
            ).show()*/

          val stringRequest = object : StringRequest(
                Request.Method.POST, EndPoints.URL_ADD_USER,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError ->
                    Toast.makeText(
                        applicationContext,
                        volleyError.message,
                        Toast.LENGTH_LONG
                    ).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["FULLNAME"] = FULLNAME
                    params["EMAIL"] = EMAIL
                    params["PASSWORD"] = PASSWORD
                    return params
                }
            }
            VolleySingleton.instance?.addToRequestQueue(stringRequest)

        }
        btnsignup.setOnClickListener {
            startActivity(Intent(this, loginwin::class.java))
        }
    }
}