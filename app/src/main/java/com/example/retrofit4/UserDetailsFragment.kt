package com.example.retrofit4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.retrofit4.databinding.FragmentUserDetailsBinding
import com.google.gson.Gson


class UserDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var viewModel: UserDetailsViewModel
    lateinit var repository: UserDetailRepository
    private val args by navArgs<UserDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        val id = args.myArgument.toString()
        val apiService = RetrofitHelper.addUserData().create(ApiService::class.java)
        repository = UserDetailRepository(apiService, id)

        viewModel = ViewModelProvider(
            this,
            UserDetailsViewModelFactory(repository)
        )[UserDetailsViewModel::class.java]

        setUpListener()

        return binding.root
    }

    private fun setUpListener() {
        viewModel.userDetails.observe(viewLifecycleOwner) { userDetails ->
            userDetails.firstOrNull()?.let {
                with(binding) {

                    /* loadingIndicator.visibility = View.GONE
                     name.visibility = View.VISIBLE
                     name.text = it.email*/
                    showContentLayout()
                    userId.text = it.id
                    gender.text = it.gender
                    email.text = it.email
                    firstName.text = it.firstName
                    lastName.text = it.lastName
                    title.text = it.title + "."
                    Glide.with(imageView.context).load(it.picture).into(imageView)

                    Toast.makeText(requireContext(), it.firstName, Toast.LENGTH_SHORT).show()
                }
            }
        }
        showPlaceholderLayout()

    }

    private fun showPlaceholderLayout() {
        binding.placeholderLayout.visibility = View.VISIBLE
        binding.contentLayout.visibility = View.GONE
    }

    private fun showContentLayout() {
        binding.placeholderLayout.visibility = View.GONE
        binding.contentLayout.visibility = View.VISIBLE
    }
}