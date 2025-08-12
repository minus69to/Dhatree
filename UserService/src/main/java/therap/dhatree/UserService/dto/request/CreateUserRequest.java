package therap.dhatree.UserService.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateUserRequest {

    public String email;
    public String password;
    public String userType; // patient | partner | doctor
    public Profile profile;

    public static class Profile {

        // patient (pregnents table)
        public String first_name;              // required for all
        public String last_name;               // required for patient
        public String phone;                   // patient
        public LocalDate date_of_birth;        // patient
        public String profile_image_url;       // patient
        public String address;                 // patient
        public String emergency_contact_name;  // patient
        public String emergency_contact_phone; // patient
        public String blood_group;             // patient
        public BigDecimal height;              // patient
        public BigDecimal pre_pregnancy_weight; // patient

        // partner
        public String partner_id; // UUID string referencing pregnents.id
        public String relationship_to_mother; // partner

        // doctor: no profile fields per SQL
    }
}
