package com.example.appmars.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appmars.MarsAdapter
import com.example.appmars.Model.Remote.Mars
import com.example.appmars.R
import com.example.appmars.ViewModel.MarsViewModel
import com.example.appmars.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var mBinding: FragmentFirstBinding
    private lateinit var mars:Mars

    private val mViewModel:MarsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter =MarsAdapter()
        mBinding.recyclerview.adapter=adapter
        mBinding.recyclerview.layoutManager= GridLayoutManager(context,2)
        mViewModel.getMarsList().observe(viewLifecycleOwner,{

            it?.let {
                adapter.update(it)
            }

        })
        adapter.selectedShoes().observe(viewLifecycleOwner, Observer {


            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion", it.id.toString())

            }
            val bundle = Bundle().apply {
                putString("marsId", it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
}