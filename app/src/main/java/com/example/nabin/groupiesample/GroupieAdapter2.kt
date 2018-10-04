package com.example.nabin.groupiesample

import android.widget.TextView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class GroupieAdapter2(var title:String, var listener:SubParentExpandableClickListener): Item(), ExpandableItem{
    private lateinit var expandableGroup: ExpandableGroup

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.root.findViewById<TextView>(R.id.tvTitle).text = "$title Sub Parent"
        viewHolder.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
            listener.onSubParentItemClicked(viewHolder.adapterPosition,expandableGroup.isExpanded)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_layout_subparent
    }

    interface SubParentExpandableClickListener {
        fun onSubParentItemClicked(position: Int, isExpanded: Boolean)
    }
}
