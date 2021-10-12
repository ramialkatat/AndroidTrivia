/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.androidtrivia

import android.content.Intent
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.view.*
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.media.AudioAttributesCompat.fromBundle
import androidx.navigation.findNavController
import com.example.androidtrivia.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
//        binding.nextMatchButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_gameWonFragment_to_gameFragment))
//since we used safe args, we can replace the above line of code with the one below:
        binding.nextMatchButton.setOnClickListener{view:View->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        var args = GameWonFragmentArgs.fromBundle(requireArguments())

        Toast.makeText(context,"NumCorrect: ${args.numCorrect},NumQuestions: ${args.numQuestions}",Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) { //inflating Menu
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)
    }
    private fun getShareIntent():Intent{
        var args = GameWonFragmentArgs.fromBundle(requireArguments())
//        val shareIntent=Intent(Intent.ACTION_SEND) //creating a new share implicit intent
//        shareIntent.setType("text/plain")//setting the type of data we're going to share
//            .putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,args.numCorrect,args.numQuestions))//intent extra is a key value data structure
//        return shareIntent
        //there is another way to do it using ShareCompat where we can set our text without having to know which extra to use
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_success_text,args.numCorrect,args.numQuestions))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share->shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}