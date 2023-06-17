package org.d3if6706213098GaleriHewan.makanan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if6706213098GaleriHewan.R
import org.d3if6706213098GaleriHewan.databinding.FragmentDataMakananBinding
import org.d3if6706213098GaleriHewan.model.Makanan
import org.d3if6706213098GaleriHewan.network.MakananApi

class MakananAdapter : RecyclerView.Adapter<MakananAdapter.ViewHolder>() {

    private val data = mutableListOf<Makanan>()
    fun updateData(newData: List<Makanan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: FragmentDataMakananBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(makanan: Makanan) = with(binding) {
            namaMakananTxt.text = makanan.nama
            kaloriTxt.text = makanan.kalori
            Glide.with(fotoMakanan.context)
                .load(MakananApi.getMakananUrl(makanan.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(fotoMakanan)
//
//
//            root.setOnClickListener {
//                val message = root.context.getString(R.string.message, makanan.nama)
//                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentDataMakananBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}