import React from 'react';
import { useAuth } from '../context/AuthContext';

const Dashboard = () => {
  const { user, logout } = useAuth();

  const handleLogout = async () => {
    await logout();
  };

  const formatDate = (dateString) => {
    if (!dateString) return 'Not provided';
    return new Date(dateString).toLocaleDateString();
  };

  const formatUserType = (userType) => {
    switch (userType) {
      case 'patient':
        return 'Expecting Mother';
      case 'partner':
        return 'Partner/Family Member';
      case 'doctor':
        return 'Healthcare Provider';
      default:
        return userType;
    }
  };

  return (
    <div className="dashboard">
      <div className="container">
        <div className="dashboard-header">
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <div>
              <h1>Welcome to Dhatree!</h1>
              <p>Your pregnancy journey companion</p>
            </div>
            <button 
              onClick={handleLogout}
              className="btn btn-primary"
              style={{ width: 'auto', padding: '10px 20px' }}
            >
              Logout
            </button>
          </div>

          <div className="user-info">
            <h3>Your Profile Information</h3>
            <div className="user-details">
              <div className="user-detail">
                <label>User ID</label>
                <span>{user.id}</span>
              </div>
              <div className="user-detail">
                <label>Email</label>
                <span>{user.email}</span>
              </div>
              <div className="user-detail">
                <label>User Type</label>
                <span>{formatUserType(user.userType)}</span>
              </div>
              <div className="user-detail">
                <label>Account Status</label>
                <span style={{ 
                  color: user.accountStatus === 'active' ? '#28a745' : '#dc3545',
                  fontWeight: 'bold' 
                }}>
                  {user.accountStatus}
                </span>
              </div>
              <div className="user-detail">
                <label>Email Verified</label>
                <span style={{ 
                  color: user.emailVerified ? '#28a745' : '#dc3545' 
                }}>
                  {user.emailVerified ? 'Yes' : 'No'}
                </span>
              </div>
              <div className="user-detail">
                <label>Last Login</label>
                <span>{formatDate(user.lastLogin)}</span>
              </div>
            </div>
          </div>
        </div>

        <div style={{ 
          background: 'white', 
          padding: '30px', 
          borderRadius: '10px', 
          textAlign: 'center',
          boxShadow: '0 2px 10px rgba(0, 0, 0, 0.1)'
        }}>
          <h2 style={{ color: '#333', marginBottom: '20px' }}>🎉 Authentication System Working!</h2>
          <p style={{ color: '#666', fontSize: '1.1rem', lineHeight: '1.6' }}>
            Your authentication is working perfectly! You have successfully:
          </p>
          <ul style={{ 
            listStyle: 'none', 
            padding: 0, 
            margin: '20px 0',
            color: '#333'
          }}>
            <li style={{ margin: '10px 0' }}>✅ Registered your account</li>
            <li style={{ margin: '10px 0' }}>✅ Logged in successfully</li>
            <li style={{ margin: '10px 0' }}>✅ Accessed your profile information</li>
            <li style={{ margin: '10px 0' }}>✅ JWT tokens are working properly</li>
          </ul>
          <p style={{ color: '#666', marginTop: '30px' }}>
            This dashboard will be expanded with pregnancy tracking features, 
            health metrics, weekly updates, and more as the application grows.
          </p>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
