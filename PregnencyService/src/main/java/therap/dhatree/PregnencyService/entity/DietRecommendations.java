package therap.dhatree.PregnencyService.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "diet_recommendations")
public class DietRecommendations {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(name = "week_number")
    private Integer weekNumber;
    
    @Column
    private Integer trimester;
    
    @Column(name = "food_category")
    // Valid values: "proteins", "dairy", "fruits", "vegetables", "grains", "fats", "supplements"
    private String foodCategory;
    
    @Column(name = "recommended_foods", columnDefinition = "TEXT")
    private List<String> recommendedFoods;
    
    @Column(name = "foods_to_avoid", columnDefinition = "TEXT")
    private List<String> foodsToAvoid;
    
    @Column(name = "nutritional_notes")
    private String nutritionalNotes;
    
    @Column(name = "calories_suggestion")
    private Integer caloriesSuggestion;
    
    // Constructors
    public DietRecommendations() {}
    
    public DietRecommendations(Integer weekNumber, Integer trimester, String foodCategory) {
        this.weekNumber = weekNumber;
        this.trimester = trimester;
        this.foodCategory = foodCategory;
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
    
    public Integer getTrimester() {
        return trimester;
    }
    
    public void setTrimester(Integer trimester) {
        this.trimester = trimester;
    }
    
    public String getFoodCategory() {
        return foodCategory;
    }
    
    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }
    
    public List<String> getRecommendedFoods() {
        return recommendedFoods;
    }
    
    public void setRecommendedFoods(List<String> recommendedFoods) {
        this.recommendedFoods = recommendedFoods;
    }
    
    public List<String> getFoodsToAvoid() {
        return foodsToAvoid;
    }
    
    public void setFoodsToAvoid(List<String> foodsToAvoid) {
        this.foodsToAvoid = foodsToAvoid;
    }
    
    public String getNutritionalNotes() {
        return nutritionalNotes;
    }
    
    public void setNutritionalNotes(String nutritionalNotes) {
        this.nutritionalNotes = nutritionalNotes;
    }
    
    public Integer getCaloriesSuggestion() {
        return caloriesSuggestion;
    }
    
    public void setCaloriesSuggestion(Integer caloriesSuggestion) {
        this.caloriesSuggestion = caloriesSuggestion;
    }
    
    // Helper methods
    public boolean isFirstTrimester() {
        return Integer.valueOf(1).equals(trimester);
    }
    
    public boolean isSecondTrimester() {
        return Integer.valueOf(2).equals(trimester);
    }
    
    public boolean isThirdTrimester() {
        return Integer.valueOf(3).equals(trimester);
    }
    
    public String getTrimesterDisplay() {
        if (trimester == null) return "Not specified";
        switch (trimester) {
            case 1: return "First Trimester";
            case 2: return "Second Trimester";
            case 3: return "Third Trimester";
            default: return "Unknown Trimester";
        }
    }
    
    public boolean isProteins() {
        return "proteins".equals(foodCategory);
    }
    
    public boolean isDairy() {
        return "dairy".equals(foodCategory);
    }
    
    public boolean isFruits() {
        return "fruits".equals(foodCategory);
    }
    
    public boolean isVegetables() {
        return "vegetables".equals(foodCategory);
    }
    
    public boolean isGrains() {
        return "grains".equals(foodCategory);
    }
    
    public boolean isFats() {
        return "fats".equals(foodCategory);
    }
    
    public boolean isSupplements() {
        return "supplements".equals(foodCategory);
    }
    
    public boolean hasRecommendedFoods() {
        return recommendedFoods != null && !recommendedFoods.isEmpty();
    }
    
    public boolean hasFoodsToAvoid() {
        return foodsToAvoid != null && !foodsToAvoid.isEmpty();
    }
    
    public boolean hasNutritionalNotes() {
        return nutritionalNotes != null && !nutritionalNotes.trim().isEmpty();
    }
    
    public boolean hasCaloriesSuggestion() {
        return caloriesSuggestion != null;
    }
    
    public String getWeekDisplay() {
        if (weekNumber == null) return "General";
        return "Week " + weekNumber;
    }
    
    public String getFoodCategoryDisplay() {
        if (foodCategory == null || foodCategory.trim().isEmpty()) {
            return "General";
        }
        return foodCategory.substring(0, 1).toUpperCase() + foodCategory.substring(1).toLowerCase();
    }
    
    public String getCaloriesDisplay() {
        if (caloriesSuggestion == null) return "Not specified";
        return caloriesSuggestion + " calories";
    }
}
