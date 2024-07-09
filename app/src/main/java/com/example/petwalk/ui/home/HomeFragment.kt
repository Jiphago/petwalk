package com.example.petwalk.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petwalk.R
import com.example.petwalk.data.HomeItem

class HomeFragment : Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeItemAdapter(getItems(), this)
        recyclerView.adapter = adapter
    }

    private fun getItems(): List<HomeItem> {
        return listOf(
            HomeItem("동네 친구들과 함께하는 산책!", "경북대학교 정문", "Caption 2", "예약 중", R.drawable.dog_1),
            HomeItem("오전 산책할 사람 구합니다!", "경북대학교 쪽문", "Caption 2", "예약 중", R.drawable.dog_2),
            HomeItem("활동적인 산책 친구 구함", "경북대학교 정문", "Caption 2", "예약 중", R.drawable.dog_3),
            HomeItem("산책하며 힐링하자!", "경북대학교 북문", "Caption 2", "예약 중", R.drawable.dog_4),
            HomeItem("우릭 강쥐랑 산책하실분", "경북대학교 텍문", "Caption 2", "예약 중", R.drawable.dog_5),
            HomeItem("Title 6", "Caption 1", "Caption 2", "예약 중", R.drawable.dog_1),
            HomeItem("Title 7", "Caption 1", "Caption 2", "예약 중", R.drawable.dog_2),
            HomeItem("Title 8", "Caption 1", "Caption 2", "예약 중", R.drawable.dog_3),
            HomeItem("Title 9", "Caption 1", "Caption 2", "예약 중", R.drawable.dog_4),
            HomeItem("Title 10", "Caption 1", "Caption 2", "예약 중", R.drawable.dog_5)
        )
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(requireContext(), PostActivity::class.java)
        startActivity(intent)
    }
}
