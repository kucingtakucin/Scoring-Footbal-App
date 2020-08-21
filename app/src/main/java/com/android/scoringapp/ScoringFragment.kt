package com.android.scoringapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.android.scoringapp.databinding.FragmentScoringBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoringFragment : Fragment() {
    private lateinit var dataBinding: FragmentScoringBinding
    private lateinit var args: ScoringFragmentArgs
    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_scoring, container, false
        )
        viewModel = ViewModelProvider(this@ScoringFragment)
            .get(ScoreViewModel::class.java)
        dataBinding.apply {
            args = arguments?.let { ScoringFragmentArgs.fromBundle(it) }!!
            scoreViewModel = viewModel
            lifecycleOwner = this@ScoringFragment
            teamName1.text = args.team1
            teamName2.text = args.team2
            viewModel.apply {
                setTeam(args.team1, args.team2)
                eventFinish.observe(viewLifecycleOwner, Observer {
                    if (it!!)
                    view?.findNavController()?.navigate(
                        ScoringFragmentDirections.actionScoringFragmentToFinishFragment(winners.value!!)
                    )
                })
            }
            return root
        }
    }
}