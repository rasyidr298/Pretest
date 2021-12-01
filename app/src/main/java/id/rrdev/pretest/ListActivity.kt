package id.rrdev.pretest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import id.rrdev.pretest.adapter.ListAdapter
import id.rrdev.pretest.data.Data
import id.rrdev.pretest.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    private lateinit var listAdapter: ListAdapter

    private val listDummy1 = (0..100).map { Data(1,"Bambang") }.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listAdapter = ListAdapter()
        with(binding.rvList){
            layoutManager = GridLayoutManager(this@ListActivity, 1, GridLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        listAdapter.addList(listDummy1)
    }
}