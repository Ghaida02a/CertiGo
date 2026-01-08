# Quiz System Implementation Summary

## Overview
This implementation creates a complete quiz system where questions and options are fetched from the database, displayed as multiple-choice questions, and user answers are saved to the UserAnswer table.

## Backend Changes

### 1. New DTOs Created

#### `OptionResponse.java`
- Simple DTO for returning option data without circular references
- Fields: `id`, `optionText`, `isCorrect`
- Used to send option data to the frontend

#### `QuestionWithOptionsResponse.java`
- DTO for returning questions with their associated options
- Fields: `id`, `questionText`, `options` (List of OptionResponse)
- Used by the quiz page to display questions

#### `UserAnswerBulkCreateRequest.java`
- DTO for saving multiple user answers at once
- Fields: `userId`, `quizId`, `quizResultId`, `answers` (List of UserAnswerItem)
- Inner class `UserAnswerItem` contains: `questionId`, `selectedOptionId`

### 2. Repository Updates

#### `QuestionRepository.java`
- Added method: `findByQuizId(Integer quizId)` - Fetches all active questions for a specific quiz

#### `OptionRepository.java`
- Added method: `findByQuestionId(Integer questionId)` - Fetches all active options for a specific question

### 3. Service Updates

#### `QuestionService.java`
- Added method: `getQuestionsWithOptionsByQuizId(Integer quizId)`
  - Fetches all questions for a quiz
  - For each question, fetches its options
  - Returns a list of QuestionWithOptionsResponse objects

#### `UserAnswerService.java`
- Added method: `saveAnswersBulk(UserAnswerBulkCreateRequest request)`
  - Validates user, quiz, and quiz result
  - Iterates through each answer
  - Validates question and selected option
  - Determines if answer is correct by checking option's `isCorrect` field
  - Saves all answers to the database
  - Returns list of saved answers

### 4. Controller Updates

#### `QuestionController.java`
- Added endpoint: `GET /question/getByQuizId?quizId={id}`
  - Returns all questions with their options for a specific quiz
  - Used by the frontend to load quiz questions

#### `UserAnswerController.java`
- Added endpoint: `POST /UserAnswer/createBulk`
  - Accepts bulk user answers
  - Saves all answers in a single transaction
  - Returns confirmation of saved answers

## Frontend Changes

### `quiz.html` Updates

#### 1. Modified `startQuiz()` function
- Now fetches questions and options from the database via API call
- Endpoint: `GET /question/getByQuizId?quizId={id}`
- Stores questions in `currentQuiz.questions`
- Initializes user answers array

#### 2. Modified `displayQuestion()` function
- Updated to use option IDs instead of array indices
- Radio button values now use actual option IDs from database
- Checks if user has already selected an option and marks it as checked

#### 3. Modified `nextQuestion()` and `prevQuestion()` functions
- Now saves answers as objects: `{questionId, selectedOptionId}`
- Uses actual database IDs instead of array indices

#### 4. Modified `submitQuiz()` function
- Calculates score by checking `isCorrect` property of selected options
- Creates a quiz result via API: `POST /quizResult/create`
- Saves all user answers in bulk via API: `POST /UserAnswer/createBulk`
- Displays results with correct/incorrect answers
- Handles errors gracefully with user-friendly messages

## Data Flow

### Taking a Quiz:
1. User selects a quiz from the list
2. Frontend calls `GET /question/getByQuizId?quizId={id}` to fetch questions and options
3. Questions are displayed one at a time with multiple-choice options
4. User selects answers and navigates through questions
5. User clicks "Submit Quiz"

### Submitting Answers:
1. Frontend calculates the score
2. Frontend calls `POST /quizResult/create` to save the quiz result
3. Frontend calls `POST /UserAnswer/createBulk` with:
   - User ID (from localStorage)
   - Quiz ID
   - Quiz Result ID (from step 2)
   - Array of answers (questionId + selectedOptionId)
4. Backend validates all data and saves to UserAnswer table
5. Frontend displays results with score and answer breakdown

## Database Tables Used

### Question Table
- Stores quiz questions
- Linked to Quiz via `quiz_id`

### Option Table
- Stores answer options for questions
- Linked to Question via `question_id`
- Has `isCorrect` field to mark correct answers

### UserAnswer Table
- Stores user's selected answers
- Fields: `user_id`, `quiz_id`, `question_id`, `quiz_result_id`, `selectedOption`, `isCorrect`
- Automatically determines `isCorrect` based on selected option

### QuizResult Table
- Stores overall quiz results
- Fields: `user_id`, `quiz_id`, `score`, `isPassed`

## API Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/question/getByQuizId?quizId={id}` | Get all questions with options for a quiz |
| POST | `/UserAnswer/createBulk` | Save multiple user answers at once |
| POST | `/quizResult/create` | Create a quiz result record |

## Testing the Implementation

1. **Create Test Data:**
   - Create a quiz
   - Add questions to the quiz
   - Add options to each question (mark correct answers)

2. **Take the Quiz:**
   - Navigate to quiz.html
   - Select a quiz
   - Answer the questions
   - Submit the quiz

3. **Verify Results:**
   - Check that quiz result is saved in QuizResult table
   - Check that user answers are saved in UserAnswer table
   - Verify `isCorrect` field is properly set
   - Confirm score calculation is accurate

## Key Features

✅ Questions and options loaded from database
✅ Multiple-choice question display
✅ Navigation between questions (Previous/Next)
✅ Answer persistence while navigating
✅ Automatic score calculation
✅ Bulk save of user answers
✅ Correct/incorrect answer tracking
✅ User-friendly error handling
✅ Results display with answer breakdown
