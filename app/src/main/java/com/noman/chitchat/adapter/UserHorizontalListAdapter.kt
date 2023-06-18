package com.noman.chitchat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.noman.chitchat.R
import com.noman.chitchat.model.User

class UserHorizontalListAdapter(
    context: Context,
    userList: MutableList<User.Users>,
    private var onClickUserIcon: UserHorizontalListAdapter.OnClickUserIcon
):RecyclerView.Adapter<UserListIconItemHolder>(){

    private var mContext: Context = context
    private val content: MutableList<User.Users> = userList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListIconItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserListIconItemHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: UserListIconItemHolder, position: Int) {
        val userContent: User.Users = content[position]
        holder.bind(userContent)
        holder.itemView.setOnClickListener{
            onClickUserIcon.onClickUserIcon(userContent.id)
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }
    interface OnClickUserIcon{
        fun onClickUserIcon(id:Int)
    }
}

class UserListIconItemHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_horizontal_user_list,parent,false)){
    private var icon: ImageView = itemView.findViewById(R.id.userIcon)
    fun bind(item:User.Users){
        icon.setImageResource(item.image)
    }
}
