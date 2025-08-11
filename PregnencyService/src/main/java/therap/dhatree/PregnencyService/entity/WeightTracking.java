package therap.dhatree.PregnencyService.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "weight_tracking")
public class WeightTracking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "pregnancy_id")
    private UUID pregnancyId;
    
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    
    @Column(name = "recorded_date", nullable = false)
    private LocalDate recordedDate;
    
    @Column(name = "current_weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal currentWeight;
    
    @Column(name = "weight_gain_total", precision = 5, scale = 2)
    private BigDecimal weightGainTotal;
    
    @Column(name = "weight_gain_this_week", precision = 4, scale = 2)
    private BigDecimal weightGainThisWeek;
    
    @Column(name = "pregnancy_week")
    private Integer pregnancyWeek;
    
    @Column(name = "bmi_current", precision = 4, scale = 2)
    private BigDecimal bmiCurrent;
    
    @Column(name = "weight_status")
    // Valid values: "underweight", "normal", "overweight", "obese"
    private String weightStatus;
    
    @Column(name = "expected_weight_min", precision = 5, scale = 2)
    private BigDecimal expectedWeightMin;
    
    @Column(name = "expected_weight_max", precision = 5, scale = 2)
    private BigDecimal expectedWeightMax;
    
    @Column
    private String remarks;
    
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;
    
    // Constructors
    public WeightTracking() {}
    
    public WeightTracking(UUID pregnancyId, UUID userId, LocalDate recordedDate, BigDecimal currentWeight) {
        this.pregnancyId = pregnancyId;
        this.userId = userId;
        this.recordedDate = recordedDate;
        this.currentWeight = currentWeight;
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
    
    public UUID getUserId() {
        return userId;
    }
    
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    
    public LocalDate getRecordedDate() {
        return recordedDate;
    }
    
    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }
    
    public BigDecimal getCurrentWeight() {
        return currentWeight;
    }
    
    public void setCurrentWeight(BigDecimal currentWeight) {
        this.currentWeight = currentWeight;
    }
    
    public BigDecimal getWeightGainTotal() {
        return weightGainTotal;
    }
    
    public void setWeightGainTotal(BigDecimal weightGainTotal) {
        this.weightGainTotal = weightGainTotal;
    }
    
    public BigDecimal getWeightGainThisWeek() {
        return weightGainThisWeek;
    }
    
    public void setWeightGainThisWeek(BigDecimal weightGainThisWeek) {
        this.weightGainThisWeek = weightGainThisWeek;
    }
    
    public Integer getPregnancyWeek() {
        return pregnancyWeek;
    }
    
    public void setPregnancyWeek(Integer pregnancyWeek) {
        this.pregnancyWeek = pregnancyWeek;
    }
    
    public BigDecimal getBmiCurrent() {
        return bmiCurrent;
    }
    
    public void setBmiCurrent(BigDecimal bmiCurrent) {
        this.bmiCurrent = bmiCurrent;
    }
    
    public String getWeightStatus() {
        return weightStatus;
    }
    
    public void setWeightStatus(String weightStatus) {
        this.weightStatus = weightStatus;
    }
    
    public BigDecimal getExpectedWeightMin() {
        return expectedWeightMin;
    }
    
    public void setExpectedWeightMin(BigDecimal expectedWeightMin) {
        this.expectedWeightMin = expectedWeightMin;
    }
    
    public BigDecimal getExpectedWeightMax() {
        return expectedWeightMax;
    }
    
    public void setExpectedWeightMax(BigDecimal expectedWeightMax) {
        this.expectedWeightMax = expectedWeightMax;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Helper methods
    public boolean isUnderweight() {
        return "underweight".equals(weightStatus);
    }
    
    public boolean isNormalWeight() {
        return "normal".equals(weightStatus);
    }
    
    public boolean isOverweight() {
        return "overweight".equals(weightStatus);
    }
    
    public boolean isObese() {
        return "obese".equals(weightStatus);
    }
    
    public boolean isWithinExpectedRange() {
        if (expectedWeightMin == null || expectedWeightMax == null || currentWeight == null) {
            return false;
        }
        return currentWeight.compareTo(expectedWeightMin) >= 0 && currentWeight.compareTo(expectedWeightMax) <= 0;
    }
    
    public BigDecimal getWeightGainPercentage() {
        if (weightGainTotal == null || currentWeight == null) {
            return null;
        }
        return weightGainTotal.divide(currentWeight, 4, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100"));
    }
}
