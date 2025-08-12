# Dhatree - Pregnancy Tracking Platform

## 🚀 Frontend Service

A React.js frontend application for the Dhatree pregnancy tracking platform.

### Features

- **Authentication System**
  - User Registration (Patient, Partner, Doctor)
  - Login/Logout functionality
  - JWT token-based authentication
  - Protected routes and user sessions

- **User Types**
  - **Patient (Expecting Mother)**: Complete profile with medical information
  - **Partner/Family Member**: Linked to patient with relationship details
  - **Doctor/Healthcare Provider**: Professional user accounts

- **Modern UI/UX**
  - Responsive design for all device sizes
  - Beautiful gradient backgrounds and card layouts
  - Form validation and error handling
  - Loading states and user feedback

### Technology Stack

- **React 18** - Modern React with hooks
- **React Router 6** - Client-side routing
- **Axios** - HTTP client for API calls
- **CSS3** - Custom styling with gradients and animations
- **Docker** - Containerized deployment
- **Nginx** - Production web server

### Architecture

```
FrontendService/
├── src/
│   ├── components/          # React components
│   │   ├── Login.js        # Login form component
│   │   ├── Register.js     # Registration form component
│   │   └── Dashboard.js    # User dashboard
│   ├── context/            # React context providers
│   │   └── AuthContext.js  # Authentication state management
│   ├── services/           # API service layer
│   │   └── api.js         # Axios configuration and interceptors
│   ├── App.js             # Main application component
│   ├── index.js           # Application entry point
│   └── index.css          # Global styles
├── public/
│   └── index.html         # HTML template
├── Dockerfile             # Multi-stage Docker build
├── nginx.conf            # Nginx configuration
└── package.json          # Dependencies and scripts
```

### API Integration

The frontend communicates with the UserService backend through:

- **Registration**: `POST /api/v1/auth/register`
- **Login**: `POST /api/v1/auth/login`
- **Get Current User**: `GET /api/v1/auth/me`
- **Logout**: `POST /api/v1/auth/logout`

### Environment Configuration

The application uses Docker networking to communicate with the backend:
- Frontend runs on port 3000 (mapped to host)
- Backend API calls are proxied through Nginx to `userservice:8080`
- JWT tokens stored in localStorage for session management

### Docker Setup

1. **Development**: The app is built using Node.js and then served via Nginx
2. **Production**: Optimized static build served by Nginx Alpine
3. **Networking**: Connected to backend via Docker bridge network

### User Experience Flow

1. **Landing**: Users are redirected to login if not authenticated
2. **Registration**: Multi-step form based on user type selection
3. **Authentication**: JWT tokens manage session state
4. **Dashboard**: Personalized welcome page with user information
5. **Logout**: Clean session termination with backend notification

### Form Validation

- Email format validation
- Password strength requirements (minimum 6 characters)
- Required field validation based on user type
- Real-time error messaging
- Password confirmation matching

### Responsive Design

- Mobile-first approach
- Grid layouts that adapt to screen size
- Touch-friendly form controls
- Readable typography across devices

### Security Features

- JWT token storage and automatic inclusion in requests
- Token refresh handling (future implementation)
- Protected route guards
- Input sanitization and validation
- CORS handling through backend configuration

This frontend provides a complete authentication experience that integrates seamlessly with the Dhatree UserService backend!
