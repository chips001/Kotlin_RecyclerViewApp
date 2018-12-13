package com.example.chips.view.recycler.kotlin_recyclerviewapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.time_zone_recycler_view)
        val adapter = TimeZoneRecyclerViewAdapter(this) {
            Toast.makeText(this, it.displayName, Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    class TimeZoneRecyclerViewAdapter(context: Context, private val onItemClicked: (TimeZone) -> Unit):
        RecyclerView.Adapter<TimeZoneRecyclerViewAdapter.TimeZoneViewHolder>() {

        private val inflater = LayoutInflater.from(context)
        private val timeZones = TimeZone.getAvailableIDs().map {
            TimeZone.getTimeZone(it)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeZoneViewHolder {
            val view = inflater.inflate(R.layout.time_zone_recycler_view_row, parent, false)
            val viewHolder = TimeZoneViewHolder(view)
            view.setOnClickListener {
                val position = viewHolder.adapterPosition
                val timeZone = timeZones[position]
                onItemClicked(timeZone)
            }
            return viewHolder
        }

        override fun getItemCount(): Int {
            return timeZones.size
        }

        override fun onBindViewHolder(viewHolder: TimeZoneViewHolder, position: Int) {
            val timeZone = timeZones[position]
            viewHolder.timeZoneTextView.text = timeZone.id
        }

        class TimeZoneViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val timeZoneTextView = view.findViewById<TextView>(R.id.time_zone_text_view)
        }
    }
}
