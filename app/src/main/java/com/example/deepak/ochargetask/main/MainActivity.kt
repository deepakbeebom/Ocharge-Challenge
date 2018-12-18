package com.example.deepak.ochargetask.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.deepak.ochargetask.R
import com.example.deepak.ochargetask.adapters.UserAdapter
import com.example.deepak.ochargetask.application.UserViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        AndroidInjection.inject(this)


        val vm = ViewModelProviders.of(this, viewModelFactory)[UserViewModel::class.java]
        load.setOnClickListener {
            vm.allUsers.observe(this, Observer {
                adapter.setUsers(it)
                load.visibility = View.GONE
            })
        }

//        viewModelFactory = ViewModelProvider.Factory

//        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
//        load.setOnClickListener {
//            vm.allUsers.observe(this, Observer {
//                adapter.setUsers(it)
//                load.visibility = View.GONE
//            })
//        }

    }
}
