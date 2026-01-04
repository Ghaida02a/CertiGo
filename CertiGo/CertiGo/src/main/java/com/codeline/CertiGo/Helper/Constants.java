package com.codeline.CertiGo.Helper;

public class Constants {
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
    public final  static String USER_ID_NOT_VALID = "User ID is not valid";
    public final static  String USER_ANSWERS_NOT_VALID = "User answers are not valid";

    //User CreateRequest Validations
    public final static String USER_NOT_FOUND = "User not found";
    public final static String USER_USER_NAME_NOT_VALID = "User name is not valid";
    public final static String USER_EMAIL_NOT_VALID = "User email is not valid";
    public final static String USER_PASSWORD_NOT_VALID = "User password is not valid";
    public final static String USER_ROLE_NOT_VALID = "User role is not valid";
    public final static String USER_ENROLLMENTS_NOT_VALID = "User enrollments are not valid";
    public final static String USER_PAYMENTS_NOT_VALID = "User payments are not valid";
    public final static String USER_QUIZ_RESULTS_NOT_VALID = "User quiz results are not valid";
    public final static String USER_USER_ANSWERS_NOT_VALID = "User answers are not valid";
    public final static String USER_CERTIFICATES_NOT_VALID = "User certificates are not valid";
}
