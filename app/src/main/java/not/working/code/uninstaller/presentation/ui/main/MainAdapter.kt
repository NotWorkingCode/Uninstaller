package not.working.code.uninstaller.presentation.ui.main

import android.content.pm.ApplicationInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import not.working.code.uninstaller.R
import not.working.code.uninstaller.databinding.ItemAppBinding
import not.working.code.uninstaller.model.AppInfo

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val data = ArrayList<AppInfo>()

    fun refresh(newData: List<AppInfo>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(appInfo: AppInfo) {
            binding.apply {
                itemAppTitle.text = appInfo.name
                itemAppPackage.text = appInfo.packageName
                itemAppImage.setImageDrawable(appInfo.icon)
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