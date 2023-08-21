package com.shall_we.signup

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shall_we.R
import com.shall_we.databinding.FragmentAgreementBinding


class AgreementFragment : Fragment() {
    private lateinit var binding: FragmentAgreementBinding

    fun initAgreement() {

        val checkList = listOf(
            binding.cbAgree1,
            binding.cbAgree2,
            binding.cbAgree3
        )

        fun checkAllChecked(): Boolean {
            if (checkList.all { checkBox -> checkBox.isChecked }){
                binding.btnNext.setBackgroundResource(R.drawable.btn_next)
                return true
            }
            binding.btnNext.setBackgroundResource(R.drawable.btn_next_black)
            return false
        }

        binding.cbAgreeAll.setOnCheckedChangeListener { button, ischecked ->
            if (ischecked) {
                checkList.forEach { checkBox -> checkBox.isChecked = true }
                binding.cbAgree4.isChecked = true
            }
            else {
                checkList.forEach { checkBox -> checkBox.isChecked = false }
                binding.cbAgree4.isChecked = false
            }
        }

        binding.cbAgree1.setOnCheckedChangeListener { button, ischecked ->
            if (ischecked && checkAllChecked()) {
                binding.btnNext.setBackgroundResource(R.drawable.btn_next)
                binding.btnNext.isClickable = true
            }
            else {
                binding.btnNext.setBackgroundResource(R.drawable.btn_next_black)
                binding.btnNext.isClickable = false
            }
        }
        binding.cbAgree2.setOnCheckedChangeListener { button, ischecked ->
            if (ischecked && checkAllChecked()) {
                binding.btnNext.setBackgroundResource(R.drawable.btn_next)
                binding.btnNext.isClickable = true
            }
            else {
                binding.btnNext.setBackgroundResource(R.drawable.btn_next_black)
                binding.btnNext.isClickable = false
            }
        }
        binding.cbAgree3.setOnCheckedChangeListener { button, ischecked ->
            if (ischecked && checkAllChecked()) {
                binding.btnNext.setBackgroundResource(R.drawable.btn_next)
                binding.btnNext.isClickable = true
            }
            else {
                binding.btnNext.setBackgroundResource(R.drawable.btn_next_black)
                binding.btnNext.isClickable = false
            }
        }
        binding.cbAgree4.setOnCheckedChangeListener { button, ischecked ->
            // Todo: 마케팅동의 정보 전달.
        }
        binding.tvAgree2More.setOnClickListener {
            agreementDialog(it, resources.getString(R.string.agreement_service))
        }

        binding.tvAgree3More.setOnClickListener {
            agreementDialog(it, resources.getString(R.string.agreement_personal))
        }
        binding.btnNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isChecked", binding.cbAgree4.isChecked)
            val profileFragment = ProfileFragment() // 전환할 프래그먼트 인스턴스 생성
            profileFragment.arguments = bundle
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView3, profileFragment, "profile")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commitAllowingStateLoss()

        }

    }

    private fun agreementDialog(view: View, string: String) {
        val myLayout = LayoutInflater.from(context).inflate(R.layout.dialog_agreement_more, null)
        val build = AlertDialog.Builder(view.context).apply {
            setView(myLayout)
        }
        val dialog = build.create()
        val textView = myLayout.findViewById<TextView>(R.id.tv_agreement_description) // 여기서 textViewId는 실제 TextView의 ID여야 합니다
        textView.text = string
        textView.movementMethod = ScrollingMovementMethod.getInstance()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        myLayout.findViewById<Button>(R.id.btn_close).setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩 초기화 및 반환
        binding = FragmentAgreementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAgreement()
    }




    companion object {

    }
}


