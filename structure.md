dhatree-pregnancy-tracker/
├── README.md
├── .gitignore
├── .env
├── docker-compose.yml
├── services/
│   ├── user-service/
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/dhatree/userservice/
│   │   │   │   │       ├── UserServiceApplication.java
│   │   │   │   │       ├── config/
│   │   │   │   │       │   ├── DatabaseConfig.java
│   │   │   │   │       │   ├── SecurityConfig.java
│   │   │   │   │       │   ├── JwtConfig.java
│   │   │   │   │       │   └── RedisConfig.java
│   │   │   │   │       ├── controller/
│   │   │   │   │       │   ├── AuthController.java
│   │   │   │   │       │   ├── UserController.java
│   │   │   │   │       │   └── ProfileController.java
│   │   │   │   │       ├── service/ -
│   │   │   │   │       │   ├── AuthService.java
│   │   │   │   │       │   ├── UserService.java
│   │   │   │   │       │   ├── TokenService.java
│   │   │   │   │       │   └── ProfileService.java
│   │   │   │   │       ├── repository/ -
│   │   │   │   │       │   ├── UserRepository.java
│   │   │   │   │       │   ├── TokenRepository.java
│   │   │   │   │       │   └── ProfileRepository.java
│   │   │   │   │       ├── model/ -
│   │   │   │   │       │   ├── User.java
│   │   │   │   │       │   ├── UserProfile.java
│   │   │   │   │       │   └── AuthToken.java
│   │   │   │   │       ├── dto/ -
│   │   │   │   │       │   ├── request/
│   │   │   │   │       │   │   ├── LoginRequest.java
│   │   │   │   │       │   │   ├── SignupRequest.java
│   │   │   │   │       │   │   └── UpdateProfileRequest.java
│   │   │   │   │       │   └── response/
│   │   │   │   │       │       ├── AuthResponse.java
│   │   │   │   │       │       ├── UserResponse.java
│   │   │   │   │       │       └── ProfileResponse.java
│   │   │   │   │       ├── exception/
│   │   │   │   │       │   ├── GlobalExceptionHandler.java
│   │   │   │   │       │   ├── UserNotFoundException.java
│   │   │   │   │       │   └── AuthenticationException.java
│   │   │   │   │       └── util/
│   │   │   │   │           ├── JwtUtil.java
│   │   │   │   │           ├── PasswordUtil.java
│   │   │   │   │           └── ValidationUtil.java
│   │   │   │   └── resources/
│   │   │   │       ├── application.yml
│   │   │   │       ├── application-dev.yml
│   │   │   │       └── application-prod.yml
│   │   │   └── test/
│   │   │       └── java/
│   │   │           └── com/dhatree/userservice/
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               └── integration/
│   │   ├── target/
│   │   └── logs/
│   ├── community-service/
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/dhatree/communityservice/
│   │   │   │   │       ├── CommunityServiceApplication.java
│   │   │   │   │       ├── config/
│   │   │   │   │       ├── controller/
│   │   │   │   │       │   ├── ForumController.java
│   │   │   │   │       │   ├── GroupController.java
│   │   │   │   │       │   └── MessageController.java
│   │   │   │   │       ├── service/
│   │   │   │   │       ├── repository/
│   │   │   │   │       ├── model/
│   │   │   │   │       │   ├── Forum.java
│   │   │   │   │       │   ├── Group.java
│   │   │   │   │       │   ├── Message.java
│   │   │   │   │       │   └── Post.java
│   │   │   │   │       ├── dto/
│   │   │   │   │       ├── exception/
│   │   │   │   │       └── util/
│   │   │   │   └── resources/
│   │   │   └── test/
│   │   ├── target/
│   │   └── logs/
│   ├── pregnancy-service/
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/dhatree/pregnancyservice/
│   │   │   │   │       ├── PregnancyServiceApplication.java
│   │   │   │   │       ├── config/
│   │   │   │   │       ├── controller/
│   │   │   │   │       │   ├── PregnancyController.java
│   │   │   │   │       │   ├── CalculatorController.java
│   │   │   │   │       │   ├── WeeklyUpdateController.java
│   │   │   │   │       │   └── DietController.java
│   │   │   │   │       ├── service/
│   │   │   │   │       ├── repository/
│   │   │   │   │       ├── model/
│   │   │   │   │       │   ├── Pregnancy.java
│   │   │   │   │       │   ├── WeeklyUpdate.java
│   │   │   │   │       │   ├── DietPlan.java
│   │   │   │   │       │   └── WellnessTip.java
│   │   │   │   │       ├── dto/
│   │   │   │   │       ├── exception/
│   │   │   │   │       └── util/
│   │   │   │   └── resources/
│   │   │   └── test/
│   │   ├── target/
│   │   └── logs/
│   ├── ai-service/
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/dhatree/aiservice/
│   │   │   │   │       ├── AiServiceApplication.java
│   │   │   │   │       ├── config/
│   │   │   │   │       │   ├── OpenAIConfig.java
│   │   │   │   │       │   └── AIModelConfig.java
│   │   │   │   │       ├── controller/
│   │   │   │   │       │   ├── DiaryController.java
│   │   │   │   │       │   ├── QuestionnaireController.java
│   │   │   │   │       │   └── InsightsController.java
│   │   │   │   │       ├── service/
│   │   │   │   │       │   ├── DiaryService.java
│   │   │   │   │       │   ├── AIQuestionnaireService.java
│   │   │   │   │       │   └── PredictionService.java
│   │   │   │   │       ├── repository/
│   │   │   │   │       ├── model/
│   │   │   │   │       │   ├── DiaryEntry.java
│   │   │   │   │       │   ├── Questionnaire.java
│   │   │   │   │       │   └── AIPrediction.java
│   │   │   │   │       ├── dto/
│   │   │   │   │       ├── exception/
│   │   │   │   │       └── util/
│   │   │   │   └── resources/
│   │   │   └── test/
│   │   ├── target/
│   │   └── logs/
│   ├── healthcare-service/
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/dhatree/healthcareservice/
│   │   │   │   │       ├── HealthcareServiceApplication.java
│   │   │   │   │       ├── config/
│   │   │   │   │       │   ├── GoogleMapsConfig.java
│   │   │   │   │       │   └── LocationConfig.java
│   │   │   │   │       ├── controller/
│   │   │   │   │       │   ├── AppointmentController.java
│   │   │   │   │       │   ├── DoctorController.java
│   │   │   │   │       │   ├── HospitalController.java
│   │   │   │   │       │   ├── BloodBankController.java
│   │   │   │   │       │   └── PharmacyController.java
│   │   │   │   │       ├── service/
│   │   │   │   │       │   ├── AppointmentService.java
│   │   │   │   │       │   ├── LocationService.java
│   │   │   │   │       │   └── MapsIntegrationService.java
│   │   │   │   │       ├── repository/
│   │   │   │   │       ├── model/
│   │   │   │   │       │   ├── Appointment.java
│   │   │   │   │       │   ├── Doctor.java
│   │   │   │   │       │   ├── Hospital.java
│   │   │   │   │       │   ├── BloodBank.java
│   │   │   │   │       │   └── Pharmacy.java
│   │   │   │   │       ├── dto/
│   │   │   │   │       ├── exception/
│   │   │   │   │       └── util/
│   │   │   │   └── resources/
│   │   │   └── test/
│   │   ├── target/
│   │   └── logs/
│   ├── notification-service/
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/dhatree/notificationservice/
│   │   │   │   │       ├── NotificationServiceApplication.java
│   │   │   │   │       ├── config/
│   │   │   │   │       │   ├── EmailConfig.java
│   │   │   │   │       │   ├── FCMConfig.java
│   │   │   │   │       │   └── SchedulerConfig.java
│   │   │   │   │       ├── controller/
│   │   │   │   │       │   ├── NotificationController.java
│   │   │   │   │       │   └── ReminderController.java
│   │   │   │   │       ├── service/
│   │   │   │   │       │   ├── EmailService.java
│   │   │   │   │       │   ├── PushNotificationService.java
│   │   │   │   │       │   └── ReminderService.java
│   │   │   │   │       ├── repository/
│   │   │   │   │       ├── model/
│   │   │   │   │       │   ├── Notification.java
│   │   │   │   │       │   ├── Reminder.java
│   │   │   │   │       │   └── NotificationTemplate.java
│   │   │   │   │       ├── dto/
│   │   │   │   │       ├── exception/
│   │   │   │   │       └── util/
│   │   │   │   └── resources/
│   │   │   │       └── templates/
│   │   │   │           ├── email/
│   │   │   │           └── push/
│   │   │   └── test/
│   │   ├── target/
│   │   └── logs/
│   └── frontend-service/
│       ├── Dockerfile
│       ├── package.json
│       ├── package-lock.json
│       ├── nginx.conf
│       ├── public/
│       │   ├── index.html
│       │   ├── favicon.ico
│       │   ├── manifest.json
│       │   └── assets/
│       │       ├── images/
│       │       ├── icons/
│       │       └── fonts/
│       ├── src/
│       │   ├── App.js
│       │   ├── index.js
│       │   ├── components/
│       │   │   ├── common/
│       │   │   │   ├── Header.jsx
│       │   │   │   ├── Footer.jsx
│       │   │   │   ├── Sidebar.jsx
│       │   │   │   ├── Loading.jsx
│       │   │   │   ├── Modal.jsx
│       │   │   │   └── Button.jsx
│       │   │   ├── auth/
│       │   │   │   ├── Login.jsx
│       │   │   │   ├── Signup.jsx
│       │   │   │   ├── Profile.jsx
│       │   │   │   └── ForgotPassword.jsx
│       │   │   ├── pregnancy/
│       │   │   │   ├── Calculator.jsx
│       │   │   │   ├── WeeklyUpdate.jsx
│       │   │   │   ├── DietRecommendations.jsx
│       │   │   │   └── WellnessTips.jsx
│       │   │   ├── community/
│       │   │   │   ├── Forums.jsx
│       │   │   │   ├── Groups.jsx
│       │   │   │   ├── Messages.jsx
│       │   │   │   └── CreatePost.jsx
│       │   │   ├── healthcare/
│       │   │   │   ├── HospitalFinder.jsx
│       │   │   │   ├── Appointments.jsx
│       │   │   │   ├── DoctorProfile.jsx
│       │   │   │   ├── BloodBankFinder.jsx
│       │   │   │   └── PharmacyFinder.jsx
│       │   │   ├── ai/
│       │   │   │   ├── Diary.jsx
│       │   │   │   ├── Questionnaire.jsx
│       │   │   │   ├── Insights.jsx
│       │   │   │   └── AIChat.jsx
│       │   │   └── notifications/
│       │   │       ├── NotificationCenter.jsx
│       │   │       ├── ReminderSettings.jsx
│       │   │       └── PushNotifications.jsx
│       │   ├── pages/
│       │   │   ├── HomePage.jsx
│       │   │   ├── DashboardPage.jsx
│       │   │   ├── PregnancyPage.jsx
│       │   │   ├── CommunityPage.jsx
│       │   │   ├── HealthcarePage.jsx
│       │   │   ├── DiaryPage.jsx
│       │   │   └── SettingsPage.jsx
│       │   ├── services/
│       │   │   ├── api.js
│       │   │   ├── auth.service.js
│       │   │   ├── pregnancy.service.js
│       │   │   ├── community.service.js
│       │   │   ├── healthcare.service.js
│       │   │   ├── ai.service.js
│       │   │   └── notification.service.js
│       │   ├── utils/
│       │   │   ├── constants.js
│       │   │   ├── helpers.js
│       │   │   ├── validators.js
│       │   │   └── dateUtils.js
│       │   ├── context/
│       │   │   ├── AuthContext.js
│       │   │   ├── PregnancyContext.js
│       │   │   ├── NotificationContext.js
│       │   │   └── ThemeContext.js
│       │   ├── hooks/
│       │   │   ├── useAuth.js
│       │   │   ├── usePregnancy.js
│       │   │   ├── useNotifications.js
│       │   │   └── useLocalStorage.js
│       │   ├── styles/
│       │   │   ├── globals.css
│       │   │   ├── variables.css
│       │   │   ├── components/
│       │   │   │   ├── header.css
│       │   │   │   ├── buttons.css
│       │   │   │   └── forms.css
│       │   │   └── pages/
│       │   │       ├── dashboard.css
│       │   │       ├── pregnancy.css
│       │   │       └── community.css
│       │   └── assets/
│       │       ├── images/
│       │       ├── icons/
│       │       └── fonts/
│       ├── build/
│       ├── node_modules/
│       └── logs/
├── .github/ **Optional**
│   └── workflows/
│       ├── ci-cd.yml
│       ├── user-service.yml
│       ├── community-service.yml
│       ├── pregnancy-service.yml
│       ├── ai-service.yml
│       ├── healthcare-service.yml
│       ├── notification-service.yml
│       └── frontend-service.yml
# --------------------------------------------------------------------------------------**Optional**
├── scripts/ **Optional**
│   ├── setup.sh
│   ├── start-dev.sh
│   ├── stop-services.sh
│   ├── supabase-setup.sql
│   └── deploy.sh
├── docs/ **Optional**
│   ├── API_DOCUMENTATION.md
│   ├── DEPLOYMENT.md
│   ├── MICROSERVICES_GUIDE.md
│   └── DATABASE_SCHEMA.md
├── api-gateway/ **Optional**
│   ├── Dockerfile
│   ├── nginx.conf
│   ├── routes.conf
│   └── ssl/
│       ├── certificates/
│       └── keys/
├── shared/ **Optional**
│   ├── database/
│   │   ├── supabase/
│   │   │   ├── migrations/
│   │   │   │   ├── 001_create_user_tables.sql
│   │   │   │   ├── 002_create_pregnancy_tables.sql
│   │   │   │   ├── 003_create_community_tables.sql
│   │   │   │   ├── 004_create_healthcare_tables.sql
│   │   │   │   ├── 005_create_ai_tables.sql
│   │   │   │   └── 006_create_notification_tables.sql
│   │   │   ├── seeds/
│   │   │   │   ├── hospitals.sql
│   │   │   │   ├── doctors.sql
│   │   │   │   ├── wellness_tips.sql
│   │   │   │   └── blood_banks.sql
│   │   │   ├── functions/
│   │   │   │   ├── auth_functions.sql
│   │   │   │   ├── pregnancy_calculations.sql
│   │   │   │   └── location_functions.sql
│   │   │   └── policies/
│   │   │       ├── rls_policies.sql
│   │   │       └── security_policies.sql
│   │   └── schemas/
│   │       ├── user_schema.sql
│   │       ├── pregnancy_schema.sql
│   │       ├── community_schema.sql
│   │       ├── healthcare_schema.sql
│   │       ├── ai_schema.sql
│   │       └── notification_schema.sql
│   ├── common/
│   │   ├── models/
│   │   │   ├── ApiResponse.java
│   │   │   ├── UserDto.java
│   │   │   ├── BaseEntity.java
│   │   │   └── ErrorResponse.java
│   │   ├── utils/
│   │   │   ├── DateUtil.java
│   │   │   ├── ValidationUtil.java
│   │   │   ├── EncryptionUtil.java
│   │   │   └── StringUtil.java
│   │   ├── constants/
│   │   │   ├── AppConstants.java
│   │   │   ├── PregnancyConstants.java
│   │   │   ├── ErrorConstants.java
│   │   │   └── NotificationConstants.java
│   │   └── exceptions/
│   │       ├── BaseException.java
│   │       ├── ValidationException.java
│   │       └── ServiceException.java
│   └── docker/
│       ├── redis/
│       │   ├── redis.conf
│       │   └── init.sh
│       └── nginx/
│           ├── nginx.conf
│           ├── ssl/
│           └── logs/
├── config/ **Optional**
│   ├── monitoring/
│   │   ├── prometheus.yml
│   │   ├── grafana/
│   │   │   ├── dashboards/
│   │   │   └── provisioning/
│   │   └── alertmanager.yml
│   └── environments/
│       ├── development.yml
│       ├── staging.yml
│       └── production.yml
└── logs/ **Optional**
    ├── services/
    ├── api-gateway/
    └── monitoring/
