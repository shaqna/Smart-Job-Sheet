package com.maoungedev.smartjobsheet.data.mapper

import com.maoungedev.smartjobsheet.data.sources.local.entity.PracticeEntity
import com.maoungedev.smartjobsheet.data.sources.local.entity.QuestionEntity
import com.maoungedev.smartjobsheet.data.sources.remote.response.PracticeResponse
import com.maoungedev.smartjobsheet.data.sources.remote.response.QuestionResponse
import com.maoungedev.smartjobsheet.domain.model.Practice
import com.maoungedev.smartjobsheet.domain.model.Question

object DataMapper {

    object QuizMapper {
        fun mapResponsesToEntities(
            responses: List<QuestionResponse>,
            collection: String
        ): List<QuestionEntity> =
            responses.map {
                QuestionEntity(
                    uid = it.uid,
                    number = it.number,
                    question = it.question,
                    answer = it.answer,
                    correctAnswer = it.correct_answer,
                    collection = collection,
                    quizPicture = it.picture
                )
            }


        fun mapEntitiesToDomain(
            entities: List<QuestionEntity>
        ): List<Question> =
            entities.map {
                Question(
                    uid = it.uid,
                    number = it.number,
                    question = it.question,
                    answer = it.answer,
                    correctAnswer = it.correctAnswer,
                    quizPicture = it.quizPicture
                )
            }
    }

    object PracticeMapper {
        fun mapResponsesToEntities(
            responses: PracticeResponse,
            docId: String
        ): PracticeEntity =
            PracticeEntity(
                id = responses.id,
                title = responses.title,
                achievementIndicator = responses.achievement_indicator,
                basicCompetencies = responses.basic_competencies,
                information = responses.information,
                instruction = responses.instruction,
                materials = responses.materials,
                objective = responses.objective,
                tools = responses.tools,
                task = responses.task,
                workSafety = responses.work_safety,
                workSteps = responses.work_steps,
                picture = responses.picture,
                docId = docId
            )


        fun mapEntitiesToDomain(
            entities: PracticeEntity
        ): Practice =
            Practice(
                id = entities.id,
                title = entities.title,
                achievementIndicator = entities.achievementIndicator,
                basicCompetencies = entities.basicCompetencies,
                information = entities.information,
                instruction = entities.instruction,
                materials = entities.materials,
                objective = entities.objective,
                tools = entities.tools,
                task = entities.task,
                workSafety = entities.workSafety,
                workSteps = entities.workSteps,
                picture = entities.picture,
            )
    }

}