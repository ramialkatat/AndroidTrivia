package com.example.androidtrivia

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidtrivia.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_title, container, false) we will use data binding instead.
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_title,
            container,
            false
        ) //false is to prevent it from being attached to the viewGroup)
//      binding.playButton.setOnClickListener{
//  view:View-> Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)}
// We can replace the above line of code with an extension function of the Android View class: view.findNavController()
//        binding.playButton.setOnClickListener{view:View-> //anonymous function
//        view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)

        //We can also replace the anonymous function. Navigation can create the onClickListener for us
//        binding.playButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment))
//Then after we have used safe args, we can use NavDirections by switching to an anonymous function
        binding.playButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        setHasOptionsMenu(true)//Telling Android that this Fragment has a menu
        return binding.root//which contains the root of the layout that we are inflating
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) { //this method creates the menu by inflating it
        super.onCreateOptionsMenu(menu, inflater)//we inflate menus just like we do to layouts
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //when an item is selected
        return NavigationUI.onNavDestinationSelected(
            item!!,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
    //Note that there is no need for us to specify a new action connecting the source with the destination. We just specify the destination to which we want to navigate to in the menu file itself. In our case, we are navigating to the aboutFragment
}