package demo.demoapp.presentation.find_meal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import demo.demoapp.BaseFragment
import demo.demoapp.R
import demo.demoapp.databinding.FragmentBaseBinding
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
                        this.nothingFound.visibility = View.GONE
                    }
                    showProgressBar(baseBinding.progressMealSearch)
                }else if(it.error.isNotBlank()){
                    with(baseBinding) {
                        this.nothingFound.visibility = View.VISIBLE
                        this.mealSearchRecycler.visibility = View.GONE
                    }
                    hideProgressBar(baseBinding.progressMealSearch)
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }else if(it.data?.isNotEmpty() == true) {
                    it.data.let { mealList ->
                        with(baseBinding) {
                         this.mealSearchRecycler.visibility = View.VISIBLE
                         this.nothingFound.visibility = View.GONE
                        }
                        hideProgressBar(baseBinding.progressMealSearch)
                        findYourMealAdapter.setContentList(mealList.toMutableList())
                    }
                }else{
                    with(baseBinding){
                        this.nothingFound.visibility = View.VISIBLE
                        this.mealSearchRecycler.visibility = View.GONE
                    }
                }
            }
        }

        findYourMealAdapter.itemClickListener {
            val bundle = bundleOf(getString(R.string.mealId) to it.mealId)
             findNavController().navigate(R.id.action_findYourMealFragment_to_mealDetailFragment,bundle)
        }

    }

}