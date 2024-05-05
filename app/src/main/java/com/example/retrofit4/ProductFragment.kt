package com.example.retrofit4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit4.databinding.FragmentProductBinding
import com.google.gson.Gson


class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var viewModel: UserViewModel
    private val adapter = UserAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false)
        val repository = (activity?.application as App).repository
        viewModel =
            ViewModelProvider(this, UserViewModelFactory(repository))[UserViewModel::class.java]
        setUpListener()
        return binding.root
    }

    private fun setUpListener() {
        setUpUserData()
        getUserDetails()
    }

    private fun setUpUserData() {


        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            userData.firstOrNull()?.let {
                with(binding) {
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    adapter.setUserData(it.data)

                }
            }
        }
    }

    private fun getUserDetails() {
        adapter.onItemClick = { userData ->

           /* // Access the first name of the clicked item
            val firstName = userData.firstName.toString()
            Toast.makeText(requireContext(), firstName, Toast.LENGTH_SHORT).show()*/

            val action =
                ProductFragmentDirections.actionProductFragmentToUserDetailsFragment(userData.id)
            findNavController().navigate(action)
        }
    }


}