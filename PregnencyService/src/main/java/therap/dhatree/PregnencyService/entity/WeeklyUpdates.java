package therap.dhatree.PregnencyService.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "weekly_updates")
public class WeeklyUpdates {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "week_number", nullable = false, unique = true)
    private Integer weekNumber;
    
    @Column(name = "baby_size")
    private String babySize;
    
    @Column(name = "baby_development", columnDefinition = "TEXT")
    private List<String> babyDevelopment;
    
    @Column(name = "mother_changes", columnDefinition = "TEXT")
    private List<String> motherChanges;
    
    @Column(name = "tips_advice", columnDefinition = "TEXT")
    private List<String> tipsAdvice;
    
    @Column(name = "common_symptoms", columnDefinition = "TEXT")
    private List<String> commonSymptoms;
    
    @Column(name = "important_notes", columnDefinition = "TEXT")
    private List<String> importantNotes;
    
    @Column(name = "medical_checkups", columnDefinition = "TEXT")
    private List<String> medicalCheckups;
    
    // Constructors
    public WeeklyUpdates() {}
    
    public WeeklyUpdates(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }
    
    public WeeklyUpdates(Integer weekNumber, String babySize) {
        this.weekNumber = weekNumber;
        this.babySize = babySize;
    }
    
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public Integer getWeekNumber() {
        return weekNumber;
    }
    
    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }
    
    public String getBabySize() {
        return babySize;
    }
    
    public void setBabySize(String babySize) {
        this.babySize = babySize;
    }
    
    public List<String> getBabyDevelopment() {
        return babyDevelopment;
    }
    
    public void setBabyDevelopment(List<String> babyDevelopment) {
        this.babyDevelopment = babyDevelopment;
    }
    
    public List<String> getMotherChanges() {
        return motherChanges;
    }
    
    public void setMotherChanges(List<String> motherChanges) {
        this.motherChanges = motherChanges;
    }
    
    public List<String> getTipsAdvice() {
        return tipsAdvice;
    }
    
    public void setTipsAdvice(List<String> tipsAdvice) {
        this.tipsAdvice = tipsAdvice;
    }
    
    public List<String> getCommonSymptoms() {
        return commonSymptoms;
    }
    
    public void setCommonSymptoms(List<String> commonSymptoms) {
        this.commonSymptoms = commonSymptoms;
    }
    
    public List<String> getImportantNotes() {
        return importantNotes;
    }
    
    public void setImportantNotes(List<String> importantNotes) {
        this.importantNotes = importantNotes;
    }
    
    public List<String> getMedicalCheckups() {
        return medicalCheckups;
    }
    
    public void setMedicalCheckups(List<String> medicalCheckups) {
        this.medicalCheckups = medicalCheckups;
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
    
    public boolean hasBabyDevelopment() {
        return babyDevelopment != null && !babyDevelopment.isEmpty();
    }
    
    public boolean hasMotherChanges() {
        return motherChanges != null && !motherChanges.isEmpty();
    }
    
    public boolean hasTipsAdvice() {
        return tipsAdvice != null && !tipsAdvice.isEmpty();
    }
    
    public boolean hasCommonSymptoms() {
        return commonSymptoms != null && !commonSymptoms.isEmpty();
    }
    
    public boolean hasImportantNotes() {
        return importantNotes != null && !importantNotes.isEmpty();
    }
    
    public boolean hasMedicalCheckups() {
        return medicalCheckups != null && !medicalCheckups.isEmpty();
    }
    
    public String getWeekDisplay() {
        if (weekNumber == null) return "Unknown Week";
        return "Week " + weekNumber;
    }
    
    public String getBabySizeDisplay() {
        if (babySize == null || babySize.trim().isEmpty()) {
            return "Size not specified";
        }
        return babySize;
    }
}
