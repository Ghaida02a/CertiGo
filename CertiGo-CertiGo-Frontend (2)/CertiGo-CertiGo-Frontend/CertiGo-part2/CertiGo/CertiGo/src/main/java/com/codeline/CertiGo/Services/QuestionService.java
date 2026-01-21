package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.OptionCreateRequest;  // Added import
import com.codeline.CertiGo.DTOCreateRequest.QuestionCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.OptionResponse;
import com.codeline.CertiGo.DTOResponse.QuestionResponse;
import com.codeline.CertiGo.DTOResponse.QuestionWithOptionsResponse;
import com.codeline.CertiGo.DTOUpdateRequest.QuestionUpdateRequest;
import com.codeline.CertiGo.Entity.Option;
import com.codeline.CertiGo.Entity.Question;
import com.codeline.CertiGo.Entity.Quiz;
import com.codeline.CertiGo.Entity.UserAnswer;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codeline.CertiGo.Repository.OptionRepository;
import com.codeline.CertiGo.Repository.QuestionRepository;
import com.codeline.CertiGo.Repository.QuizRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private OptionRepository optionRepository;

    // ---------------- SAVE ----------------
    public QuestionResponse saveQuestion(QuestionCreateRequestDTO request) throws CustomException {
        // Validate the entire request (including options)
        QuestionCreateRequestDTO.validateQuestion(request);

        Question question = new Question();
        question.setQuestionText(request.getQuestionText());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setIsActive(Boolean.TRUE);
        question.setCreatedAt(new Date());

        // QUIZ
        Quiz quiz = quizRepository.findById(request.getQuiz_id())
                .orElseThrow(() -> new CustomException(Constants.QUIZ_ID_NOT_VALID, 404));
        question.setQuiz(quiz);

        // Save the question first
        Question savedQuestion = questionRepository.save(question);

        // Now save the options, linking them to the saved question
        if (request.getOptions() != null && !request.getOptions().isEmpty()) {
            for (OptionCreateRequest optionRequest : request.getOptions()) {
                Option option = OptionCreateRequest.convertToOption(optionRequest);
                option.setQuestion(savedQuestion);  // Link to the saved question
                option.setIsActive(Boolean.TRUE);    // Assuming Option has isActive; set to true
                option.setCreatedAt(new Date());     // Assuming Option has createdAt
                optionRepository.save(option);
            }
        }

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

        // Save updated question
        Question savedQuestion = questionRepository.save(existingQuestion);

        return QuestionResponse.fromEntity(savedQuestion);
    }

    // Get questions with options by quiz ID
    public List<QuestionWithOptionsResponse> getQuestionsWithOptionsByQuizId(Integer quizId) throws CustomException {
        List<Question> questions = questionRepository.findByQuizId(quizId);

        return questions.stream().map(question -> {
            List<Option> options = optionRepository.findByQuestionId(question.getId());
            List<OptionResponse> optionResponses = options.stream()
                    .map(OptionResponse::fromEntity)
                    .collect(Collectors.toList());

            return QuestionWithOptionsResponse.builder()
                    .id(question.getId())
                    .questionText(question.getQuestionText())
                    .options(optionResponses)
                    .build();
        }).collect(Collectors.toList());
    }
}