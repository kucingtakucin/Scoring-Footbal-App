package com.android.scoringapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.android.scoringapp.databinding.FragmentIntroBinding

/**
 * A simple [Fragment] subclass.
 */
class IntroFragment : Fragment() {
    private lateinit var dataBinding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        setHasOptionsMenu(true)
        dataBinding.apply {
            startButton.setOnClickListener {
                view?.findNavController()?.navigate(IntroFragmentDirections
                    .actionIntroFragmentToScoringFragment(
                        teamName1.text.toString(), teamName2.text.toString()
                    )
                )
            }
            return root
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(item)
    }
}