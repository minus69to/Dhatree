package therap.dhatree.PregnencyService.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "pregnancies")
public class Pregnancy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "pregnent_id", nullable = false)
    private UUID pregnentId;
    
    @Column(name = "last_menstrual_period", nullable = false)
    private LocalDate lastMenstrualPeriod;
    
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    
    @Column(name = "current_week")
    private Integer currentWeek;
    
    @Column(name = "current_day")
    private Integer currentDay;
    
    @Column(name = "pregnancy_status")
    // Valid values: "active", "completed", "terminated", "miscarriage"
    private String pregnancyStatus = "active";
    
    @Column(name = "is_first_pregnancy")
    private Boolean isFirstPregnancy = true;
    
    @Column(name = "multiple_pregnancy")
    private Boolean multiplePregnancy = false;
    
    @Column(name = "high_risk_pregnancy")
    private Boolean highRiskPregnancy = false;
    
    @Column(name = "pre_pregnancy_bmi", precision = 4, scale = 2)
    private BigDecimal prePregnancyBmi;
    
    @Column(name = "target_weight_gain_min", precision = 4, scale = 1)
    private BigDecimal targetWeightGainMin;
    
    @Column(name = "target_weight_gain_max", precision = 4, scale = 1)
    private BigDecimal targetWeightGainMax;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
    
    // Constructors
    public Pregnancy() {}
    
    public Pregnancy(UUID pregnentId, LocalDate lastMenstrualPeriod, LocalDate dueDate) {
        this.pregnentId = pregnentId;
        this.lastMenstrualPeriod = lastMenstrualPeriod;
        this.dueDate = dueDate;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getPregnentId() {
        return pregnentId;
    }
    
    public void setPregnentId(UUID pregnentId) {
        this.pregnentId = pregnentId;
    }
    
    public LocalDate getLastMenstrualPeriod() {
        return lastMenstrualPeriod;
    }
    
    public void setLastMenstrualPeriod(LocalDate lastMenstrualPeriod) {
        this.lastMenstrualPeriod = lastMenstrualPeriod;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    
    public Integer getCurrentWeek() {
        return currentWeek;
    }
    
    public void setCurrentWeek(Integer currentWeek) {
        this.currentWeek = currentWeek;
    }
    
    public Integer getCurrentDay() {
        return currentDay;
    }
    
    public void setCurrentDay(Integer currentDay) {
        this.currentDay = currentDay;
    }
    
    public String getPregnancyStatus() {
        return pregnancyStatus;
    }
    
    public void setPregnancyStatus(String pregnancyStatus) {
        this.pregnancyStatus = pregnancyStatus;
    }
    
    public Boolean getIsFirstPregnancy() {
        return isFirstPregnancy;
    }
    
    public void setIsFirstPregnancy(Boolean isFirstPregnancy) {
        this.isFirstPregnancy = isFirstPregnancy;
    }
    
    public Boolean getMultiplePregnancy() {
        return multiplePregnancy;
    }
    
    public void setMultiplePregnancy(Boolean multiplePregnancy) {
        this.multiplePregnancy = multiplePregnancy;
    }
    
    public Boolean getHighRiskPregnancy() {
        return highRiskPregnancy;
    }
    
    public void setHighRiskPregnancy(Boolean highRiskPregnancy) {
        this.highRiskPregnancy = highRiskPregnancy;
    }
    
    public BigDecimal getPrePregnancyBmi() {
        return prePregnancyBmi;
    }
    
    public void setPrePregnancyBmi(BigDecimal prePregnancyBmi) {
        this.prePregnancyBmi = prePregnancyBmi;
    }
    
    public BigDecimal getTargetWeightGainMin() {
        return targetWeightGainMin;
    }
    
    public void setTargetWeightGainMin(BigDecimal targetWeightGainMin) {
        this.targetWeightGainMin = targetWeightGainMin;
    }
    
    public BigDecimal getTargetWeightGainMax() {
        return targetWeightGainMax;
    }
    
    public void setTargetWeightGainMax(BigDecimal targetWeightGainMax) {
        this.targetWeightGainMax = targetWeightGainMax;
    }
    
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Helper methods
    public boolean isActive() {
        return "active".equals(pregnancyStatus);
    }
    
    public boolean isCompleted() {
        return "completed".equals(pregnancyStatus);
    }
    
    public boolean isHighRisk() {
        return Boolean.TRUE.equals(highRiskPregnancy);
    }
    
    public boolean isMultiple() {
        return Boolean.TRUE.equals(multiplePregnancy);
    }
}
