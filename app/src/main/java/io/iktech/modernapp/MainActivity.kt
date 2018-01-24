package io.iktech.modernapp

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.iktech.modernapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
//    var repository = Repository("Medium Android Repository Article",
//            "Fleka", 1000, true)
    var mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.repository = repository
        binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.executePendingBindings()
//        binding.apply {
////            repositoryName.text = "Modern Android Medium Article"
//            repositoryOwner.text = this.repository?.repositoryOwner
//            numberOfStarts.text = this.repository?.numberOfStars.toString() + " stars"
//        }
//        Handler().postDelayed({repository.repositoryName="New Name"}, 10000)
    }
}
