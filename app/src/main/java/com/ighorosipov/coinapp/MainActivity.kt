package com.ighorosipov.coinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.ighorosipov.coinapp.databinding.ActivityMainBinding
import com.ighorosipov.coinapp.util.di.appComponent


class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        navController?.let {
            setupActionBarWithNavController(it)
        }
        onCustomToolbarBackPress()

    }

    fun inject() {
        appComponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    private fun onCustomToolbarBackPress() {
        binding.toolbar.setNavigationOnClickListener {
            navController?.popBackStack()
        }
    }
}