package ds.jampov2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ds.jampov2.databinding.ActivitySpashScreenBinding

class SpashScreen : AppCompatActivity() {

    private val binding by lazy {
        ActivitySpashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val login = getSharedPreferences("pmLogin", Context.MODE_PRIVATE)
                .getBoolean("login", false)
            if (login) {
                startActivity(Intent(this,ActUser::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        },3000)
    }
}