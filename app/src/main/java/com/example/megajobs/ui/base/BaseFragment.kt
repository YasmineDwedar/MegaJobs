import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<VDB : ViewDataBinding> constructor(val layoutID: Int) : Fragment() {

    var binding: VDB? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutID, container, false);
        val view = binding?.getRoot()
        setObservers()
        setListeners()

        return view

    }

    override fun onDestroy() {
        removeObservers()
        super.onDestroy()
    }


    abstract fun setListeners()

    abstract fun setObservers()

    abstract fun removeObservers()
}