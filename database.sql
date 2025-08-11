CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NULL,
    auth_provider VARCHAR(50) DEFAULT 'local' CHECK (auth_provider IN ('local', 'google')),
    google_id VARCHAR(100) UNIQUE,
    user_type VARCHAR(20) NOT NULL,
    email_verified BOOLEAN DEFAULT FALSE,
    is_active BOOLEAN DEFAULT TRUE,
    account_status VARCHAR(20) DEFAULT 'pending' CHECK (account_status IN ('pending', 'active', 'suspended')),
    last_login TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE pregnents (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    profile_image_url TEXT,
    address TEXT,
    emergency_contact_name VARCHAR(100),
    emergency_contact_phone VARCHAR(20),
    blood_group VARCHAR(10),
    height DECIMAL(5,2),
    pre_pregnancy_weight DECIMAL(5,2)
);


CREATE TABLE partner (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    relationship_to_mother VARCHAR(50),
    partner_id UUID REFERENCES pregnents(id)
);

CREATE TABLE doctors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id),
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    profile_image_url TEXT,
    address TEXT,
    specialization TEXT,
    qualification TEXT[],
    experience_years INTEGER,
    license_number VARCHAR(100),
    hospital_affiliation VARCHAR(200),
    consultation_fee DECIMAL(10,2)
);


 


CREATE TABLE password_reset_tokens (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES users(id),
    token VARCHAR(255) UNIQUE NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    is_used BOOLEAN DEFAULT FALSE,
    used_at TIMESTAMP WITH TIME ZONE
);


CREATE TABLE pregnancies (
    id UUID PRIMARY KEY,
    pregnent_id UUID NOT NULL,
    last_menstrual_period DATE NOT NULL,
    due_date DATE NOT NULL,
    current_week INTEGER,
    current_day INTEGER,
    pregnancy_status VARCHAR(50) DEFAULT 'active',
    is_first_pregnancy BOOLEAN DEFAULT TRUE,
    multiple_pregnancy BOOLEAN DEFAULT FALSE,
    high_risk_pregnancy BOOLEAN DEFAULT FALSE,
    pre_pregnancy_bmi DECIMAL(4,2),
    target_weight_gain_min DECIMAL(4,1),
    target_weight_gain_max DECIMAL(4,1),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);




CREATE TABLE weight_tracking (
    id UUID PRIMARY KEY,
    pregnancy_id UUID REFERENCES pregnancies(id),
    user_id UUID NOT NULL,
    recorded_date DATE NOT NULL,
    current_weight DECIMAL(5,2) NOT NULL,
    weight_gain_total DECIMAL(5,2),
    weight_gain_this_week DECIMAL(4,2),
    pregnancy_week INTEGER,
    bmi_current DECIMAL(4,2),
    weight_status VARCHAR(20),
    expected_weight_min DECIMAL(5,2),
    expected_weight_max DECIMAL(5,2),
    remarks TEXT,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE weight_gain_guidelines (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    bmi_category VARCHAR(20) NOT NULL,
    bmi_range_min DECIMAL(4,2) NOT NULL,
    bmi_range_max DECIMAL(4,2) NOT NULL,
    total_weight_gain_min DECIMAL(4,1) NOT NULL,
    total_weight_gain_max DECIMAL(4,1) NOT NULL,
    first_trimester_gain DECIMAL(3,1),
    second_third_trimester_weekly DECIMAL(3,2),
    twins_total_gain_min DECIMAL(4,1),
    twins_total_gain_max DECIMAL(4,1)
);

CREATE TABLE weekly_updates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    week_number INTEGER NOT NULL UNIQUE,
    baby_size VARCHAR(100),
    baby_development TEXT[],
    mother_changes TEXT[],
    tips_advice TEXT[],
    common_symptoms TEXT[],
    important_notes TEXT[],
    medical_checkups TEXT[]
);

CREATE TABLE diet_recommendations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    week_number INTEGER,
    trimester INTEGER,
    food_category VARCHAR(100),
    recommended_foods TEXT[],
    foods_to_avoid TEXT[],
    nutritional_notes TEXT,
    calories_suggestion INTEGER
);

CREATE TABLE health_metrics (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    pregnancy_id UUID REFERENCES pregnancies(id),
    week_number INTEGER NOT NULL,
    recorded_date DATE NOT NULL,
    weight DECIMAL(5,2),
    blood_pressure_systolic INTEGER,
    blood_pressure_diastolic INTEGER,
    heart_rate INTEGER,
    baby_movement_count INTEGER,
    symptoms TEXT[],
    mood_rating INTEGER CHECK (mood_rating >= 1 AND mood_rating <= 5),
    energy_level INTEGER CHECK (energy_level >= 1 AND energy_level <= 5),
    sleep_hours DECIMAL(3,1),
    water_intake DECIMAL(4,1),
    notes TEXT
);




