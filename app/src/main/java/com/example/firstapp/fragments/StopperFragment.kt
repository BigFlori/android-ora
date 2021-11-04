package com.example.firstapp.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopperFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StopperFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var stopperRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun convertNumber(number: Int): String {
        return if(number < 10 && number > -1)
            "0$number"
        else
            "$number"
    }

    private fun generateExampleList(size: Int): List<RoundtimeItem> {
        val list = ArrayList<RoundtimeItem>()
        for(i in 0 until size) {
            list += RoundtimeItem(i, convertNumber(i*2) + ":" + convertNumber(i*3) + "." + convertNumber(i*4))
        }
        return list
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_stopper, container, false)

        val stopperRecyclerView: RecyclerView = view.findViewById(R.id.stopper_recycler_view)
        val roundtimeAdapter: RoundtimeAdapter = RoundtimeAdapter()
        val fabStartPause: FloatingActionButton = view.findViewById(R.id.fab_stopper_startpause)
        val fabRoundtime: FloatingActionButton = view.findViewById(R.id.fab_stopper_roundtime)
        val fabReset: FloatingActionButton = view.findViewById(R.id.fab_stopper_reset)
        val stopperValue: TextView = view.findViewById(R.id.stopper_value)

        stopperRecyclerView.adapter = roundtimeAdapter
        if (container != null) {
            stopperRecyclerView.layoutManager = LinearLayoutManager(container.context)
        }
        stopperRecyclerView.setHasFixedSize(true)

        fabStartPause.setOnClickListener {
            if(!stopperRunning)
                fabStartPause.setImageResource(R.drawable.icon_pause)
            else
                fabStartPause.setImageResource(R.drawable.icon_play)
            stopperRunning = !stopperRunning
        }

        fabRoundtime.setOnClickListener {
            if(!stopperValue.text.equals("00:00.00"))
                roundtimeAdapter.addItem(RoundtimeItem(1, stopperValue.text as String))
        }

        fabReset.setOnClickListener {
            roundtimeAdapter.clearList()
            stopperValue.text = "00:00.00"
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StopperFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StopperFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}