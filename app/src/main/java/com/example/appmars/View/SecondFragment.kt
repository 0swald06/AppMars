package com.example.appmars.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appmars.R
import com.example.appmars.ViewModel.MarsViewModel
import com.example.appmars.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit  var  mBinding: FragmentSecondBinding

    private val  mViewModel : MarsViewModel  by activityViewModels()
    private var marsId : String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSecondBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->

            marsId = bundle.getString("marsId")
            Log.d("pruebaID2",marsId.toString())

        }
        marsId?.let { id ->
            Log.d("pruebaID3",id.toString())
            mViewModel.getMarsById(id.toInt())
            print( mViewModel.getMarsById(id.toInt()))
        }
        mViewModel.selectedItem().observe(viewLifecycleOwner,{
            Log.d("pruebaID4",it.img_src)
            Glide.with(mBinding.itemImagen).load(it.img_src).into(mBinding.itemImagen)
            mBinding.itemPrecio.text=it.price.toString()
            mBinding.itemTipo.text=it.type
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
}