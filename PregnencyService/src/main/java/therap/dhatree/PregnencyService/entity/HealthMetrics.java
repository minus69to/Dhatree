package therap.dhatree.PregnencyService.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "health_metrics")
public class HealthMetrics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "pregnancy_id")
    private UUID pregnancyId;
    
    @Column(name = "week_number", nullable = false)
    private Integer weekNumber;
    
    @Column(name = "recorded_date", nullable = false)
    private LocalDate recordedDate;
    
    @Column(precision = 5, scale = 2)
    private BigDecimal weight;
    
    @Column(name = "blood_pressure_systolic")
    private Integer bloodPressureSystolic;
    
    @Column(name = "blood_pressure_diastolic")
    private Integer bloodPressureDiastolic;
    
    @Column(name = "heart_rate")
    private Integer heartRate;
    
    @Column(name = "baby_movement_count")
    private Integer babyMovementCount;
    
    @Column(columnDefinition = "TEXT")
    private List<String> symptoms;
    
    @Column(name = "mood_rating")
    // Valid values: 1, 2, 3, 4, 5 (1=Very Poor, 5=Excellent)
    private Integer moodRating;
    
    @Column(name = "energy_level")
    // Valid values: 1, 2, 3, 4, 5 (1=Very Low, 5=Very High)
    private Integer energyLevel;
    
    @Column(name = "sleep_hours", precision = 3, scale = 1)
    private BigDecimal sleepHours;
    
    @Column(name = "water_intake", precision = 4, scale = 1)
    private BigDecimal waterIntake;
    
    @Column
    private String notes;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
    
    // Constructors
    public HealthMetrics() {}
    
    public HealthMetrics(UUID pregnancyId, Integer weekNumber, LocalDate recordedDate) {
        this.pregnancyId = pregnancyId;
        this.weekNumber = weekNumber;
        this.recordedDate = recordedDate;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getPregnancyId() {
        return pregnancyId;
    }
    
    public void setPregnancyId(UUID pregnancyId) {
        this.pregnancyId = pregnancyId;
    }
    
    public Integer getWeekNumber() {
        return weekNumber;
    }
    
    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }
    
    public LocalDate getRecordedDate() {
        return recordedDate;
    }
    
    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    public Integer getBloodPressureSystolic() {
        return bloodPressureSystolic;
    }
    
    public void setBloodPressureSystolic(Integer bloodPressureSystolic) {
        this.bloodPressureSystolic = bloodPressureSystolic;
    }
    
    public Integer getBloodPressureDiastolic() {
        return bloodPressureDiastolic;
    }
    
    public void setBloodPressureDiastolic(Integer bloodPressureDiastolic) {
        this.bloodPressureDiastolic = bloodPressureDiastolic;
    }
    
    public Integer getHeartRate() {
        return heartRate;
    }
    
    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }
    
    public Integer getBabyMovementCount() {
        return babyMovementCount;
    }
    
    public void setBabyMovementCount(Integer babyMovementCount) {
        this.babyMovementCount = babyMovementCount;
    }
    
    public List<String> getSymptoms() {
        return symptoms;
    }
    
    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
    
    public Integer getMoodRating() {
        return moodRating;
    }
    
    public void setMoodRating(Integer moodRating) {
        this.moodRating = moodRating;
    }
    
    public Integer getEnergyLevel() {
        return energyLevel;
    }
    
    public void setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
    }
    
    public BigDecimal getSleepHours() {
        return sleepHours;
    }
    
    public void setSleepHours(BigDecimal sleepHours) {
        this.sleepHours = sleepHours;
    }
    
    public BigDecimal getWaterIntake() {
        return waterIntake;
    }
    
    public void setWaterIntake(BigDecimal waterIntake) {
        this.waterIntake = waterIntake;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Helper methods
    public boolean isFirstTrimester() {
        return weekNumber != null && weekNumber >= 1 && weekNumber <= 12;
    }
    
    public boolean isSecondTrimester() {
        return weekNumber != null && weekNumber >= 13 && weekNumber <= 26;
    }
    
    public boolean isThirdTrimester() {
        return weekNumber != null && weekNumber >= 27 && weekNumber <= 40;
    }
    
    public String getTrimester() {
        if (isFirstTrimester()) return "First Trimester";
        if (isSecondTrimester()) return "Second Trimester";
        if (isThirdTrimester()) return "Third Trimester";
        return "Unknown";
    }
    
    public boolean hasBloodPressure() {
        return bloodPressureSystolic != null && bloodPressureDiastolic != null;
    }
    
    public boolean hasHeartRate() {
        return heartRate != null;
    }
    
    public boolean hasBabyMovement() {
        return babyMovementCount != null;
    }
    
    public boolean hasSymptoms() {
        return symptoms != null && !symptoms.isEmpty();
    }
    
    public boolean hasMoodRating() {
        return moodRating != null && moodRating >= 1 && moodRating <= 5;
    }
    
    public boolean hasEnergyLevel() {
        return energyLevel != null && energyLevel >= 1 && energyLevel <= 5;
    }
    
    public boolean hasSleepHours() {
        return sleepHours != null;
    }
    
    public boolean hasWaterIntake() {
        return waterIntake != null;
    }
    
    public String getMoodDisplay() {
        if (moodRating == null) return "Not rated";
        switch (moodRating) {
            case 1: return "Very Poor";
            case 2: return "Poor";
            case 3: return "Fair";
            case 4: return "Good";
            case 5: return "Excellent";
            default: return "Invalid Rating";
        }
    }
    
    public String getEnergyDisplay() {
        if (energyLevel == null) return "Not rated";
        switch (energyLevel) {
            case 1: return "Very Low";
            case 2: return "Low";
            case 3: return "Moderate";
            case 4: return "High";
            case 5: return "Very High";
            default: return "Invalid Rating";
        }
    }
    
    public String getBloodPressureDisplay() {
        if (!hasBloodPressure()) return "Not recorded";
        return bloodPressureSystolic + "/" + bloodPressureDiastolic + " mmHg";
    }
    
    public String getHeartRateDisplay() {
        if (!hasHeartRate()) return "Not recorded";
        return heartRate + " bpm";
    }
    
    public String getSleepHoursDisplay() {
        if (!hasSleepHours()) return "Not recorded";
        return sleepHours + " hours";
    }
    
    public String getWaterIntakeDisplay() {
        if (!hasWaterIntake()) return "Not recorded";
        return waterIntake + " liters";
    }
    
    public boolean isNormalBloodPressure() {
        if (!hasBloodPressure()) return false;
        return bloodPressureSystolic >= 90 && bloodPressureSystolic <= 140 &&
               bloodPressureDiastolic >= 60 && bloodPressureDiastolic <= 90;
    }
    
    public boolean isNormalHeartRate() {
        if (!hasHeartRate()) return false;
        return heartRate >= 60 && heartRate <= 100;
    }
    
    public boolean isAdequateSleep() {
        if (!hasSleepHours()) return false;
        return sleepHours.compareTo(new BigDecimal("7.0")) >= 0;
    }
    
    public boolean isAdequateWaterIntake() {
        if (!hasWaterIntake()) return false;
        return waterIntake.compareTo(new BigDecimal("2.0")) >= 0;
    }
}
