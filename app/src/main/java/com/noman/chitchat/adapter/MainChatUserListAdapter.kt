package com.noman.chitchat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noman.chitchat.R
import com.noman.chitchat.model.UserInfo

class MainChatUserListAdapter(
    context: Context,
    userList: MutableList<UserInfo.UserInfoList>,
    private var onClickUser: OnClickUser
):RecyclerView.Adapter<MainChatListUserViewHolder>(){

    private var mContext: Context = context
    private val content: MutableList<UserInfo.UserInfoList> = userList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainChatListUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainChatListUserViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: MainChatListUserViewHolder, position: Int) {
        val userContent: UserInfo.UserInfoList = content[position]
        holder.bind(userContent,mContext)
        holder.itemView.setOnClickListener{
            onClickUser.onClickUser(content[position])
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }
    interface OnClickUser{
        fun onClickUser(userInfo: UserInfo.UserInfoList)
    }
}

class MainChatListUserViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_main_chat_list,parent,false)){
    private var icon: ImageView = itemView.findViewById(R.id.userIcon)
    private var seenIcon: ImageView = itemView.findViewById(R.id.ivUserSeenStatusIcon)
    private val userName: TextView = itemView.findViewById(R.id.txtUserName)
    private val message: TextView = itemView.findViewById(R.id.txtMessage)
    fun bind(item:UserInfo.UserInfoList,context: Context){
       userName.text = item.name
       message.text = "Hello user this is a demo message"
        Glide.with(context).load(item.image)
            .placeholder(R.drawable.google).into(icon)
        Glide.with(context).load(item.image)
            .placeholder(R.drawable.google).into(seenIcon)
    }
}
