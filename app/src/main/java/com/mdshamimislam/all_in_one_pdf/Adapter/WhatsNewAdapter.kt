package com.mdshamimislam.all_in_one_pdf.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.mdshamimislam.all_in_one_pdf.Model_Class.WhatsNew
import com.mdshamimislam.all_in_one_pdf.R

class WhatsNewAdapter(private val mContext: Context, mWhatsNewsList: ArrayList<WhatsNew>) :
    RecyclerView.Adapter<WhatsNewAdapter.WhatsNewViewHolder>() {
    private val mWhatsNewsList: List<WhatsNew>
    override fun onCreateViewHolder(mParent: ViewGroup, viewType: Int): WhatsNewViewHolder {
        val mView: View = LayoutInflater.from(mParent.context)
            .inflate(R.layout.item_whats_new, mParent, false)
        return WhatsNewViewHolder(mView)
    }

    override fun onBindViewHolder(holder: WhatsNewViewHolder, position: Int) {
        holder.tvDescription!!.setText(mWhatsNewsList[position].getDescription())
        holder.tvHeading!!.setText(mWhatsNewsList[position].getTitle())
        if (!mWhatsNewsList[position].getIcon().equals("")) {
            val resources = mContext.resources
            val resourceId = resources.getIdentifier(
                mWhatsNewsList[position].getIcon(),
                "drawable", mContext.packageName
            )
            holder.icon!!.setBackgroundResource(resourceId)
        }
    }

    override fun getItemCount(): Int {
        return mWhatsNewsList.size
    }

    inner class WhatsNewViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @BindView(R.id.title)
        var tvHeading: TextView? = null

        @BindView(R.id.description)
        var tvDescription: TextView? = null

        @BindView(R.id.icon)
        var icon: ImageView? = null

        init {
            if (itemView != null) {
                ButterKnife.bind(this, itemView)
            }
        }
    }

    init {
        this.mWhatsNewsList = mWhatsNewsList
    }
}
