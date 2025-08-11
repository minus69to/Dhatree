package therap.dhatree.PregnencyService.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "weight_gain_guidelines")
public class WeightGainGuidelines {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "bmi_category", nullable = false)
    // Valid values: "underweight", "normal", "overweight", "obese"
    private String bmiCategory;
    
    @Column(name = "bmi_range_min", nullable = false, precision = 4, scale = 2)
    private BigDecimal bmiRangeMin;
    
    @Column(name = "bmi_range_max", nullable = false, precision = 4, scale = 2)
    private BigDecimal bmiRangeMax;
    
    @Column(name = "total_weight_gain_min", nullable = false, precision = 4, scale = 1)
    private BigDecimal totalWeightGainMin;
    
    @Column(name = "total_weight_gain_max", nullable = false, precision = 4, scale = 1)
    private BigDecimal totalWeightGainMax;
    
    @Column(name = "first_trimester_gain", precision = 3, scale = 1)
    private BigDecimal firstTrimesterGain;
    
    @Column(name = "second_third_trimester_weekly", precision = 3, scale = 2)
    private BigDecimal secondThirdTrimesterWeekly;
    
    @Column(name = "twins_total_gain_min", precision = 4, scale = 1)
    private BigDecimal twinsTotalGainMin;
    
    @Column(name = "twins_total_gain_max", precision = 4, scale = 1)
    private BigDecimal twinsTotalGainMax;
    
    // Constructors
    public WeightGainGuidelines() {}
    
    public WeightGainGuidelines(String bmiCategory, BigDecimal bmiRangeMin, BigDecimal bmiRangeMax, 
                               BigDecimal totalWeightGainMin, BigDecimal totalWeightGainMax) {
        this.bmiCategory = bmiCategory;
        this.bmiRangeMin = bmiRangeMin;
        this.bmiRangeMax = bmiRangeMax;
        this.totalWeightGainMin = totalWeightGainMin;
        this.totalWeightGainMax = totalWeightGainMax;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getBmiCategory() {
        return bmiCategory;
    }
    
    public void setBmiCategory(String bmiCategory) {
        this.bmiCategory = bmiCategory;
    }
    
    public BigDecimal getBmiRangeMin() {
        return bmiRangeMin;
    }
    
    public void setBmiRangeMin(BigDecimal bmiRangeMin) {
        this.bmiRangeMin = bmiRangeMin;
    }
    
    public BigDecimal getBmiRangeMax() {
        return bmiRangeMax;
    }
    
    public void setBmiRangeMax(BigDecimal bmiRangeMax) {
        this.bmiRangeMax = bmiRangeMax;
    }
    
    public BigDecimal getTotalWeightGainMin() {
        return totalWeightGainMin;
    }
    
    public void setTotalWeightGainMin(BigDecimal totalWeightGainMin) {
        this.totalWeightGainMin = totalWeightGainMin;
    }
    
    public BigDecimal getTotalWeightGainMax() {
        return totalWeightGainMax;
    }
    
    public void setTotalWeightGainMax(BigDecimal totalWeightGainMax) {
        this.totalWeightGainMax = totalWeightGainMax;
    }
    
    public BigDecimal getFirstTrimesterGain() {
        return firstTrimesterGain;
    }
    
    public void setFirstTrimesterGain(BigDecimal firstTrimesterGain) {
        this.firstTrimesterGain = firstTrimesterGain;
    }
    
    public BigDecimal getSecondThirdTrimesterWeekly() {
        return secondThirdTrimesterWeekly;
    }
    
    public void setSecondThirdTrimesterWeekly(BigDecimal secondThirdTrimesterWeekly) {
        this.secondThirdTrimesterWeekly = secondThirdTrimesterWeekly;
    }
    
    public BigDecimal getTwinsTotalGainMin() {
        return twinsTotalGainMin;
    }
    
    public void setTwinsTotalGainMin(BigDecimal twinsTotalGainMin) {
        this.twinsTotalGainMin = twinsTotalGainMin;
    }
    
    public BigDecimal getTwinsTotalGainMax() {
        return twinsTotalGainMax;
    }
    
    public void setTwinsTotalGainMax(BigDecimal twinsTotalGainMax) {
        this.twinsTotalGainMax = twinsTotalGainMax;
    }
    
    // Helper methods
    public boolean isUnderweight() {
        return "underweight".equals(bmiCategory);
    }
    
    public boolean isNormal() {
        return "normal".equals(bmiCategory);
    }
    
    public boolean isOverweight() {
        return "overweight".equals(bmiCategory);
    }
    
    public boolean isObese() {
        return "obese".equals(bmiCategory);
    }
    
    public boolean isBmiInRange(BigDecimal bmi) {
        if (bmi == null || bmiRangeMin == null || bmiRangeMax == null) {
            return false;
        }
        return bmi.compareTo(bmiRangeMin) >= 0 && bmi.compareTo(bmiRangeMax) <= 0;
    }
    
    public BigDecimal getAverageWeightGain() {
        if (totalWeightGainMin == null || totalWeightGainMax == null) {
            return null;
        }
        return totalWeightGainMin.add(totalWeightGainMax).divide(new BigDecimal("2"), 1, BigDecimal.ROUND_HALF_UP);
    }
    
    public BigDecimal getTwinsAverageWeightGain() {
        if (twinsTotalGainMin == null || twinsTotalGainMax == null) {
            return null;
        }
        return twinsTotalGainMin.add(twinsTotalGainMax).divide(new BigDecimal("2"), 1, BigDecimal.ROUND_HALF_UP);
    }
    
    public String getBmiRangeDisplay() {
        if (bmiRangeMin == null || bmiRangeMax == null) {
            return "Not specified";
        }
        return bmiRangeMin + " - " + bmiRangeMax;
    }
    
    public String getTotalWeightGainDisplay() {
        if (totalWeightGainMin == null || totalWeightGainMax == null) {
            return "Not specified";
        }
        return totalWeightGainMin + " - " + totalWeightGainMax + " kg";
    }
}
