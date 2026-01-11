, # Quiz System - Multiple Choice Questions

## Overview
The quiz system displays multiple-choice questions with options fetched from the database (Option table). Users select their answers from radio buttons, and the selected answers are saved to the UserAnswer table in the database.

## Implementation Details

### Frontend (quiz.html)

#### 1. `displayQuestion()` function
- Displays questions with multiple-choice options from the database
- Each option is rendered as a radio button with the option text from the Option table
- Previously selected answers are restored when navigating back to a question
- Uses option IDs from the database (not array indices)

```javascript
if (question.options && question.options.length > 0) {
    // Display multiple choice options from database
    question.options.forEach((option, index) => {
        const optionDiv = document.createElement('div');
        optionDiv.className = 'quiz-option';
        const isChecked = userAnswers[currentQuestionIndex] && userAnswers[currentQuestionIndex].selectedOptionId === option.id;
        optionDiv.innerHTML = `
            <input type="radio" id="option${option.id}" name="quizOption" value="${option.id}" ${isChecked ? 'checked' : ''}>
            <label for="option${option.id}">${escapeHtml(option.optionText)}</label>
        `;
        optionsContainer.appendChild(optionDiv);
    });
}
```

#### 2. `nextQuestion()` and `prevQuestion()` functions
- Captures the selected radio button option
- Saves the question ID and selected option ID
- Preserves user selections when navigating between questions

```javascript
const selectedOption = document.querySelector('input[name="quizOption"]:checked');
const question = currentQuiz.questions[currentQuestionIndex];

if (selectedOption) {
    userAnswers[currentQuestionIndex] = {
        questionId: question.id,
        selectedOptionId: parseInt(selectedOption.value)
    };
}
```

#### 3. `submitQuiz()` function
- Calculates score by checking the `isCorrect` property of selected options
- Creates a quiz result via API
- Saves all user answers in bulk to the database
- Displays results showing selected answers vs correct answers

```javascript
const selectedOption = question.options.find(opt => opt.id === userAnswer.selectedOptionId);
const correctOption = question.options.find(opt => opt.isCorrect);
const isCorrect = selectedOption && selectedOption.isCorrect;
```

### Backend Implementation

#### 1. `UserAnswerBulkCreateRequest.java`
DTO for receiving multiple user answers in a single request:

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public static class UserAnswerItem {
    private Integer questionId;
    private Integer selectedOptionId;
}
```

#### 2. `UserAnswerService.java` - `saveAnswersBulk()` method
Processes and saves user answers:

```java
// Validate question
Question question = questionRepository.getQuestionById(answerItem.getQuestionId());

// Validate option
Option selectedOption = optionRepository.getOptionById(answerItem.getSelectedOptionId());

// Set answer properties
answer.setSelectedOption(selectedOption.getOptionText());
answer.setIsCorrect(selectedOption.getIsCorrect());  // Auto-graded
```

## How It Works

### User Experience:
1. User selects a quiz from the available quizzes
2. Questions and options are fetched from the database via API
3. User views one question at a time with multiple-choice options
4. User selects an answer by clicking a radio button
5. User navigates through questions using Previous/Next buttons
6. User submits the quiz when finished

### Data Flow:
1. **Load Quiz**: `GET /question/getByQuizId?quizId={id}` fetches questions with options
2. **Display Options**: Options from the Option table are rendered as radio buttons
3. **Save Answers**: Selected option IDs are stored temporarily in the frontend
4. **Submit Quiz**:
   - Quiz result is created: `POST /quizResult/create`
   - All answers are saved in bulk: `POST /UserAnswer/createBulk`
5. **Auto-Grading**: System checks `isCorrect` field of selected options
6. **Display Results**: Shows score and answer breakdown

### Grading Logic:
- **Automatic Grading**: Based on the `isCorrect` property of the selected option from the Option table
- **Score Calculation**: (Correct Answers / Total Questions) × 100
- **Pass/Fail**: Determined by comparing score to quiz passing threshold

## Database Schema

### Question Table
```
Question {
    id: Integer
    questionText: String
    quiz: Quiz (FK)
    isActive: Boolean
    createdAt: Date
    updatedAt: Date
}
```

### Option Table
```
Option {
    id: Integer
    optionText: String
    isCorrect: Boolean      // Marks the correct answer
    question: Question (FK)
    isActive: Boolean
    createdAt: Date
    updatedAt: Date
}
```

### UserAnswer Table
```
UserAnswer {
    id: Integer
    selectedOption: String  // Stores the text of the selected option
    isCorrect: Boolean      // Automatically set based on selected option
    user: User (FK)
    quiz: Quiz (FK)
    question: Question (FK)
    quizResult: QuizResult (FK)
    isActive: Boolean
    createdAt: Date
    updatedAt: Date
}
```

## API Endpoints

### 1. Get Questions with Options
```
GET /question/getByQuizId?quizId={id}
```

**Response:**
```json
[
    {
        "id": 1,
        "questionText": "What is the capital of France?",
        "options": [
            {"id": 1, "optionText": "London", "isCorrect": false},
            {"id": 2, "optionText": "Paris", "isCorrect": true},
            {"id": 3, "optionText": "Berlin", "isCorrect": false},
            {"id": 4, "optionText": "Madrid", "isCorrect": false}
        ]
    }
]
```

### 2. Save User Answers
```
POST /UserAnswer/createBulk
```

**Request:**
```json
{
    "userId": 1,
    "quizId": 5,
    "quizResultId": 10,
    "answers": [
        {
            "questionId": 1,
            "selectedOptionId": 2
        },
        {
            "questionId": 2,
            "selectedOptionId": 7
        }
    ]
}
```

## Key Features

✅ **Database-Driven**: Questions and options loaded from database tables
✅ **Multiple Choice**: Clean radio button interface for answer selection
✅ **Auto-Grading**: Automatic scoring based on correct options
✅ **Navigation**: Previous/Next buttons to move between questions
✅ **Answer Persistence**: Selected answers are preserved when navigating
✅ **Bulk Save**: All answers saved in a single API call
✅ **Results Display**: Shows score, correct/incorrect answers, and explanations
✅ **User-Friendly**: Intuitive interface with clear feedback

## Future Enhancements

1. **Question Types**: Support for true/false, multiple select, matching questions
2. **Time Limits**: Add countdown timer for timed quizzes
3. **Question Randomization**: Shuffle questions and options for each attempt
4. **Review Mode**: Allow users to review their answers after submission
5. **Partial Credit**: Support for questions with multiple correct answers
6. **Question Bank**: Random selection of questions from a larger pool
7. **Explanations**: Show explanations for correct/incorrect answers
8. **Progress Indicator**: Visual progress bar showing quiz completion

## Testing Checklist

1. **Database Setup**: Create quiz with questions and options (mark correct answers)
2. **Load Quiz**: Verify questions and options load from database
3. **Select Answers**: Test radio button selection for each question
4. **Navigation**: Navigate back/forth, verify selections persist
5. **Submit Quiz**: Submit and verify answers save to UserAnswer table
6. **Grading**: Verify `isCorrect` field is set correctly based on selected options
7. **Results**: Check score calculation and results display
8. **Edge Cases**: Test with no answer selected, all correct, all incorrect
