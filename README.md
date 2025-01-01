
# API ENDPOINTS

---

# 1. Get All Questions

``` GET /public/questions ```

## Description

Fetches a list of questions with options for pagination and sorting.



## Request Parameters

| Parameter    | Type    | Default   | Required | Description                                       |
|--------------|---------|-----------|----------|---------------------------------------------------|
| `pageNumber` | Integer | `0`       | No       | The page number to retrieve (0-based indexing).   |
| `pageSize`   | Integer | `20`      | No       | The number of questions per page.                |
| `sortBy`     | String  | `questionId` | No    | The field used to sort the questions.            |
| `sortOrder`  | String  | `asc`     | No       | The sort direction: `asc` for ascending or `desc` for descending order. |



## Response

### Status Code
- **200 OK**

### Response Body
A JSON object containing:
- The list of questions.
- Pagination metadata such as the current page, total pages, and total number of questions.

<details>
  <summary>Click to view JSON response</summary>

<pre>
    <code>
{
    "content": [
        {
            "questionId": 1,
            "title": "What is the atomic number of Hydrogen?",
            "description": "Choose the correct answer.",
            "author": "Kushidhar",
            "status": "ACTIVE",
            "subject": "CHEMISTRY",
            "difficulty": "EASY",
            "options": [
                {
                    "optionId": 1,
                    "text": "1"
                },
                {
                    "optionId": 2,
                    "text": "2"
                },
                {
                    "optionId": 3,
                    "text": "3"
                },
                {
                    "optionId": 4,
                    "text": "4"
                }
            ],
            "tagList": [
                {
                    "tagId": 3,
                    "text": "atomic_number"
                },
                {
                    "tagId": 2,
                    "text": "hydrogen"
                },
                {
                    "tagId": 1,
                    "text": "periodic_table"
                }
            ],
            "correctOptionId": null
        }
    ],
    "pageNumber": 0,
    "pageSize": 2,
    "totalElements": 1,
    "totalPages": 1,
    "lastPage": true
}
 </code>
  </pre>
</details>

---

## 2. Get Question by ID

```GET /public/question/{questionId}```

**Description:**

This API retrieves a question by its `questionId`. 
If the question exists, it returns the details of the question.
If not, a 404 error with a message is returned.


#### Path Parameter
- `questionId` (required): The unique identifier for the question.

<details>
  <summary>Success JSON Response (HTTP 200) </summary>
<pre>
    <code>
  {
    "questionId": 1,
    "title": "What is the atomic number of Hydrogen?",
    "description": "Choose the correct answer.",
    "author": "Kushidhar",
    "status": "ACTIVE",
    "subject": "CHEMISTRY",
    "difficulty": "EASY",
    "options": [
      { "optionId": 1, "text": "1" },
      { "optionId": 2, "text": "2" },
      { "optionId": 3, "text": "3" },
      { "optionId": 4, "text": "4" }
    ],
    "tagList": [
      { "tagId": 1, "text": "periodic_table" },
      { "tagId": 3, "text": "atomic_number" },
      { "tagId": 2, "text": "hydrogen" }
    ],
    "correctOptionId": null
  }
 </code>
  </pre>
</details>

<details>
  <summary>Error Response (HTTP 404) JSON</summary>
<pre>
    <code>
{
  "message": "Question not found with questionId: 11",
  "status": false
}
 </code>
  </pre>
</details>

---

## 3. Create Question API

`POST /api/admin/questions`

### Description
Creates a new question with the provided details, including options and tags.

<details>
<summary>Request JSON Payload </summary>
<pre>
    <code>
{
    "title": "What is the atomic number of Hydrogen?",
    "description": "Choose the correct answer.",
    "subject": "CHEMISTRY",
    "difficulty": "EASY",
    "status": "ACTIVE",
    "author": "Kushidhar",
    "options": [
        {
            "text": "1"
        },
        {
            "text": "2"
        },
        {
            "text": "3"
        },
        {
            "text": "4"
        }
    ],
    "tagList": [
        {
            "text": "atomic_number"
        },
        {
            "text": "hydrogen"
        },
        {
            "text": "periodic_table"
        }
    ],
    "correctOptionId": 1
}
 </code>
  </pre>
</details>

