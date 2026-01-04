package com.codeline.CertiGo.Services;

import com.codeline.CertiGo.DTOCreateRequest.QuizResultCreateRequestDTO;
import com.codeline.CertiGo.DTOResponse.QuizResultResponse;
<<<<<<< HEAD
=======
import com.codeline.CertiGo.DTOUpdateRequest.QuizResultUpdateRequest;
>>>>>>> 8f943c218056e41edbb4576ea421649a609600c8
import com.codeline.CertiGo.Entity.*;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Repository.QuizResultRepository;
import com.codeline.CertiGo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
<<<<<<< HEAD
import com.codeline.CertiGo.Repositories.QuizRepository;
import com.codeline.CertiGo.Repositories.CourseRepository;

=======
import java.util.stream.Collectors;
>>>>>>> 8f943c218056e41edbb4576ea421649a609600c8

@Service
public class QuizResultService {

    @Autowired
<<<<<<< HEAD
     QuizResultRepository quizResultRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
     QuizRepository quizRepository;
=======
    private QuizResultRepository quizResultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;
>>>>>>> 8f943c218056e41edbb4576ea421649a609600c8

    public QuizResultResponse saveQuizResult(QuizResultCreateRequestDTO request) throws CustomException {

        QuizResult quizResult = new QuizResult();
        quizResult.setScore(request.getScore());
        quizResult.setIsPassed(request.getIsPassed());
        quizResult.setIsActive(Boolean.TRUE);
        quizResult.setCreatedAt(new Date());

        // user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new CustomException(Constants.USER_ID_NOT_VALID, 404));
        quizResult.setUser(user);

        // quiz
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new CustomException(Constants.QUIZ_ID_NOT_VALID, 404));
        quizResult.setQuiz(quiz);

        // course
<<<<<<< HEAD
        Course course = CourseRepository.findById(request.getCourseId())
=======
        Course course = courseRepository.findById(request.getCourseId())
>>>>>>> 8f943c218056e41edbb4576ea421649a609600c8
                .orElseThrow(() -> new CustomException(Constants.COURSE_ID_NOT_VALID, 404));
        quizResult.setCourse(course);

        // user answers
        //quizResult.setUserAnswers( QuizResultCreateRequestDTO.convertToQuizResult(request.getUserAnswers()) );
        if (request.getUserAnswers() != null) {
            request.getUserAnswers().forEach(answer -> {
                answer.setQuizResult(quizResult);
            });
            quizResult.setUserAnswers(request.getUserAnswers());
        }

        QuizResult savedResult = quizResultRepository.save(quizResult);

        return QuizResultResponse.fromEntity(savedResult);
    }
    // GET BY ID
    public QuizResultResponse getQuizResultById(Integer id) throws CustomException {

        QuizResult existingResult = quizResultRepository.findById(id)
                .orElseThrow(() -> new CustomException("QuizResult not found", 404));

        if (Boolean.TRUE.equals(existingResult.getIsActive())) {
            return QuizResultResponse.fromEntity(existingResult);
        } else {
            throw new CustomException("QuizResult not active", 400);
        }
    }

    //GET ALL
    public List<QuizResultResponse> getAllQuizResults() {

        List<QuizResult> results = quizResultRepository.findAllActiveQuizResults();
        List<QuizResultResponse> responseList = new ArrayList<>();

        for (QuizResult result : results) {
            responseList.add(QuizResultResponse.fromEntity(result));
        }
        return responseList;
    }
<<<<<<< HEAD
=======

    //UPDATE
    public QuizResultResponse updateQuizResult(QuizResultUpdateRequest request)
            throws CustomException {

        QuizResult existingResult = quizResultRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("QuizResult not found" , 404));

        if (!Boolean.TRUE.equals(existingResult.getIsActive())) {
            throw new CustomException("QuizResult is not active" , 400);
        }
        existingResult.setScore(request.getScore());
        existingResult.setIsPassed(request.getIsPassed());
        existingResult.setUpdatedAt(new Date());

        // Update UserAnswers (مثال: استبدال كامل)
        if (request.getUserAnswers() != null && !request.getUserAnswers().isEmpty()) {

            List<UserAnswer> userAnswers = request.getUserAnswers()
                    .stream()
                    .map(dto -> {

                        UserAnswer answer = new UserAnswer();

                        answer.setSelectedOption(dto.getSelectedOption());
                        answer.setIsCorrect(dto.getIsCorrect());

                        // User
                        User user = userRepository.findById(dto.getUserId())
                                .orElseThrow(() -> new CustomException("User not found"));
                        answer.setUser(user);

                        // Quiz
                        Quiz quiz = quizRepository.findById(dto.getQuizId())
                                .orElseThrow(() -> new CustomException("Quiz not found"));
                        answer.setQuiz(quiz);

                        // Question
                        Question question = questionRepository.findById(dto.getQuestionId())
                                .orElseThrow(() -> new CustomException("Question not found"));
                        answer.setQuestion(question);

                        // QuizResult
                        answer.setQuizResult(existingResult);

                        return answer;
                    })
                    .collect(Collectors.toList());

            existingResult.setUserAnswers(userAnswers);
        }

        QuizResult saved = quizResultRepository.save(existingResult);
        return QuizResultResponse.fromEntity(saved);
    }

>>>>>>> 8f943c218056e41edbb4576ea421649a609600c8
    //DELETE (SOFT)
    public void deleteQuizResult(Integer id) throws CustomException {

        QuizResult existingResult = quizResultRepository.findById(id)
                .orElseThrow(() -> new CustomException("QuizResult not found", 404));

        if (Boolean.TRUE.equals(existingResult.getIsActive())) {
            existingResult.setIsActive(false);
            existingResult.setUpdatedAt(new Date());
            quizResultRepository.save(existingResult);
        } else {
            throw new CustomException("QuizResult already inactive", 400);
        }
    }

}
