package com.lexmasterteam.stoperappv2.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lexmasterteam.stoperappv2.R
import com.lexmasterteam.stoperappv2.databinding.FragmentMainBinding
import com.lexmasterteam.stoperappv2.presentation.viewmodels.StoperMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding:FragmentMainBinding

    private val mainViewModel by viewModels<StoperMainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.emitTime.collect(){
                binding.tvTimer.text = it.toString()
            }
        }
        binding.btStartstop.setOnClickListener {
            Log.d("STOPER123",mainViewModel.state.value.time)

            if(mainViewModel.state.value.isPaused){
                binding.btStartstop.text="STOP"
                binding.btReset.visibility = View.VISIBLE
                viewLifecycleOwner.lifecycleScope.launchWhenStarted{
                    mainViewModel.startStoper()
                }
            } else{
                binding.btStartstop.text="START"
                viewLifecycleOwner.lifecycleScope.launchWhenStarted{
                    mainViewModel.stoporStartStoper()
                }
            }


        }
        binding.btReset.setOnClickListener {
            mainViewModel.resetStoper()
            binding.btReset.visibility = View.GONE
        }
    }


}