package com.example.nabin.groupiesample

import android.widget.TextView
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class GroupieAdapter3(var title: String) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.root.findViewById<TextView>(R.id.tvTitle).text = "$title Child"
    }

    override fun getLayout(): Int {
        return R.layout.item_layout_child
    }
}
