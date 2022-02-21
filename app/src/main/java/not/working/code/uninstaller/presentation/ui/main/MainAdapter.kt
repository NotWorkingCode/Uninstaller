package not.working.code.uninstaller.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import not.working.code.uninstaller.R
import not.working.code.uninstaller.databinding.ItemAppBinding
import not.working.code.uninstaller.model.AppInfo

class MainAdapter(private val onClick: (position: Int) -> Unit) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val data = ArrayList<AppInfo>()

    fun refresh(newData: List<AppInfo>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appInfo: AppInfo) = with(binding){
            itemAppTitle.text = appInfo.name
            itemAppPackage.text = appInfo.packageName
            itemAppImage.setImageDrawable(appInfo.icon)
            root.setCardBackgroundColor(
                if (appInfo.isSelected)
                    root.context.getColor(R.color.accent)
                else
                    root.context.getColor(R.color.white)
            )
            root.setOnClickListener {
                onClick.invoke(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(
        ItemAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size
}