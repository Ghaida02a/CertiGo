package com.codeline.CertiGo.Helper;

public class Constants {

    public static final String REVIEW_CREATE_REQUEST_NAME_NOT_VALID =
            "Reviewer name is not valid";

    public static final String REVIEW_CREATE_REQUEST_COMMENTS_NOT_VALID =
            "Review comments are not valid";

    public static final String REVIEW_CREATE_REQUEST_RATING_NOT_VALID =
            "Rating is not valid";

    public static final String REVIEW_CREATE_REQUEST_COURSE_ID_NOT_VALID =
            "Course ID is not valid";

    public static final String REVIEW_CREATE_REQUEST_USER_ID_NOT_VALID =
            "User ID is not valid";


    public static final String INSTRUCTOR_CREATE_REQUEST_NAME_NOT_VALID =
            "Instructor name must not be null or blank";

    public static final String INSTRUCTOR_CREATE_REQUEST_BIO_NOT_VALID =
            "Instructor bio must not be null or blank";

    public static final String INSTRUCTOR_CREATE_REQUEST_EMAIL_NOT_VALID =
            "Instructor email is not valid";

    public static final String INSTRUCTOR_CREATE_REQUEST_COURSES_NOT_VALID =
            "Instructor courses list is not valid";


    public static final String USER_ANSWER_CREATE_REQUEST_SELECTED_OPTION_NOT_VALID =
            "Selected option is not valid";

    public static final String USER_ANSWER_CREATE_REQUEST_IS_CORRECT_NOT_VALID =
            "Answer correctness flag is not valid";

    public static final String USER_ANSWER_USER_ID_NOT_VALID =
            "User ID is not valid";

    public static final String USER_ANSWER_QUIZ_ID_NOT_VALID =
            "Quiz ID is not valid";

    public static final String USER_ANSWER_QUESTION_ID_NOT_VALID =
            "Question ID is not valid";

    public static final String USER_ANSWER_QUIZ_RESULT_ID_NOT_VALID =
            "Quiz result ID is not valid";


    public static final String OPTION_CREATE_REQUEST_OPTION_TEXT_NOT_VALID =
            "Option text must not be null or blank";

    public static final String OPTION_CREATE_REQUEST_IS_CORRECT_NOT_VALID =
            "Option correctness flag must not be null";

    public static final String OPTION_CREATE_REQUEST_QUESTION_ID_NOT_VALID =
            "Question ID is not valid";


    // HTTP Status Codes
    public static final int HTTP_STATUS_IS_NULL = 422;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    // Response Messages and Status Codes for Company Validations
    public final static String SUCCESS = "Success";
    public final static String BAD_REQUEST = "BAD REQUEST";
    public final static String COMPANY_NAME_NOT_VALID = "Company name is not valid";
    public final static String LOCATION_NOT_VALID = "Location is not valid";
    public final static String INDUSTRY_NOT_VALID = "Industry is not valid";
    public final static String CONTACT_EMAIL_NOT_VALID = "Contact email is not valid";
    public final static String COURSE_LIST_NOT_VALID = "Course list is not valid";
    public final static int HTTP_STATUS_SUCCESS = 200;
    public final static int HTTP_STATUS_BAD_REQUEST = 400;
    public final static int HTTP_STATUS_IS_NULL = 422;
    public final static int HTTP_STATUS_NOT_FOUND = 404;

    //Response Messages and Status Codes for Enrollment Validations
    public final static String ENROLLMENT_NOT_FOUND = "Enrollment not found";
    public final static String ENROLLMENT_STATUS_NOT_VALID = "Enrollment status is not valid";
    public final static String USERNAME_NOT_VALID = "Username is not valid";
    public final static String COURSE_ID_NOT_VALID = "Course ID is not valid";

    //Response Messages Question Validations
    public final static String OPTION_NOT_FOUND = "Option not found";
    public final static String QUESTION_TEXT_CANNOT_BE_EMPTY = "Question text cannot be empty";
    public final static String CORRECT_ANSWER_NOT_VALID = "Correct answer is not valid";
    public final static String QUIZ_ID_NOT_VALID = "Quiz ID is not valid";
    public final static String USER_ANSWER_NOT_VALID = "User answer is not valid";
    public final static String QUESTION_NOT_FOUND = "Question not found";
    public final static String QUESTION_TEXT_NOT_VALID = "Question text is not valid";
    public final static String QUESTION_TYPE_NOT_VALID = "Question type is not valid";

    //Response Messages Quiz Result Validations
    public final static String SCORE_NOT_VALID = "Score is not valid";
    public final static String IS_PASSED_NOT_VALID = "isPassed is not valid";
    public final static String USER_ID_NOT_VALID = "User ID is not valid";
    public final static String USER_ANSWERS_NOT_VALID = "User answers are not valid";


    public final static Integer LOWER_DURATION_RANGE = 0;
    public final static Integer UPPER_DURATION_RANGE = 300;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    //Course
    public final static String COURSE_CREATE_REQUEST_COURSE_NAME_NOT_VALID = "NAME IS NOT VALID";
    public final static String COURSE_CREATE_REQUEST_TYPE_NOT_VALID = "TYPE IS NOT VALID";
    public final static String COURSE_CREATE_REQUEST_DURATION_NOT_VALID = "DURATION IS NOT VALID";
    public final static String COURSE_CREATE_REQUEST_PRICE_NOT_VALID = "PRICE IS NOT VALID";
    public final static String COURSE_CREATE_REQUEST_IS_COMPLETED_NOT_VALID = "IS COMPLETED IS NOT VALID";
    public final static String COURSE_CREATE_REQUEST_COMPANY_ID_NOT_VALID = "COMPANY ID IS NOT VALID";
    public final static String COURSE_CREATE_REQUEST_INSTRUCTORS_ID_NOT_VALID = "INSTRUCTORS ID IS NOT VALID";

    //Quiz
    public final static String QUIZ_CREATE_REQUEST_TOTAL_QUESTIONS_NOT_VALID = "TOTAL QUESTIONS IS NOT VALID";
    public final static String QUIZ_CREATE_REQUEST_PASSING_SCORE_NOT_VALID = "PASSING SCORE IS NOT VALID";
    public final static String QUIZ_CREATE_REQUEST_COURSE_ID_NOT_VALID = "COURSE ID IS NOT VALID";
    public final static String QUIZ_CREATE_REQUEST_QUESTIONS_ID_NOT_VALID = "QUESTIONS ID IS NOT VALID";

    //Lesson
    public final static String LESSON_CREATE_REQUEST_ID_NOT_VALID = " ID IS NOT VALID";
    public final static String LESSON_CREATE_REQUEST_LESSON_NAME_NOT_VALID = "LESSON NAME IS NOT VALID";
    public final static String LESSON_CREATE_REQUEST_PDF_URL_NOT_VALID = "PDF URL IS NOT VALID";
    public final static String LESSON_CREATE_REQUEST_COURSE_ID_NOT_VALID = "COURSE ID IS NOT VALID";
}

