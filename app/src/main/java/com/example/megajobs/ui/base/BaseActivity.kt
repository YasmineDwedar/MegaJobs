import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity <VDB: ViewDataBinding>constructor(val layoutID: Int): AppCompatActivity() {

    var binding: VDB? = null

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutID)

    }

    override fun onStart() {
        super.onStart()
        setListeners()
    }


    abstract fun setListeners()

}