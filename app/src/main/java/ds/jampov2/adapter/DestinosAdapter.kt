package ds.jampov2.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ds.jampov2.ActPergunta
import ds.jampov2.R
import ds.jampov2.model.Destino


class DestinosAdapter(val destinos: ArrayList<Destino>): RecyclerView.Adapter<DestinosAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardDestino: CardView = itemView.findViewById(R.id.cardDestino)
        val txtcomentario: TextView = itemView.findViewById(R.id.txtDestino)
        val txtPergunta_username: TextView = itemView.findViewById(R.id.txtPergunta_username)
        val imgDestino: ImageView = itemView.findViewById(R.id.imgDestino)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            //caso for para mudar, é mudar o "item_destino"
            .inflate(R.layout.itam_destino, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return destinos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val context = holder.cardDestino.context

        val d = destinos[position]
        holder.txtcomentario.text = d.destino
        holder.txtPergunta_username.text = ("${d.preco}")
        Picasso.get()
            .load(d.imgURL)
            .into(holder.imgDestino)

        holder.cardDestino.setOnClickListener {
            Toast.makeText(context, "Pos: $position", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, ActPergunta::class.java)
                .putExtra("id", position))
        }
    }
}