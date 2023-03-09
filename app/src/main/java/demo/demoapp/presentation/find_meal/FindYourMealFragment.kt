package demo.demoapp.presentation.find_meal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import demo.demoapp.BaseFragment
import demo.demoapp.R
import demo.demoapp.databinding.FragmentFindYourMealBinding

@AndroidEntryPoint
class FindYourMealFragment : BaseFragment<FragmentFindYourMealBinding>(R.layout.fragment_find_your_meal) {

    private val findYourMealViewModel : FindYourMealViewModel by viewModels()
    private val findYourMealAdapter = FindYourMealAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        baseBinding.mealSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
           android.widget.SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
              query?.let {
                  findYourMealViewModel.findYourMealList(it)
              }
               return false
           }
           override fun onQueryTextChange(newText: String?): Boolean {
              return false
           }
       })
        baseBinding.mealSearchRecycler.apply {
            adapter = findYourMealAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            findYourMealViewModel.findYourMealList.collect{
                if(it.isLoading){
                    with(baseBinding){
                        this.mealSearchRecycler.visibility = View.GONE
                    }
                    showView(baseBinding.progressMealSearch)
                    hideView(baseBinding.nothingFound)
                }else if(it.error.isNotBlank()){
                    with(baseBinding) {
                        this.mealSearchRecycler.visibility = View.GONE
                    }
                    hideView(baseBinding.progressMealSearch)
                    showView(baseBinding.nothingFound)
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }else if(it.data?.isNotEmpty() == true) {
                    it.data.let { mealList ->
                        with(baseBinding) {
                         this.mealSearchRecycler.visibility = View.VISIBLE
                         this.nothingFound.visibility = View.GONE
                        }
                        hideView(baseBinding.progressMealSearch)
                        hideView(baseBinding.nothingFound)
                        findYourMealAdapter.setContentList(mealList.toMutableList())
                    }
                }else{
                    with(baseBinding){
                        this.mealSearchRecycler.visibility = View.GONE
                    }
                    showView(baseBinding.nothingFound)
                }
            }
        }

        findYourMealAdapter.itemClickListener {
            val bundle = bundleOf(getString(R.string.mealId) to it.mealId)
             findNavController().navigate(R.id.action_findYourMealFragment_to_mealDetailFragment,bundle)
        }

    }

}