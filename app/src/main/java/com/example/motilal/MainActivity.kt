package com.example.motilal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.motilal.bg_task.RepoWorker
import com.example.motilal.presentation_layer.MainViewModel
import com.example.motilal.presentation_layer.MainViewModelFactory
import com.example.motilal.utils.ProgressPresenter
import com.example.motilal.utils.ProgressProvider
import kotlinx.android.synthetic.main.activity_main.recycler
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), ProgressProvider {

    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as TestoApplication).repository)
    }
    private lateinit var dialogPresenter: ProgressPresenter
    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = RepoAdapter()
        recycler.adapter = adapter
        dialogPresenter = ProgressPresenter(this@MainActivity)
        viewModel.getRepos()
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.repos.observe(this, Observer {
            if (it == null){
                Toast.makeText(this, "Error occurred during data fetching", Toast.LENGTH_SHORT).show()
            }else{
                it.let {
                    adapter.submitList(it)
                    adapter.notifyDataSetChanged()
                }
            }
        })
        viewModel.showProgress.observe(this, Observer {
            updateProgress(it)
        })
    }

    private fun updateProgress(status: Boolean) {
        if (status){
            (this as ProgressProvider).showProgress()
        } else {
            (this as ProgressProvider).hideProgress()
        }
    }

    override fun showProgress() {
        dialogPresenter.showProgress()
    }

    override fun hideProgress() {
        dialogPresenter.hideProgress()
    }
}