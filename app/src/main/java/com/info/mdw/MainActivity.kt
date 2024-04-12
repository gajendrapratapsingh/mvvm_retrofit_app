package com.info.mdw

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.info.mdw.adapters.PostAdapter
import com.info.mdw.adapters.UserAdapter
import com.info.mdw.api.ApiResponse
import com.info.mdw.api.ApiState
import com.info.mdw.databinding.ActivityMainBinding
import com.info.mdw.models.User
import com.info.mdw.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var viewModel: MainViewModel
//    //private val viewModel: MainViewModel by viewModels()
//    private lateinit var adapter: UserAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val mainViewModel:MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        //recyclerView = findViewById(R.id.recyclerView)
        //progressBar = findViewById(R.id.progressBar)
        //adapter = UserAdapter()
        //recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = adapter
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MainViewModel::class.java)

//        viewModel.usersLiveData.observe(this, Observer { response ->
//            when (response) {
//                is ApiResponse.Success -> showData(response.data)
//                is ApiResponse.Error -> showToast(response.message ?: "Unknown error")
//                is ApiResponse.Loading -> showLoading()
//            }
//        })

        //viewModel.fetchUsers()

        initRecyclerview()

        mainViewModel.getPost()

        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect { it->
                when(it){
                    is ApiState.Loading->{
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success->{
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.setData(it.data)
                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{

                    }
                }
            }
        }
    }

    private fun initRecyclerview() {
        postAdapter= PostAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=postAdapter
        }
    }

//    private fun showData(data: List<User>){
//        progressBar.visibility = View.GONE
//        adapter.submitList(data)
//        recyclerView.visibility = View.VISIBLE
//    }
//
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//
//    private fun showLoading() {
//        recyclerView.visibility = View.GONE
//        progressBar.visibility = View.VISIBLE
//    }
}