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
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import demo.demoapp.R
import demo.demoapp.databinding.FragmentFindYourMealBinding


@AndroidEntryPoint
class FindYourMealFragment : Fragment() {
    private var fragmentFindYourMealBinding : FragmentFindYourMealBinding? = null
    private val findYourMealViewModel : FindYourMealViewModel by viewModels()
    private val findYourMealAdapter = FindYourMealAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFindYourMealBinding = FragmentFindYourMealBinding.inflate(inflater,container,false)
        return fragmentFindYourMealBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       fragmentFindYourMealBinding?.mealSearchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
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
        fragmentFindYourMealBinding?.mealSearchRecycler?.apply {
            adapter = findYourMealAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            findYourMealViewModel.findYourMealList.collect{
                if(it.isLoading){
                    fragmentFindYourMealBinding?.nothingFound?.visibility = View.GONE
                    fragmentFindYourMealBinding?.progressMealSearch?.visibility = View.VISIBLE
                }
                if(it.error.isNotBlank()){
                fragmentFindYourMealBinding?.nothingFound?.visibility = View.GONE
                fragmentFindYourMealBinding?.progressMealSearch?.visibility = View.GONE
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                if(it.data?.isNotEmpty() == true) {
                    it.data?.let {
                        if (it.isEmpty()) {
                            fragmentFindYourMealBinding?.nothingFound?.visibility = View.VISIBLE
                        }
                        fragmentFindYourMealBinding?.progressMealSearch?.visibility = View.GONE
                        findYourMealAdapter.setContentList(it.toMutableList())
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