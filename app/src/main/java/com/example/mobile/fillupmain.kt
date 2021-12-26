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

    private var editTextfullname: EditText? = null
    private var editTextemail: EditText? = null
    private var editTextpassword: EditText? = null
    private var editTextaddress: EditText? = null
    private var editTextsrcode: EditText? = null
    private var editTextcontact: EditText? = null
    private var editTextguardianname: EditText? = null
    private var editTextguardiancontact: EditText? = null

    lateinit var btnsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fillupmain)

        val btnsignup = findViewById<Button>(R.id.fillsubmit)

        btnsignup.setOnClickListener {
            editTextfullname = findViewById<TextView>(R.id.fullname) as EditText?
            editTextemail = findViewById<TextView>(R.id.email) as EditText?
            editTextpassword = findViewById<TextView>(R.id.password) as EditText?
            editTextaddress = findViewById<TextView>(R.id.address) as EditText?
            editTextsrcode = findViewById<TextView>(R.id.srcode) as EditText?
            editTextcontact = findViewById<TextView>(R.id.contact) as EditText?
            editTextguardianname = findViewById<TextView>(R.id.guardianname) as EditText?
            editTextguardiancontact =findViewById<TextView>(R.id.guardiancontact) as EditText?

            val fullname = editTextfullname?.text.toString()
            val email = editTextemail?.text.toString()
            val password = editTextpassword?.text.toString()
            val address = editTextaddress?.text.toString()
            val srcode = editTextsrcode?.text.toString()
            val contact = editTextcontact?.text.toString()
            val guardianname = editTextguardianname?.text.toString()
            val guardiancontact = editTextguardiancontact?.text.toString()
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
                    params["fullname"] = fullname
                    params["email"] = email
                    params["password"] = password
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