package com.example.appmars

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmars.Model.Remote.Mars
import com.example.appmars.databinding.ItemMarsBinding

class MarsAdapter: RecyclerView.Adapter<MarsAdapter.MarsVH>() {

    private var listMars= listOf<Mars>()
    private val selectMars= MutableLiveData<Mars>()

    fun update(list:List<Mars>){

        listMars=list
        notifyDataSetChanged()
    }

    fun selectedShoes(): LiveData<Mars> = selectMars

    inner class  MarsVH(private val mBinding: ItemMarsBinding):
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bind(mars:Mars){
            Glide.with(mBinding.itemImagen).load(mars.img_src).centerCrop()
                .into(mBinding.itemImagen)
            Log.d("pruebaimagen",mars.img_src)
            Log.d("pruebaid",mars.id)
            Log.d("pruebatype",mars.type)
            Log.d("pruebaprice",mars.price.toString())

     /*       mBinding.itemNombre.text=shoes.nombre.toUpperCase()
            mBinding.itemOrigen.text="El producto es de "+shoes.origen+" y pertenece a la marca "+shoes.marca
            //  mBinding.itemMarca.text="De la marca: "+shoes.marca
            mBinding.itemNumero.text="Numero disponible: "+shoes.numero*/
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            selectMars.value=listMars[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsVH {
        return  MarsVH(ItemMarsBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: MarsVH, position: Int) {
        val phone = listMars[position]
        holder.bind(phone)
    }
    override fun getItemCount()= listMars.size

}