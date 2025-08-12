# Auth API (JWT)

Base URL: `/api/v1`

Supported auth methods:
- Email + password (local)
- Google Sign-In (OAuth2 ID token)

User types: `patient`, `partner`, `doctor`

Tokens:
- Access Token (JWT): short-lived (e.g., 15m)
- Refresh Token (JWT or opaque): long-lived (e.g., 7d)

HTTP headers:
- `Content-Type: application/json`
- `Authorization: Bearer <accessToken>` (for protected endpoints)

Standard error shape:
```json
{
  "error": {
    "code": "INVALID_CREDENTIALS",
    "message": "Email or password is incorrect"
  }
}
```

JWT claim (example):
```json
{
  "sub": "<user_uuid>",
  "email": "user@example.com",
  "userType": "patient",
  "provider": "local|google",
  "iat": 1733745600,
  "exp": 1733746500
}
```

---

## 1) Register (email + password) // Done
POST `/auth/register`

Requests (match DB columns exactly)

Patient (`pregnents` row will be created)
```json
{
  "email": "user@example.com",
  "password": "Passw0rd!",
  "userType": "patient",
  "profile": {
    "first_name": "Jane",
    "last_name": "Doe",
    "phone": "+8801...",
    "date_of_birth": "1990-01-01",
    "profile_image_url": "https://...",
    "address": "Street, City",
    "emergency_contact_name": "John Doe",
    "emergency_contact_phone": "+8801...",
    "blood_group": "O+",
    "height": 160.5,
    "pre_pregnancy_weight": 58.2
  }
}
```

Partner (`partner` row will be created; link to an existing patient using `partner_id` = `pregnents.id`)
```json
{
  "email": "partner@example.com",
  "password": "Passw0rd!",
  "userType": "partner",
  "profile": {
    "first_name": "Mark",
    "last_name": "Doe",
    "phone": "+8801...",
    "date_of_birth": "1990-02-02",
    "relationship_to_mother": "husband",
    "partner_id": "<pregnent_uuid>"
  }
}
```

Doctor (`doctors` row will be created)
```json
{
  "email": "doc@example.com",
  "password": "Passw0rd!",
  "userType": "doctor",
  "profile": {
    "first_name": "Aisha",
    "last_name": "Rahman",
    "phone": "+8801...",
    "date_of_birth": "1985-03-03",
    "profile_image_url": "https://...",
    "address": "Clinic address",
    "specialization": "Obstetrics",
    "qualification": ["MBBS", "FCPS"],
    "experience_years": 10,
    "license_number": "LIC-12345",
    "hospital_affiliation": "City Hospital",
    "consultation_fee": 1500.00
  }
}
```

Response 201
```json
{
  "user": {
    "id": "7f6e...",
    "email": "user@example.com",
    "userType": "patient",
    "authProvider": "local",
    "emailVerified": false,
    "isActive": true,
    "accountStatus": "pending",
    "lastLogin": null
  },
  "accessToken": "<jwt>",
  "refreshToken": "<refresh>"
}
```

Notes
- Insert into `users` with `auth_provider='local'`, `user_type`, `password_hash` (bcrypt/argon2), `email_verified=false`.
- Create the corresponding profile row in `pregnents` | `partner` | `doctors` using the provided `profile` fields (names match table columns exactly). Server generates UUIDs.

Errors
- 400 INVALID_INPUT, 409 EMAIL_EXISTS, 404 PARTNER_NOT_FOUND (when partner_id does not match an existing `pregnents.id`)

---

## 2) Login (email + password) // Done
POST `/auth/login`

Request
```json
{ "email": "user@example.com", "password": "Passw0rd!" }
```

Response 200
```json
{
  "user": {
    "id": "7f6e...",
    "email": "user@example.com",
    "userType": "patient",
    "authProvider": "local",
    "emailVerified": false,
    "isActive": true,
    "accountStatus": "active",
    "lastLogin": "2025-08-12T09:00:00Z"
  },
  "accessToken": "<jwt>",
  "refreshToken": "<refresh>"
}
```

Errors
- 401 INVALID_CREDENTIALS, 403 ACCOUNT_SUSPENDED, 423 ACCOUNT_PENDING

---

## 3) Google Login / Signup (ID token)
POST `/auth/google`

Description
- Client sends Google `id_token`. Server verifies and uses `sub` as `google_id` and email.
- If first time, create `users` with `auth_provider='google'`, `google_id`, `password_hash=NULL`, and a profile row based on `userType` with the same column names as above.

Requests

First-time Patient (creates `users` + `pregnents`)
```json
{
  "idToken": "<google_id_token>",
  "userType": "patient",
  "profile": {
    "first_name": "Jane",
    "last_name": "Doe",
    "phone": "+8801...",
    "date_of_birth": "1990-01-01",
    "profile_image_url": "https://...",
    "address": "Street, City",
    "emergency_contact_name": "John Doe",
    "emergency_contact_phone": "+8801...",
    "blood_group": "O+",
    "height": 160.5,
    "pre_pregnancy_weight": 58.2
  }
}
```

