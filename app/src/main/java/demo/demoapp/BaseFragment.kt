package demo.demoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutResId : Int) : Fragment() {

    private var _baseBinding : T? = null
    protected val baseBinding : T get() = _baseBinding!!

    open fun T.initialize(){}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _baseBinding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        baseBinding.lifecycleOwner = viewLifecycleOwner
        baseBinding.initialize()
        return baseBinding.root
    }

    protected open fun showView(view : View){
        view.visibility = View.VISIBLE
    }

    protected open fun hideView(view : View){
        view.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _baseBinding = null
    }

}