package com.android.scoringapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.scoringapp.databinding.FragmentScoringBinding

/**
 * A simple [Fragment] subclass.
 */
class ScoringFragment : Fragment() {
    private lateinit var dataBinding: FragmentScoringBinding
    private lateinit var args: ScoringFragmentArgs
    private var team1Score: Int = 0
    private var team2Score: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scoring, container, false)
        dataBinding.apply {
            args = arguments?.let { ScoringFragmentArgs.fromBundle(it) }!!
            teamName1.text = args.team1
            teamName2.text = args.team2
            if (savedInstanceState !== null) {
                scoreNum1.text = savedInstanceState.getString("Score 1")
                scoreNum2.text = savedInstanceState.getString("Score 2")
                team1Score = savedInstanceState.getString("Score 1")?.toInt()!!
                team2Score = savedInstanceState.getString("Score 2")?.toInt()!!
            }
            scoreButton1.setOnClickListener {
                team1Score++
                scoreNum1.text = team1Score.toString()
                checkScore(team1Score, args.team1)
            }
            scoreButton2.setOnClickListener {
                team2Score++
                scoreNum2.text = team2Score.toString()
                checkScore(team2Score, args.team2)
            }
            return root
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("Score 1", team1Score.toString())
            putString("Score 2", team2Score.toString())
        }
    }

    private fun checkScore(score: Int, name: String) {
        if (score >= 5)
        view?.findNavController()?.navigate(
            ScoringFragmentDirections.actionScoringFragmentToFinishFragment(score, name)
        )
    }
}