<details>
  <summary>Success JSON Response (HTTP 201 Created) </summary>
<pre>
    <code>
{
    "questionId": 1,
    "title": "What is the atomic number of Hydrogen?",
    "description": "Choose the correct answer.",
    "author": "Kushidhar",
    "status": "ACTIVE",
    "subject": "CHEMISTRY",
    "difficulty": "EASY",
    "options": [
        {
            "optionId": 1,
            "text": "1"
        },
        {
            "optionId": 2,
            "text": "2"
        },
        {
            "optionId": 3,
            "text": "3"
        },
        {
            "optionId": 4,
            "text": "4"
        }
    ],
    "tagList": [
        {
            "tagId": 1,
            "text": "atomic_number"
        },
        {
            "tagId": 2,
            "text": "hydrogen"
        },
        {
            "tagId": 3,
            "text": "periodic_table"
        }
    ],
    "correctOptionId": 1
}
 </code>
  </pre>
</details>


**Validation and Error Handling**

The following validation rules must be adhered to when creating a question:
- **Title**:
    - **Required**: Yes
    - **Constraints**:
        - Minimum 3 characters
        - Maximum 50 characters
    - **Validation Message**: `"must contain at-least 3 characters & at-max 50 characters"`
    - Must be unique for every question. 
  
- **Description**:
    - **Required**: Yes
    - **Constraints**:
        - Minimum 6 characters
        - Maximum 1000 characters
    - **Validation Message**: `"must contain at-least 6 characters & at-max 1000 characters"`

- **Author**:
    - **Required**: No

- **Status**:
    - **Required**: Yes
    - **Allowed Values**: Enum `["ACTIVE", "INACTIVE"]`
    - **Validation Message**: `"Status must not be null"`

- **Subject**:
    - **Required**: Yes
    - **Allowed Values**: Enum `["CHEMISTRY", "PHYSICS", "MATH", "BIOLOGY"]`
    - **Validation Message**: `"Subject must not be null"`

- **Difficulty**:
    - **Required**: Yes
    - **Allowed Values**: Enum `["EASY", "MEDIUM", "HARD"]`
    - **Validation Message**: `"Difficulty must not be null"`
[//]: # (Recheck the options docs again)
- **Options ⛳️**:
    - **Required**: Yes
    - **Constraints**:
        - Exactly 4 options
        - Each option must have a `text` field

- **Tags (tagList)**:
    - **Required**: No
    - **Details**: A set of tags describing the question
    - Creates a new tag if it doesn't exist.

- **Correct Option ID**:
    - **Required**: Yes
    - **Constraints**:
        - Must be between 1 and 4
    - **Validation Message**: `"CorrectOptionId must be between 1 and 4"`

### Error Responses
All possible error responses for this API are listed below:

1. **Validation Errors**:
    - **Status Code**: `400 Bad Request`
    - **Response Body**:
        ```json
        {
            "title": "must contain at-least 3 characters & at-max 25 characters"
        }
       ```

2. **Duplicate Question Title**:
    - **Status Code**: `400 Bad Request`
    - **Response Body**:
      ```json
      {
          "message": "Question with title 'What is the atomic number of Hydrogen?' already exists!!!",
          "status": false
      }
      ```

3. **Invalid Enum Value**:
    - **Status Code**: `400 Bad Request`
    - **Response Body**:
      ```json
      {
          "message": "Invalid value for 'status'. Allowed values are ['ACTIVE', 'INACTIVE'].",
          "status": false
      }
      ```

4. **Missing Required Fields**:
    - **Status Code**: `400 Bad Request`
    - **Response Body**:
      ```json
      {
          "message": "Subject must not be null."
      }
      ```

### Note
- The validation rules are enforced on both the backend model (`Question`) and the Data Transfer Object (`QuestionDTO`).
- Any unexpected validation or persistence errors will return a generic `500 Internal Server Error` with details in the logs for debugging.

---
- Create Question -> http://localhost:8080/api/admin/questions
- Update Question -> http://localhost:8080/api/admin/question/{questionId}
- Delete Question -> http://localhost:8080/api/admin/question/{questionId}
- Get Answer for a question -> http://localhost:8080/api/public/answer/{questionId}
- Validate answer -> http://localhost:8080/api/admin/validate/answer
