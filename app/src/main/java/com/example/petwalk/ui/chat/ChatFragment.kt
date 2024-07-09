package com.example.petwalk.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petwalk.R
import com.example.petwalk.data.ChatItem

class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ChatItemAdapter(getChatItems())
        recyclerView.adapter = adapter
    }

    private fun getChatItems(): List<ChatItem> {
        return listOf(
            ChatItem(R.drawable.chat_profile_1, "일론머스크", "안녕하세요! 해피는 2살 중형견이고, 활발하고 장난기가 많아요. 다른 강아지들과도 잘 어울려요."),
            ChatItem(R.drawable.chat_profile_2, "스티브잡스", "애플을 창립한 잡스입니다."),
            ChatItem(R.drawable.chat_profile_3, "빌게이츠", "마이크로소프트 창립자입니다."),
            ChatItem(R.drawable.chat_profile_4, "마크저커버그", "페이스북을 창립했습니다."),
            ChatItem(R.drawable.chat_profile_5, "래리페이지", "구글 창립자 중 한 명입니다."),
            ChatItem(R.drawable.chat_profile_6, "일론머스크", "안녕하세요! 해피는 2살 중형견이고, 활발하고 장난기가 많아요. 다른 강아지들과도 잘 어울려요."),
            ChatItem(R.drawable.chat_profile_7, "스티브잡스", "애플을 창립한 잡스입니다."),
            ChatItem(R.drawable.chat_profile_1, "빌게이츠", "마이크로소프트 창립자입니다."),
            ChatItem(R.drawable.chat_profile_2, "마크저커버그", "페이스북을 창립했습니다."),
            ChatItem(R.drawable.chat_profile_3, "래리페이지", "구글 창립자 중 한 명입니다."),
            // 추가 샘플 데이터
        )
    }
}