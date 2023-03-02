package demo.demoapp.presentation.meal_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController

import dagger.hilt.android.AndroidEntryPoint
import demo.demoapp.R
import demo.demoapp.databinding.FragmentMealDetailBinding

@AndroidEntryPoint
class MealDetailFragment : Fragment() {
    private var fragmentMealDetailBinding : FragmentMealDetailBinding? = null
    private val mealDetailViewModel: MealDetailViewModel by viewModels()

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
                    fragmentMealDetailBinding?.rlProgressBar?.visibility = View.VISIBLE
                    fragmentMealDetailBinding?.nsvDetail?.visibility = View.GONE
                }
                if(it.error?.isNotBlank() == true){
                fragmentMealDetailBinding?.rlProgressBar?.visibility = View.GONE
                fragmentMealDetailBinding?.nsvDetail?.visibility = View.GONE
                Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                }
                if(it.data != null){
                    it.data.let { mealItemDetails ->
                        fragmentMealDetailBinding?.rlProgressBar?.visibility = View.GONE
                        fragmentMealDetailBinding?.nsvDetail?.visibility = View.VISIBLE
                        fragmentMealDetailBinding?.mealDetails = mealItemDetails
                    }

                }
            }
        }

        fragmentMealDetailBinding?.detailsBackArrow?.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}