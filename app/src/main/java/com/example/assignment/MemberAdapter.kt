package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(private val members: List<Member>) :
    RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    class MemberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.tv_membername)
        val mobileTextView: TextView = view.findViewById(R.id.tv_mobilenumber)
        val roleTextView: TextView = view.findViewById(R.id.tv_memberrole)
        val subscription: TextView = view.findViewById(R.id.tv_subscriptionamount)
        val loanAmount: TextView = view.findViewById(R.id.loanAmountValue)
        val depositAmount: TextView = view.findViewById(R.id.depositAmountValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val member = members[position]
        holder.nameTextView.text = member.member_name
        holder.mobileTextView.text = member.mobile_number.toString()
        holder.roleTextView.text = member.member_role
        holder.subscription.text = member.subscription_amount.toString()
        holder.loanAmount.text = member.loan_amount.toString()
        holder.depositAmount.text = member.deposit_amount.toString()
    }

    override fun getItemCount(): Int = members.size
}
