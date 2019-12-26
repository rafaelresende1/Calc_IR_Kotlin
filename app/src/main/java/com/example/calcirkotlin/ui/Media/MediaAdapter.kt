package com.example.calcirkotlin.ui.Media


import android.app.AlertDialog
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.calcirkotlin.Model.ListaMediaModel.DeletaMediaModel
import com.example.calcirkotlin.Model.ListaMediaModel.Item
import com.example.calcirkotlin.Model.ListaMoviModel.DelModel
import com.example.calcirkotlin.Model.LoginModel.TokenGetModel
import com.example.calcirkotlin.R
import com.example.calcirkotlin.RestAPI.RetrofitInitializer
import com.example.calcirkotlin.RestAPI.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MediaAdapter(
    private val listaMedia: MutableList<Item>?,
    private var activity: FragmentActivity?
) : Adapter<MediaAdapter.MediaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.media_celula, parent, false)
        return MediaViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listaMedia?.size!!
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val c = listaMedia?.get(position)
        holder.txt_acoes?.setText(c?.getAcoes())
        holder.txt_media_compra?.setText("R$ " + c?.getMediaCompra().toString())
        holder.txt_media_venda?.setText("R$ " + c?.getMediaVenda().toString())
        holder.txt_result?.setText("R$ " + c?.getLucro().toString())
        holder.txt_quant_compra?.setText("x" + c?.getQuantCompraResult().toString())
        holder.txt_quant_venda?.setText("x" + c?.getQuantVendaResult().toString())

        if(c?.getLucro()!! >= 0)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#e8fbe8"))
            holder.btt_media.setBackgroundColor(Color.parseColor("#e8fbe8"))
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#fbfbe8"))
            holder.btt_media.setBackgroundColor(Color.parseColor("fbfbe8"))
        }
    }

    inner class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var txt_acoes: TextView?
        var txt_media_compra: TextView?
        var txt_media_venda: TextView?
        var txt_result: TextView?
        var txt_quant_compra: TextView?
        var txt_quant_venda: TextView?
        var btt_media: ImageButton

        init{
            txt_acoes = itemView?.findViewById<TextView?>(R.id.txt_acao)
            txt_media_compra = itemView?.findViewById<TextView?>(R.id.txt_media_compra)
            txt_media_venda = itemView?.findViewById<TextView?>(R.id.txt_media_venda)
            txt_result = itemView?.findViewById<TextView?>(R.id.txt_result)
            txt_quant_compra = itemView?.findViewById<TextView?>(R.id.txt_quant_compra)
            txt_quant_venda = itemView?.findViewById<TextView?>(R.id.txt_quant_venda)
            btt_media = itemView.findViewById(R.id.btt_media) as ImageButton
            btt_media.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val c = listaMedia?.get(getAdapterPosition())
            val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
            builder.setTitle("Deseja Excluir?")
            builder.setItems(
                arrayOf<CharSequence>("Sim", "Não")
            ) { dialogInterface, i ->
                when (i) {
                    0 -> {
                        exluirAPI(
                            c?.getUserId()!!.toInt(),
                            c?.getIdResult()!!.toInt(),
                            getAdapterPosition()
                        )
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Item excluído!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    1 -> {
                    }
                }
            }
            builder.create().show()
        }
    }


    fun exluirAPI(use: Int, id_result: Int, position: Int) {
        val retrofitClient = RetrofitInitializer.getRetrofitInstance()
        val deletaMediaModel =
            DeletaMediaModel(use, id_result)
        val endpoint = retrofitClient.create(Service.MoviMediaDellService::class.java)
        val call = endpoint.excluimedia(
            TokenGetModel.getAccess_token(),
            "application/json",
            deletaMediaModel
        )
        call?.enqueue(object : Callback<DelModel?> {
            override fun onResponse(call: Call<DelModel?>, response: Response<DelModel?>) {

                val texte = response?.body()
                if (response != null) {
                    if (response.isSuccessful()) { //verifica aqui se o corpo da resposta não é nulo
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Item Excluído",
                            Toast.LENGTH_SHORT
                        ).show()
                        listaMedia?.removeAt(position)
                        notifyDataSetChanged()
                    } else {
                        Toast.makeText(
                            activity?.getApplicationContext(),
                            "Erro ao apagar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<DelModel?>, t: Throwable) {
                Toast.makeText(
                    activity?.getApplicationContext(),
                    "Erro na chamada ao servidor",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}

