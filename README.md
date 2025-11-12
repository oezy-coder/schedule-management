# API 명세서

## 1. 일정 생성 API
- URL: POST /schedules
- 설명: 일정 생성
- 인증/인가: 없음
- 데이터 형식: JSON

### Request
```json
{
   "title": "testtitle",
   "nickname": "user1",
   "password": "1234",
   "contents": "test"
}
```

### Response (성공)
```json
{
   "id": 1,
   "title": "testtitle",
   "nickname": "user1",
   "contents": "test",
   "createdAt": "2025-11-10T15:42:46.937858",
   "modifiedAt": "2025-11-10T15:42:46.937858"
}
```

### Response (실패)
```json
{
  "timestamp": "2025-11-10T11:30:18.005+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "비밀번호는 숫자만 입력 가능합니다.",
  "path": "/schedules"
}
```

## 2. 일정 조회 API
- URL: GET /schedules/{scheduleId}
- 설명: 일정 조회
- 인증/인가: 없음
- 데이터 형식: JSON

### Request
```GET /schedules/1```

### Response (성공)
```json
{
   "id": 1,
   "title": "testtitle",
   "nickname": "user1",
   "contents": "test",
   "createdAt": "2025-11-09T13:30:26.937858",
   "modifiedAt": "2025-11-09T13:30:26.937858"
}
```

### Response (실패)
```json
{
  "timestamp": "2025-11-12T06:53:18.839+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "없는 일정입니다.",
  "path": "/schedules/1"
}
```

## 3. 일정 전체 조회 API
- URL: GET /schedules
- 설명: 일정 전체 조회
- 인증/인가: 없음
- 데이터 형식: JSON

### Request
```GET /schedules```

### Response
```json
[
  {
    "id": 2,
    "title": "testtitle2",
    "nickname": "user2",
    "contents": "test",
    "createdAt": "2025-11-10T15:42:46.937858",
    "modifiedAt": "2025-11-10T15:42:46.937858"
  },
  {
    "id": 1,
    "title": "testtitle",
    "nickname": "user1",
    "contents": "test",
    "createdAt": "2025-11-09T13:30:26.937858",
    "modifiedAt": "2025-11-09T13:30:26.937858"
  }
]
```

## 4. 일정 수정 API
- URL: PUT /schedules/{scheduleId}
- 설명: 일정 수정
- 인증/인가: 없음
- 데이터 형식: JSON

### Request
```json
{
  "title": "modifiedtitle",
  "nickname": "user3",
  "password": "1234"
}
```
### Response (성공)
```json
{
   "id": 1,
   "title": "modifiedtitle",
   "nickname": "user3",
   "contents": "test",
   "createdAt": "2025-11-09T13:30:26.937858",
   "modifiedAt": "2025-11-11T09:50:10.937858"
}
```

### Response (실패)
```json
{
  "timestamp": "2025-11-12T06:47:22.454+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "없는 일정입니다.",
  "path": "/schedules/1"
}
```
```json
{
"timestamp": "2025-11-12T06:56:25.545+00:00",
"status": 400,
"error": "Bad Request",
"message": "비밀번호를 다시 입력해주세요.",
"path": "/schedules/2"
}
```

## 5. 일정 삭제 API
- URL: DELETE /schedules/{scheduleId}
- 설명: 일정 삭제
- 인증/인가: 없음
- 데이터 형식: JSON

### Request
```json
{
  "password": "1234"
}
```

### Response (성공)
```json
{
  (없음)
}
```

### Response (실패)
```json
{
  "timestamp": "2025-11-12T06:58:10.669+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "없는 일정입니다.",
  "path": "/schedules/1"
}
```
```json
{
"timestamp": "2025-11-12T06:58:41.793+00:00",
"status": 400,
"error": "Bad Request",
"message": "비밀번호를 다시 입력해주세요.",
"path": "/schedules/2"
}
```

# ERD
![img.png](img.png)