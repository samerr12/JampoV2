package ds.jampov2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ds.jampov2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }

    fun doLogin(view: View) {

        val queue = Volley.newRequestQueue(this)
        val url = "https://esan-tesp-ds-paw.web.ua.pt/tesp-ds-g36/api/index.php"

        // POST request + get string response from the provided URL.
        val postRequest = object : StringRequest(Method.POST, url,
            { response ->
                var msg = response
                if (response == "OK") {
                    // do login...
                    if (binding.checkBox.isChecked) {
                        // guardar em SharedPreference o login
                        getSharedPreferences("pmLogin", Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean("login",true)
                            .apply()
                        msg += " + Save"
                    }
                    startActivity(Intent(this, ActUser::class.java))
                    finish()
                }
                Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params: MutableMap<String,String> = HashMap()
                params["email"] = binding.txtEmailLogin.text.toString()
                params["password"] = binding.editTextTextPassword.text.toString()
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(postRequest)
    }
}