package io.iktech.modernapp

import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.iktech.modernapp.databinding.RvItemRepositoryBinding

/**
 * Created by ikolomiyets on 24/01/2018.
 */
class RepositoryRecyclerViewAdapter(private var items: ArrayList<Repository>,
                                    private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>(), Parcelable {

    constructor(parcel: Parcel) : this(
            TODO("items"),
            TODO("listener")) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private var binding: RvItemRepositoryBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repository, listener: OnItemClickListener?) {
            binding.repository = repo
            if (listener != null) {
                binding.root.setOnClickListener({ _ -> listener.onItemClick(layoutPosition) })
            }

            binding.executePendingBindings()
        }
    }

    fun replaceData(items: ArrayList<Repository>) {
        this.items = items
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryRecyclerViewAdapter> {
        override fun createFromParcel(parcel: Parcel): RepositoryRecyclerViewAdapter {
            return RepositoryRecyclerViewAdapter(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryRecyclerViewAdapter?> {
            return arrayOfNulls(size)
        }
    }

}

