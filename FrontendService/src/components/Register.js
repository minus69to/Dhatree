import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Register = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    userType: 'patient',
    authProvider: 'email',
    profile: {
      first_name: '',
      last_name: '',
      phone: '',
      date_of_birth: '',
      address: '',
      emergency_contact_name: '',
      emergency_contact_phone: '',
      blood_group: '',
      height: '',
      pre_pregnancy_weight: '',
      profile_image_url: '',
      partner_id: '',
      relationship_to_mother: ''
    }
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { register } = useAuth();

  const handleChange = (e) => {
    const { name, value } = e.target;
    
    if (name.startsWith('profile.')) {
      const profileField = name.split('.')[1];
      setFormData({
        ...formData,
        profile: {
          ...formData.profile,
          [profileField]: value
        }
      });
    } else {
      setFormData({
        ...formData,
        [name]: value
      });
    }
  };

  const validateForm = () => {
    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match');
      return false;
    }
    
    if (formData.password.length < 6) {
      setError('Password must be at least 6 characters long');
      return false;
    }

    if (formData.userType === 'patient') {
      if (!formData.profile.first_name || !formData.profile.last_name) {
        setError('First name and last name are required for patients');
        return false;
      }
    }

    if (formData.userType === 'partner') {
      if (!formData.profile.first_name || !formData.profile.partner_id || !formData.profile.relationship_to_mother) {
        setError('First name, partner ID, and relationship are required for partners');
        return false;
      }
    }

    if (formData.userType === 'doctor') {
      if (!formData.profile.first_name || !formData.profile.last_name) {
        setError('First name and last name are required for doctors');
        return false;
      }
    }

    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');

    if (!validateForm()) {
      return;
    }

    setLoading(true);

    try {
      // Clean up the form data before sending
      const submitData = {
        email: formData.email,
        password: formData.password,
        userType: formData.userType,
        authProvider: formData.authProvider,
        profile: {}
      };

      // Only include non-empty profile fields
      Object.keys(formData.profile).forEach(key => {
        if (formData.profile[key] && formData.profile[key].trim() !== '') {
          submitData.profile[key] = formData.profile[key];
        }
      });

      const result = await register(submitData);
      if (!result.success) {
        setError(result.error);
      }
    } catch (err) {
      setError('An unexpected error occurred');
    } finally {
      setLoading(false);
    }
  };

  const renderUserTypeFields = () => {
    switch (formData.userType) {
      case 'patient':
        return (
          <>
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="profile.first_name">First Name *</label>
                <input
                  type="text"
                  id="profile.first_name"
                  name="profile.first_name"
                  value={formData.profile.first_name}
                  onChange={handleChange}
                  required
                  placeholder="Enter first name"
                />
              </div>
              <div className="form-group">
                <label htmlFor="profile.last_name">Last Name *</label>
                <input
                  type="text"
                  id="profile.last_name"
                  name="profile.last_name"
                  value={formData.profile.last_name}
                  onChange={handleChange}
                  required
                  placeholder="Enter last name"
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="profile.phone">Phone Number</label>
                <input
                  type="tel"
                  id="profile.phone"
                  name="profile.phone"
                  value={formData.profile.phone}
                  onChange={handleChange}
                  placeholder="Enter phone number"
                />
              </div>
              <div className="form-group">
                <label htmlFor="profile.date_of_birth">Date of Birth</label>
                <input
                  type="date"
                  id="profile.date_of_birth"
                  name="profile.date_of_birth"
                  value={formData.profile.date_of_birth}
                  onChange={handleChange}
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="profile.address">Address</label>
              <input
                type="text"
                id="profile.address"
                name="profile.address"
                value={formData.profile.address}
                onChange={handleChange}
                placeholder="Enter full address"
              />
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="profile.emergency_contact_name">Emergency Contact Name</label>
                <input
                  type="text"
                  id="profile.emergency_contact_name"
                  name="profile.emergency_contact_name"
                  value={formData.profile.emergency_contact_name}
                  onChange={handleChange}
                  placeholder="Emergency contact name"
                />
              </div>
              <div className="form-group">
                <label htmlFor="profile.emergency_contact_phone">Emergency Contact Phone</label>
                <input
                  type="tel"
                  id="profile.emergency_contact_phone"
                  name="profile.emergency_contact_phone"
                  value={formData.profile.emergency_contact_phone}
                  onChange={handleChange}
                  placeholder="Emergency contact phone"
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="profile.blood_group">Blood Group</label>
                <select
                  id="profile.blood_group"
                  name="profile.blood_group"
                  value={formData.profile.blood_group}
                  onChange={handleChange}
                >
                  <option value="">Select blood group</option>
                  <option value="A+">A+</option>
                  <option value="A-">A-</option>
                  <option value="B+">B+</option>
                  <option value="B-">B-</option>
                  <option value="AB+">AB+</option>
                  <option value="AB-">AB-</option>
                  <option value="O+">O+</option>
                  <option value="O-">O-</option>
                </select>
              </div>
              <div className="form-group">
                <label htmlFor="profile.height">Height (cm)</label>
                <input
                  type="number"
                  id="profile.height"
                  name="profile.height"
                  value={formData.profile.height}
                  onChange={handleChange}
                  placeholder="Height in centimeters"
                  step="0.1"
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="profile.pre_pregnancy_weight">Pre-pregnancy Weight (kg)</label>
              <input
                type="number"
                id="profile.pre_pregnancy_weight"
                name="profile.pre_pregnancy_weight"
                value={formData.profile.pre_pregnancy_weight}
                onChange={handleChange}
                placeholder="Weight in kilograms"
                step="0.1"
              />
            </div>
          </>
        );

      case 'partner':
        return (
          <>
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="profile.first_name">First Name *</label>
                <input
                  type="text"
                  id="profile.first_name"
                  name="profile.first_name"
                  value={formData.profile.first_name}
                  onChange={handleChange}
                  required
                  placeholder="Enter first name"
                />
              </div>
              <div className="form-group">
                <label htmlFor="profile.last_name">Last Name</label>
                <input
                  type="text"
                  id="profile.last_name"
                  name="profile.last_name"
                  value={formData.profile.last_name}
                  onChange={handleChange}
                  placeholder="Enter last name"
                />
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="profile.partner_id">Partner ID (Patient's User ID) *</label>
              <input
                type="text"
                id="profile.partner_id"
                name="profile.partner_id"
                value={formData.profile.partner_id}
                onChange={handleChange}
                required
                placeholder="Enter patient's user ID"
              />
            </div>

            <div className="form-group">
              <label htmlFor="profile.relationship_to_mother">Relationship to Mother *</label>
              <select
                id="profile.relationship_to_mother"
                name="profile.relationship_to_mother"
                value={formData.profile.relationship_to_mother}
                onChange={handleChange}
                required
              >
                <option value="">Select relationship</option>
                <option value="husband">Husband</option>
                <option value="partner">Partner</option>
                <option value="boyfriend">Boyfriend</option>
                <option value="spouse">Spouse</option>
              </select>
            </div>
          </>
        );

      case 'doctor':
        return (
          <>
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="profile.first_name">First Name *</label>
                <input
                  type="text"
                  id="profile.first_name"
                  name="profile.first_name"
                  value={formData.profile.first_name}
                  onChange={handleChange}
                  required
                  placeholder="Enter first name"
                />
              </div>
              <div className="form-group">
                <label htmlFor="profile.last_name">Last Name *</label>
                <input
                  type="text"
                  id="profile.last_name"
                  name="profile.last_name"
                  value={formData.profile.last_name}
                  onChange={handleChange}
                  required
                  placeholder="Enter last name"
                />
              </div>
            </div>
          </>
        );

      default:
        return null;
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-card">
        <div className="auth-header">
          <h1>Join Dhatree</h1>
          <p>Create your account to start your pregnancy journey</p>
        </div>

        {error && (
          <div className="error-message">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="userType">I am a:</label>
            <select
              id="userType"
              name="userType"
              value={formData.userType}
              onChange={handleChange}
              required
            >
              <option value="patient">Expecting Mother (Patient)</option>
              <option value="partner">Partner/Family Member</option>
              <option value="doctor">Healthcare Provider (Doctor)</option>
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="email">Email Address</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
              placeholder="Enter your email"
            />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                placeholder="Create a password"
                minLength="6"
              />
            </div>
            <div className="form-group">
              <label htmlFor="confirmPassword">Confirm Password</label>
              <input
                type="password"
                id="confirmPassword"
                name="confirmPassword"
                value={formData.confirmPassword}
                onChange={handleChange}
                required
                placeholder="Confirm your password"
                minLength="6"
              />
            </div>
          </div>

          {renderUserTypeFields()}

          <button 
            type="submit" 
            className="btn btn-primary"
            disabled={loading}
          >
            {loading && <span className="loading"></span>}
            Create Account
          </button>
        </form>

        <div className="auth-switch">
          <p>Already have an account?</p>
          <Link to="/login">
            <button type="button">Sign In</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Register;
