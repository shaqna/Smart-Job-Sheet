package com.maoungedev.smartjobsheet.ui.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.maoungedev.smartjobsheet.databinding.ActivityPracticeBinding
import com.maoungedev.smartjobsheet.domain.model.Practice
import com.maoungedev.smartjobsheet.utils.Helper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class PracticeActivity : AppCompatActivity() {

    private val binding: ActivityPracticeBinding by lazy {
        ActivityPracticeBinding.inflate(layoutInflater)
    }


    private val viewModel: PracticeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadKoinModules(PracticeViewModel.inject())

        processIntent()
        fetchPractice()
        observe()
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                handleStateChange(it)
            }
            .launchIn(lifecycleScope)
    }

    private fun handleStateChange(it: PracticeActivityState) {
        when(it) {
            PracticeActivityState.Init -> Unit
            is PracticeActivityState.ErrorFetchedPractice -> showToast(it.message)
            is PracticeActivityState.IsLoading -> handleLoading(it.isLoading)
            is PracticeActivityState.ShowToast -> showToast(it.message)
            is PracticeActivityState.SuccessFetchedPractice -> handleSuccessFetchedPractice(it.practice)
        }
    }

    private fun handleSuccessFetchedPractice(practice: Practice) {
        with(binding) {
            if(practice.id == 5 || practice.id == 6) {
                tvPracticeNameTitle.text = Helper.generateTitlePracticeOrder(practice.id)
                tvPracticeName.text = practice.title
                tvInformation.text = Helper.generateTextInformationFromList(practice.information)
                tvAchievementIndicator.text = Helper.generateTextFromList(practice.achievementIndicator)
                tvBasicCompetence.text = Helper.generateTextFromList(practice.basicCompetencies)
                tvInstruction.text = Helper.generateTextFromList(practice.instruction)
                tvMaterials.text = Helper.generateTextFromList(practice.materials)
                tvObjective.text = Helper.generateTextFromList(practice.objective)
                tvTask.text = Helper.generateTextFromList(practice.task)
                tvTools.text = Helper.generateTextFromList(practice.tools)
                tvWorkSafety.text = Helper.generateTextFromList(practice.workSafety)
                tvWorkSteps.text = Helper.generateTextFromList(practice.workSteps)

                // penyesuaian UI dengan data yang diterima dari Firebase
                ivPractice.visibility = View.GONE
                tvInstructionTitle.visibility = View.GONE
                tvInstruction.visibility = View.GONE
                tvInformationTitle.visibility = View.GONE
                tvInformation.visibility = View.GONE

            } else {
                tvPracticeNameTitle.text = Helper.generateTitlePracticeOrder(practice.id)
                tvPracticeName.text = practice.title
                tvInformation.text = Helper.generateTextInformationFromList(practice.information)
                tvAchievementIndicator.text = Helper.generateTextFromList(practice.achievementIndicator)
                tvBasicCompetence.text = Helper.generateTextFromList(practice.basicCompetencies)
                tvInstruction.text = Helper.generateTextFromList(practice.instruction)
                tvMaterials.text = Helper.generateTextFromList(practice.materials)
                tvObjective.text = Helper.generateTextFromList(practice.objective)
                tvTask.text = Helper.generateTextFromList(practice.task)
                tvTools.text = Helper.generateTextFromList(practice.tools)
                tvWorkSafety.text = Helper.generateTextFromList(practice.workSafety)
                tvWorkSteps.text = Helper.generateTextFromList(practice.workSteps)
                Log.d("Picutre", practice.picture)
                Glide.with(this@PracticeActivity).load(practice.picture).into(ivPractice)
            }
            }

    }

    private fun showToast(message: String) {
        Toast.makeText(this@PracticeActivity, message, Toast.LENGTH_LONG).show()
        Log.d("ERROR", "showToast: $message")
    }

    private fun handleLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
        binding.scrollViewLayout.isVisible = !loading
    }

    private fun processIntent() {
        viewModel.processIntent(intent)
    }

    private fun fetchPractice() {
        viewModel.fetchPractice()
    }

}