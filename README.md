# Dhatree 🤱

A comprehensive pregnancy support platform designed to help new mothers throughout their 9-month journey with AI-powered guidance, community support, and expert resources.

## 🌟 Overview

Dhatree is a modern web application that provides expectant mothers with personalized support, tracking tools, educational resources, and AI-powered assistance during their pregnancy journey. Our platform combines cutting-edge technology with compassionate care to ensure every mother feels supported and informed.

## ✨ Features

- **🤖 AI-Powered Assistant**: Personalized pregnancy guidance and answers to common questions
- **📊 Pregnancy Tracking**: Week-by-week progress tracking with milestone alerts
- **📚 Educational Resources**: Curated articles, videos, and expert advice
- **👥 Community Support**: Connect with other expecting mothers
- **📅 Appointment Management**: Schedule and track prenatal appointments
- **🍎 Nutrition Guidance**: Personalized meal plans and nutrition tracking
- **🧘 Wellness Activities**: Guided meditation, exercises, and relaxation techniques
- **📱 Mobile-Responsive**: Access your support system anywhere, anytime
- **🔔 Smart Notifications**: Reminders for appointments, medications, and milestones
- **📈 Health Metrics**: Track weight, symptoms, and vital signs

## 🛠 Technology Stack

### Frontend

- **React** - Modern UI library for building interactive user interfaces
- **TypeScript** - Type-safe JavaScript for better development experience
- **Tailwind CSS** - Utility-first CSS framework for rapid styling
- **React Router** - Client-side routing
- **React Query** - Data fetching and state management

### Backend

- **Spring Boot** - Java-based framework for building robust APIs
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence layer
- **Maven** - Dependency management and build tool

### Database & Authentication

- **Supabase** - Backend-as-a-Service platform
  - PostgreSQL database
  - Real-time subscriptions
  - Built-in authentication
  - File storage for images and documents
  - Row Level Security (RLS)

### AI Integration

- **OpenAI GPT API** - Conversational AI for pregnancy guidance
- **Hugging Face Models** - Specialized health and wellness models
- **Custom ML Models** - Personalized recommendations

### DevOps & Tools

- **Docker** - Containerization
- **GitHub Actions** - CI/CD pipeline
- **Vercel/Netlify** - Frontend deployment
- **Railway/Heroku** - Backend deployment

## 🚀 Getting Started

### Prerequisites

- Node.js 18+ and npm/yarn
- Java 17+
- Maven 3.6+
- Docker (optional, for containerized development)
- Supabase account

### Environment Setup

1. **Clone the repository**

   ```bash
   git clone https://github.com/minus69to/Dhatree.git
   cd Dhatree
   ```

2. **Setup Supabase**

   - Create a new project on [Supabase](https://supabase.com)
   - Copy your project URL and anon key
   - Run the database migrations (provided in `/database` folder)

3. **Configure Environment Variables**

   Create `.env` files in both frontend and backend directories:

   **Frontend (.env)**

   ```
   REACT_APP_SUPABASE_URL=your_supabase_url
   REACT_APP_SUPABASE_ANON_KEY=your_supabase_anon_key
   REACT_APP_API_BASE_URL=http://localhost:8080/api
   REACT_APP_OPENAI_API_KEY=your_openai_api_key
   ```

   **Backend (application.properties)**

   ```
   supabase.url=your_supabase_url
   supabase.key=your_supabase_service_key
   openai.api.key=your_openai_api_key
   spring.datasource.url=your_supabase_db_url
   spring.datasource.username=postgres
   spring.datasource.password=your_db_password
   ```

### Running the Application

#### Frontend Development Server

```bash
cd frontend
npm install
npm start
```

The React app will be available at `http://localhost:3000`

#### Backend Development Server

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The Spring Boot API will be available at `http://localhost:8080`

#### Using Docker (Optional)

```bash
docker-compose up -d
```

## 📖 API Documentation

Once the backend is running, visit `http://localhost:8080/swagger-ui.html` for interactive API documentation.

### Key Endpoints

- `POST /api/auth/login` - User authentication
- `GET /api/pregnancy/week/{week}` - Weekly pregnancy information
- `POST /api/ai/chat` - AI assistant interaction
- `GET /api/user/profile` - User profile management
- `POST /api/appointments` - Appointment scheduling

## 🤝 Contributing

We welcome contributions from the community! Please read our [Contributing Guidelines](CONTRIBUTING.md) before submitting pull requests.

### Development Workflow

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 🧪 Testing

### Frontend Tests

```bash
cd frontend
npm test
```

### Backend Tests

```bash
cd backend
mvn test
```

## 🐛 Bug Reports & Feature Requests

Please use our [GitHub Issues](https://github.com/minus69to/Dhatree/issues) to report bugs or request new features.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Team

1.  [Md Hasin Arafat Al Sifat](https://github.com/Arafat1969)

2.  [Souvik Mondol Turzo](https://github.com/minus69to)

## 🙏 Acknowledgments

- Medical advisors and healthcare professionals
- Open-source community
- Beta testers and early users
- Pregnancy support organizations

## 📞 Support

For support, email support@dhatree.com or join our community Discord server.

---

**Made with ❤️ for expectant mothers worldwide**
