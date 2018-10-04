package com.example.nabin.groupiesample


import android.widget.TextView
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder


class GroupieAdapter1(val model: Model1, var listener: ParentExpandableClickListener) : Item(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        expandableGroup = onToggleListener
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.root.findViewById<TextView>(R.id.tvTitle).text = "${model.title} Parent"
        viewHolder.itemView.setOnClickListener {
            expandableGroup.onToggleExpanded()
            listener.onParentItemClicked(viewHolder.adapterPosition,expandableGroup.isExpanded,model)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_layout_parent
    }

    interface ParentExpandableClickListener {
        fun onParentItemClicked(position: Int, isExpanded: Boolean,model: Model1)
    }
}
