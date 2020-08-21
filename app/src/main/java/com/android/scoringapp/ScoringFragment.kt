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
            teamName1.text = args.team1
            teamName2.text = args.team2
            viewModel.apply {
                scoreNum1.text = team1Score.toString()
                scoreNum2.text = team2Score.toString()

                scoreButton1.setOnClickListener {
                    updateScore(1)
                    scoreNum1.text = team1Score.toString()
                    checkScore(team1Score.value, args.team1)
                }
                scoreButton2.setOnClickListener {
                    updateScore(2)
                    scoreNum2.text = team2Score.toString()
                    checkScore(team2Score.value, args.team2)
                }

                team1Score.observe(viewLifecycleOwner, Observer {
                    scoreNum1.text = it.toString()
                })
                team2Score.observe(viewLifecycleOwner, Observer {
                    scoreNum2.text = it.toString()
                })
            }
            return root
        }
    }

    private fun checkScore(score: Int?, name: String) {
        if (score!! >= 5)
        view?.findNavController()?.navigate(
            ScoringFragmentDirections.actionScoringFragmentToFinishFragment(score, name)
        )
    }
}