package ds.jampov2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ds.jampov2.databinding.ActivityActPerguntaBinding

class ActPergunta : AppCompatActivity() {

    private val binding by lazy {
        ActivityActPerguntaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var extras = intent.extras
        if (extras != null) {
            val id = extras.getInt("id")
            val d = Config.destinos[id]
            binding.txtTestePau.text = d.destino
        }
    }
}