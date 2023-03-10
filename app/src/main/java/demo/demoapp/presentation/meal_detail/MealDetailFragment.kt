package demo.demoapp.presentation.meal_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import dagger.hilt.android.AndroidEntryPoint
import demo.demoapp.BaseFragment
import demo.demoapp.R
import demo.demoapp.databinding.FragmentMealDetailBinding

@AndroidEntryPoint
class MealDetailFragment : BaseFragment<FragmentMealDetailBinding>(R.layout.fragment_meal_detail){

    private val mealDetailViewModel: MealDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(getString(R.string.mealId))?.let {
            mealDetailViewModel.getMealDetails(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            mealDetailViewModel.mealDetails.collect{
                with(baseBinding){
                    mealDetailsState = it
                }
                when {
                    it.error?.isNotBlank() == true -> {
                        Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
                    }
                }
                when {
                    it.data != null -> {
                        it.data.let { mealItemDetails ->
                            with(baseBinding){
                                mealDetails = mealItemDetails
                            }
                        }
                    }
                }
            }
        }

        baseBinding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}