package com.example.nabin.groupiesample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

import java.util.ArrayList

class MainActivity : AppCompatActivity(), GroupieAdapter1.ParentExpandableClickListener, GroupieAdapter2.SubParentExpandableClickListener {
    private var tempExpandableGroup: ExpandableGroup? = null
    private var tempExpanded = false

    private var recyclerView: RecyclerView? = null
    private val groupAdapter = GroupAdapter<ViewHolder>()
    var positionHolder = mutableListOf<PositionHolder>()
    var childPositionHolder = mutableListOf<PositionHolder>()

    var expandableGroupParent: ExpandableGroup? = null
    var expandableGroupChild: ExpandableGroup? = null


    lateinit var subParent: ArrayList<Model1>
    lateinit var child: ArrayList<Model1>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rvGroupie)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        val parentItem = ArrayList<Model1>()
        for (i in 0..9) {
            if (i % 2 == 0) {
                parentItem.add(Model1("Nabin1"))
            } else {
                parentItem.add(Model1("Sujin"))
            }
        }

        subParent = ArrayList<Model1>()
        for (i in 0..4) {
            if (i % 2 == 0) {
                subParent.add(Model1("Subrat"))
            } else {
                subParent.add(Model1("Krishna"))
            }
        }

        child = ArrayList<Model1>()
        for (i in 0..1) {
            if (i % 2 == 0) {
                child.add(Model1("Rumi 1 "))
            } else {
                child.add(Model1("Rumi 2"))
            }
        }



        parentItem.forEachIndexed { index, parent ->
            expandableGroupParent = ExpandableGroup(GroupieAdapter1(parent, this)).apply {
                groupAdapter.add(this)
                positionHolder.add(PositionHolder(this, index))
            }

        }

        recyclerView?.adapter = groupAdapter

    }

    override fun onParentItemClicked(position: Int, isExpanded: Boolean, model: Model1) {
        val tempSubParent = mutableListOf<Model1>()

        if (isExpanded && !positionHolder[position].hasAddedChildren) {
            positionHolder[position].hasAddedChildren = true
            tempSubParent.addAll(subParent)
            positionHolder[position].expandableGroup.apply {
                tempSubParent.forEachIndexed { index, model1 ->
                    add(ExpandableGroup(GroupieAdapter2(model1.title, this@MainActivity)))
                }
                groupAdapter.notifyItemRangeChanged(position,tempSubParent.count())
            }

        }
    }


    override fun onSubParentItemClicked(position: Int, isExpanded: Boolean) {
//        val tempChild = mutableListOf<Model1>()
//        tempChild.addAll(child)
//
//        if (isExpanded && !childPositionHolder[position].hasAddedChildren) {
//            childPositionHolder[position].hasAddedChildren = true
//
//            tempChild.forEachIndexed { index, childItem ->
//                childPositionHolder[position].expandableGroup.add(
//                        Section(
//                        GroupieAdapter3(childItem.title)
//                ))
//            }
//
//        }
    }
}


