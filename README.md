
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
- Create Question -> http://localhost:8080/api/admin/questions
- Update Question -> http://localhost:8080/api/admin/question/{questionId}
- Delete Question -> http://localhost:8080/api/admin/question/{questionId}
- Get Answer for a question -> http://localhost:8080/api/public/answer/{questionId}
- Validate answer -> http://localhost:8080/api/admin/validate/answer
