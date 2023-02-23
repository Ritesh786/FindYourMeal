package transport.ritesh.demoapp.presentation.meal_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.navArgs

import dagger.hilt.android.AndroidEntryPoint
import transport.ritesh.demoapp.R
import transport.ritesh.demoapp.databinding.FragmentFindYourMealBinding
import transport.ritesh.demoapp.databinding.FragmentMealDetailBinding

@AndroidEntryPoint
class MealDetailFragment : Fragment() {
    private var fragmentMealDetailBinding : FragmentMealDetailBinding? = null
    private val mealDetailViewModel:MealDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMealDetailBinding = FragmentMealDetailBinding.inflate(inflater,container,false)
        return fragmentMealDetailBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(getString(R.string.mealId))?.let {
            mealDetailViewModel.getMealDetails(it)
        }
        lifecycle.coroutineScope.launchWhenCreated {
            mealDetailViewModel.mealDetails.collect{
                if(it.isLoading == true){
                //todo Add ProgressBar here
                }
                if(it.error?.isNotBlank() == true){
                Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                if(it.data != null){
                    it.data?.let {
                        fragmentMealDetailBinding?.mealDetails = it
                    }

                }
            }
        }

    }

}