package com.example.mobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject

class loginwin() : AppCompatActivity() {

    private var editTextEMAIL: EditText? = null
    private var editTextPASSWORD: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginwin)

        editTextEMAIL = findViewById<EditText>(R.id.EMAIL)
        editTextPASSWORD = findViewById<EditText>(R.id.PASSWORD)
        val login = findViewById<Button>(R.id.loginwinbot)
        val EMAIL = findViewById<EditText>(R.id.EMAIL)
        var PASSWORD = findViewById<EditText>(R.id.PASSWORD)


        login.setOnClickListener {
            if (EMAIL.text.isNullOrBlank() && PASSWORD.text.isNullOrBlank()) {
                Toast.makeText(this, "Please fill the required fields!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${EMAIL.text} is logged in!", Toast.LENGTH_SHORT).show()
                val stringRequest =
                    object : StringRequest(
                        Request.Method.POST, EndPoints.URL_LOGIN_USER,
                        Response.Listener<String> { response ->
                            try {
                                val obj = JSONObject(response)
                                Toast.makeText(
                                    applicationContext,
                                    obj.getString("message"),
                                    Toast.LENGTH_LONG
                                ).show()
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
                            params["EMAIL"] = EMAIL.toString()
                            params["PASSWORD"] = PASSWORD.toString()
                            return params
                        }
                    }
                VolleySingleton.instance?.addToRequestQueue(stringRequest)
                /*loginuser()
                startActivity(Intent(this, Export::class.java))*/
            }
        }
    }

    private fun loginuser() {
        var EMAIL = editTextEMAIL?.getText().toString()
        var PASSWORD = editTextPASSWORD?.getText().toString()
            val stringRequest =
                object : StringRequest(
                    Request.Method.POST, EndPoints.URL_LOGIN_USER,
                    Response.Listener<String> { response ->
                        try {
                            val obj = JSONObject(response)
                            Toast.makeText(
                                applicationContext,
                                obj.getString("message"),
                                Toast.LENGTH_LONG
                            ).show()
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
                        params["EMAIL"] = EMAIL
                        params["PASSWORD"] = PASSWORD
                        return params
                    }
                }
            VolleySingleton.instance?.addToRequestQueue(stringRequest)
        }

    }
