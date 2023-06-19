package com.noman.chitchat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noman.chitchat.R
import com.noman.chitchat.model.UserInfo

class UserHorizontalListAdapter(
    context: Context,
    userList: MutableList<UserInfo.UserInfoList>,
    private var onClickUserIcon: OnClickUserIcon
):RecyclerView.Adapter<UserListIconItemHolder>(){

    private var mContext: Context = context
    private val content: MutableList<UserInfo.UserInfoList> = userList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListIconItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserListIconItemHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: UserListIconItemHolder, position: Int) {
        val userContent:UserInfo.UserInfoList = content[position]
        holder.bind(userContent,mContext)
        holder.itemView.setOnClickListener{
            onClickUserIcon.onClickUserIcon(userContent.name!!)
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }
    interface OnClickUserIcon{
        fun onClickUserIcon(name:String)
    }
}

class UserListIconItemHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_horizontal_user_list,parent,false)){
    private var icon: ImageView = itemView.findViewById(R.id.userIcon)
    fun bind(item:UserInfo.UserInfoList,context: Context){
        Glide.with(context).load(item.image)
            .placeholder(R.drawable.google).into(icon)
    }
}
