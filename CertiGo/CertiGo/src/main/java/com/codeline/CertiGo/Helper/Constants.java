package com.codeline.CertiGo.Helper;

public class Constants {

    public static final String BAD_REQUEST =
            "BAD REQUEST";

    public static final String SUCCESS =
            "SUCCESS";

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

}
