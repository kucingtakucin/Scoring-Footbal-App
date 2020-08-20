package com.android.scoringapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.scoringapp.databinding.FragmentFinishBinding

/**
 * A simple [Fragment] subclass.
 */
class FinishFragment : Fragment() {
    private lateinit var dataBinding: FragmentFinishBinding
    private lateinit var args: FinishFragmentArgs

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_finish, container, false)
        args = arguments?.let { FinishFragmentArgs.fromBundle(it) }!!
        dataBinding.apply {
            resultText.text = "${args.name} menang!!!"
            return root
        }
    }
}