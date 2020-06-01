package com.clicknext.pattern.view.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clicknext.pattern.R
import com.clicknext.pattern.databinding.ItemExampleBinding
import com.clicknext.pattern.view.example.model.ExampleModel

class ExampleAdapter (private val mContext: Context? ,
                      private val mExampleList: ArrayList<ExampleModel>? ,
                      private val mOnItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {

    private lateinit var mBinding: ItemExampleBinding

    interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mBinding = ItemExampleBinding.inflate(mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        return ViewHolder(mBinding.root)
    }

    override fun getItemCount(): Int {
        return if(mExampleList != null && mExampleList.isNotEmpty()){
            mExampleList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.displayData(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun displayData(position: Int){
            mBinding.txvValueItemExample.text = mExampleList?.get(position)?.value
            onItemClickListener(position)
        }

        private fun onItemClickListener(position: Int){
            itemView.setOnClickListener {
                mOnItemClickListener.onItemClickListener(position)
            }
        }
    }
}