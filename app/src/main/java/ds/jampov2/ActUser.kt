package ds.jampov2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ds.jampov2.adapter.DestinosAdapter
import ds.jampov2.databinding.ActivityActUserBinding
import ds.jampov2.model.Destino
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ActUser : AppCompatActivity() {

    private val binding by lazy {
        ActivityActUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerViewDestinos.layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this)
        var url = "https://esan-tesp-ds-paw.web.ua.pt/tesp-ds-g36/api/user/list.php"

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                try {
                    //em caso de ser um object, trocar para um JSONobject
                    val jsonObbject = JSONObject(response)
                    val jsonArray = jsonObbject.optJSONArray("destinos")
                    for (i in 0 until jsonArray.length()) {
                        val item = jsonArray.getJSONObject(i)
                        val d = Destino()
                        d.destino = item.getString("username")
                        d.preco = item.getString("email")
                        d.imgURL = item.getString("img")
                        Config.destinos.add( d )
                    }
                    binding.recyclerViewDestinos.adapter =
                        DestinosAdapter(Config.destinos)

                } catch (e: JSONException) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                }
            },
            {
                error: VolleyError ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            })
        queue.add(request)
    }
}