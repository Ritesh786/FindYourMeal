package transport.ritesh.demoapp.presentation.find_meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import transport.ritesh.demoapp.databinding.ViewHolderSearchListBinding
import transport.ritesh.demoapp.domain.model.Meal

class FindYourMealAdapter : RecyclerView.Adapter<FindYourMealAdapter.MyViewHolder>() {

    private var listener :((Meal)->Unit)?=null

    var list = mutableListOf<Meal>()

    fun setContentList(list: MutableList<Meal>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: ViewHolderSearchListBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FindYourMealAdapter.MyViewHolder {
        val binding =
            ViewHolderSearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(Meal)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: FindYourMealAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.meal = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}