First-time Partner (creates `users` + `partner`)
```json
{
  "idToken": "<google_id_token>",
  "userType": "partner",
  "profile": {
    "first_name": "Mark",
    "last_name": "Doe",
    "phone": "+8801...",
    "date_of_birth": "1990-02-02",
    "relationship_to_mother": "husband",
    "partner_id": "<pregnent_uuid>"
  }
}
```

First-time Doctor (creates `users` + `doctors`)
```json
{
  "idToken": "<google_id_token>",
  "userType": "doctor",
  "profile": {
    "first_name": "Aisha",
    "last_name": "Rahman",
    "phone": "+8801...",
    "date_of_birth": "1985-03-03",
    "profile_image_url": "https://...",
    "address": "Clinic address",
    "specialization": "Obstetrics",
    "qualification": ["MBBS", "FCPS"],
    "experience_years": 10,
    "license_number": "LIC-12345",
    "hospital_affiliation": "City Hospital",
    "consultation_fee": 1500.00
  }
}
```

Existing Google user (no profile needed)
```json
{ "idToken": "<google_id_token>" }
```

Response 200
```json
{
  "user": {
    "id": "7f6e...",
    "email": "user@gmail.com",
    "userType": "patient",
    "authProvider": "google",
    "emailVerified": true,
    "isActive": true,
    "accountStatus": "active",
    "lastLogin": "2025-08-12T09:00:00Z"
  },
  "accessToken": "<jwt>",
  "refreshToken": "<refresh>"
}
```

Errors
- 400 INVALID_GOOGLE_TOKEN, 400 MISSING_USER_TYPE (on first signup), 404 PARTNER_NOT_FOUND

---

## 4) Forgot Password (request reset)
POST `/auth/password/forgot`

Request
```json
{ "email": "user@example.com" }
```

Response 200
```json
{ "message": "If the account exists, a reset link has been sent" }
```

Notes
- Insert into `password_reset_tokens` with `user_id`, `token` (unique), `expires_at`, `is_used=false`.

---

## 5) Reset Password (use token)
POST `/auth/password/reset`

Request
```json
{
  "token": "<reset_token>",
  "newPassword": "NewPassw0rd!"
}
```

Response 200
```json
{ "message": "Password reset successful" }
```

Notes
- Validate `password_reset_tokens.token`, check `expires_at`, `is_used`.
- On success: set `is_used=true`, `used_at=now()`, and update `users.password_hash`.

Errors
- 400 INVALID_TOKEN, 410 TOKEN_EXPIRED, 409 TOKEN_USED

---

## 6) Refresh Access Token
POST `/auth/token/refresh`

Request
```json
{ "refreshToken": "<refresh>" }
```

Response 200
```json
{ "accessToken": "<new_jwt>" }
```

Errors
- 401 INVALID_REFRESH_TOKEN

---

## 7) Logout
POST `/auth/logout`

Request
```json
{ "refreshToken": "<refresh>" }
```

Response 204

Notes
- Invalidate/revoke the provided refresh token.

---

## 8) Get Current User
GET `/auth/me`

Headers: `Authorization: Bearer <accessToken>`

Response 200
```json
{
  "id": "7f6e...",
  "email": "user@example.com",
  "userType": "patient",
  "authProvider": "local",
  "emailVerified": false,
  "isActive": true,
  "accountStatus": "active",
  "lastLogin": "2025-08-12T09:00:00Z"
}
```

---

## Validation (from SQL)
- `users.auth_provider`: one of `local`, `google`
- `users.user_type`: `patient`, `partner`, or `doctor`
- `pregnents.first_name`, `pregnents.last_name`: required
- `partner.first_name`, `partner.last_name`: required; `partner.partner_id` must reference an existing `pregnents.id`
- `doctors.first_name`, `doctors.last_name`: required
- Types and formats follow the table definitions exactly

---

## Minimal cURL examples

Register patient
```bash
curl -X POST https://api.example.com/api/v1/auth/register \
  -H 'Content-Type: application/json' \
  -d '{
    "email":"user@example.com",
    "password":"Passw0rd!",
    "userType":"patient",
    "profile":{
      "first_name":"Jane",
      "last_name":"Doe"
    }
  }'
```

Login
```bash
curl -X POST https://api.example.com/api/v1/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"email":"user@example.com","password":"Passw0rd!"}'
```

Google (first-time doctor)
```bash
curl -X POST https://api.example.com/api/v1/auth/google \
  -H 'Content-Type: application/json' \
  -d '{
    "idToken":"<google_id_token>",
    "userType":"doctor",
    "profile": {"first_name":"Aisha","last_name":"Rahman"}
  }'
```

Forgot password
```bash
curl -X POST https://api.example.com/api/v1/auth/password/forgot \
  -H 'Content-Type: application/json' \
  -d '{"email":"user@example.com"}'
```

Reset password
```bash
curl -X POST https://api.example.com/api/v1/auth/password/reset \
  -H 'Content-Type: application/json' \
  -d '{"token":"<reset_token>","newPassword":"NewPassw0rd!"}'
```

Refresh token
```bash
curl -X POST https://api.example.com/api/v1/auth/token/refresh \
  -H 'Content-Type: application/json' \
  -d '{"refreshToken":"<refresh>"}'
```
