package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.QuestionCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.QuestionResponse;
import com.codeline.CertiGo.DTOUpdateRequest.QuestionUpdateRequest;
import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.Entity.UserAnswer;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.DTOResponse.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.QuestionRepository;
import com.codeline.CertiGo.Repository.QuizRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired

    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    // ---------------- SAVE ----------------
    public QuestionResponse saveQuestion(QuestionCreateRequestDTO request) throws CustomException {

        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setIsActive(true);
        question.setCreatedAt(new Date());

        // QUIZ
        Quiz quiz = quizRepository.findById(request.getQuiz_id())
                .orElseThrow(() -> new CustomException(Constants.QUIZ_ID_NOT_VALID, 404));
        question.setQuiz(quiz);

        // OPTIONS
        List<Option> options = new ArrayList<>();
        if (request.getOptions() != null && !request.getOptions().isEmpty()) {
            for (Option option : request.getOptions()) {
                option.setQuestion(question);
                options.add(option);
            }
        }
        question.setOptions(options);
        // USER ANSWERS
        List<UserAnswer> userAnswers = new ArrayList<>();
        if (request.getUserAnswers() != null && !request.getUserAnswers().isEmpty()) {
            for (UserAnswer userAnswer : request.getUserAnswers()) {
                userAnswer.setQuestion(question);
                userAnswers.add(userAnswer);
            }
        }
        question.setUserAnswers(userAnswers);

        Question savedQuestion = questionRepository.save(question);
        return QuestionResponse.fromEntity(savedQuestion);
    }

    public List<QuestionResponse> getAllQuestions() {

        List<Question> questions = questionRepository.findAll();
        List<QuestionResponse> responseList = new ArrayList<>();

        for (Question question : questions) {
            if (Boolean.TRUE.equals(question.getIsActive())) {
                responseList.add(QuestionResponse.fromEntity(question));
            }
        }
        return responseList;
    }

    public QuestionResponse getQuestionById(Integer id) throws CustomException {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.BAD_REQUEST, 404));

        if (Boolean.TRUE.equals(question.getIsActive())) {
            return QuestionResponse.fromEntity(question);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }
    }

    public void deleteQuestion(Integer id) throws CustomException {

        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException(Constants.BAD_REQUEST, 404));

        if (Boolean.TRUE.equals(question.getIsActive())) {
            question.setIsActive(false);
            question.setUpdatedAt(new Date());
            questionRepository.save(question);
        } else {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }
    }

    // UPDATE
    public QuestionResponse updateQuestion(QuestionUpdateRequest request) throws CustomException {

        Question existingQuestion = questionRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(Constants.BAD_REQUEST, 404));
        // Check active
        if (!Boolean.TRUE.equals(existingQuestion.getIsActive())) {
            throw new CustomException(Constants.BAD_REQUEST, 400);
        }

        // Update basic fields
        existingQuestion.setQuestionText(request.getQuestionText());
        existingQuestion.setCorrectAnswer(request.getCorrectAnswer());
        existingQuestion.setUpdatedAt(new Date());

        // Update quiz (optional)
        if (request.getQuiz_id() != null) {
            Quiz quiz = quizRepository.findById(request.getQuiz_id())
                    .orElseThrow(() -> new CustomException(Constants.QUIZ_ID_NOT_VALID, 404));
            existingQuestion.setQuiz(quiz);
        }

        // Update options
        List<Option> options = new ArrayList<>();
        if (request.getOptions() != null && !request.getOptions().isEmpty()) {
            for (Option option : request.getOptions()) {
                option.setQuestion(existingQuestion);
                options.add(option);
            }
        }
        existingQuestion.setOptions(options);

        // Update user answers
        List<UserAnswer> userAnswers = new ArrayList<>();
        if (request.getUserAnswers() != null && !request.getUserAnswers().isEmpty()) {
            for (UserAnswer userAnswer : request.getUserAnswers()) {
                userAnswer.setQuestion(existingQuestion);
                userAnswers.add(userAnswer);
            }
        }
        existingQuestion.setUserAnswers(userAnswers);

        // Save updated question
        Question savedQuestion = questionRepository.save(existingQuestion);

        return QuestionResponse.fromEntity(savedQuestion);
    }
}