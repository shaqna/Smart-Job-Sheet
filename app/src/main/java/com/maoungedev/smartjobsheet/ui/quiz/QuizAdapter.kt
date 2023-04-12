package com.maoungedev.smartjobsheet.ui.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.maoungedev.smartjobsheet.databinding.ItemQuestionBinding
import com.maoungedev.smartjobsheet.domain.model.Question
import com.maoungedev.smartjobsheet.utils.ButtonColor

class QuizAdapter : RecyclerView.Adapter<QuizAdapter.QuestionViewHolder>() {

    private lateinit var onBtnSubmitCallBack: OnBtnSubmitCallBack
    private val questions = arrayListOf<Question>()

    fun setItems(list: List<Question>) {
        questions.clear()
        questions.addAll(list)
    }


    override fun onBindViewHolder(holder: QuizAdapter.QuestionViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemBinding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    fun setOnItemClickCallBack(onItemClickCallBack: OnBtnSubmitCallBack) {
        this.onBtnSubmitCallBack = onItemClickCallBack
    }

    inner class QuestionViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var answerSelected: String? = null

        fun bind(item: Question) {

            with(binding) {

                tvQuestion.text = item.question.replace("\\n", "\n")
                btnA.text = "A. ${item.answer[0]}"
                btnB.text = "B. ${item.answer[1]}"
                btnC.text = "C. ${item.answer[2]}"
                btnD.text = "D. ${item.answer[3]}"


                btnSubmit.setOnClickListener {
                    onBtnSubmitCallBack.checkAnswer(
                        answerSelected ?: "",
                        item.correctAnswer
                    )
                }
            }
            observeAnswer(item.answer)
        }

        private fun observeAnswer(answer: List<String>) {
            initDefaultSelector()
            with(binding) {
                btnA.setOnClickListener {
                    setButtonColorSelector(it)
                    answerSelected = answer[0]

                }
                btnB.setOnClickListener {
                    setButtonColorSelector(it)
                    answerSelected = answer[1]

                }
                btnC.setOnClickListener {
                    setButtonColorSelector(it)
                    answerSelected = answer[2]

                }
                btnD.setOnClickListener {
                    setButtonColorSelector(it)
                    answerSelected = answer[3]

                }

            }
        }

        private fun initDefaultSelector() {
            with(binding) {
                btnA.backgroundTintList =
                    ContextCompat.getColorStateList(itemView.context, ButtonColor.UNSELECTED)
                btnB.backgroundTintList = ContextCompat.getColorStateList(
                    itemView.context,
                    ButtonColor.UNSELECTED
                )
                btnC.backgroundTintList = ContextCompat.getColorStateList(
                    itemView.context,
                    ButtonColor.UNSELECTED
                )
                btnD.backgroundTintList = ContextCompat.getColorStateList(
                    itemView.context,
                    ButtonColor.UNSELECTED
                )

            }
        }


        private fun setButtonColorSelector(it: View?) {
            with(binding) {
                when (it) {
                    btnA -> {
                        btnA.backgroundTintList =
                            ContextCompat.getColorStateList(itemView.context, ButtonColor.SELECTED)
                        btnB.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )
                        btnC.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )
                        btnD.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )

                    }
                    btnB -> {
                        btnA.backgroundTintList =
                            ContextCompat.getColorStateList(
                                itemView.context,
                                ButtonColor.UNSELECTED
                            )
                        btnB.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.SELECTED
                        )
                        btnC.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )
                        btnD.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )

                    }
                    btnC -> {
                        btnA.backgroundTintList =
                            ContextCompat.getColorStateList(
                                itemView.context,
                                ButtonColor.UNSELECTED
                            )
                        btnB.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )
                        btnC.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.SELECTED
                        )
                        btnD.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )

                    }
                    btnD -> {
                        btnA.backgroundTintList =
                            ContextCompat.getColorStateList(
                                itemView.context,
                                ButtonColor.UNSELECTED
                            )
                        btnB.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )
                        btnC.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.UNSELECTED
                        )
                        btnD.backgroundTintList = ContextCompat.getColorStateList(
                            itemView.context,
                            ButtonColor.SELECTED
                        )

                    }

                }
            }
        }
    }


    interface OnBtnSubmitCallBack {
        fun checkAnswer(answer: String, correctAnswer: String)
    }
}