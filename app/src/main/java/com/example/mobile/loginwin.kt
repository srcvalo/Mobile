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

    private var editTextemail: EditText? = null
    private var editTextpassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginwin)

        editTextemail = findViewById<EditText>(R.id.email)
        editTextpassword = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.loginwinbot)
        val email = findViewById<EditText>(R.id.email)
        var password = findViewById<EditText>(R.id.password)


        login.setOnClickListener {
            if (email.text.isNullOrBlank() && password.text.isNullOrBlank()) {
                Toast.makeText(this, "Please fill the required fields!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${email.text} is logged in!", Toast.LENGTH_SHORT).show()
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
                            params["email"] = email.toString()
                            params["password"] = password.toString()
                            return params
                        }
                    }
                VolleySingleton.instance?.addToRequestQueue(stringRequest)
                /*loginuser()
                startActivity(Intent(this, Export::class.java))*/
            }
        }
    }

    /*private fun loginuser() {
        var email = editTextemail?.getText().toString()
        var password = editTextpassword?.getText().toString()
        var email = editTextemail?.getText().toString()
        var password = editTextpassword?.getText().toString()
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
                        params["email"] = email
                        params["password"] = password
                        return params
                    }
                }
            VolleySingleton.instance?.addToRequestQueue(stringRequest)
        }

    }*/

}