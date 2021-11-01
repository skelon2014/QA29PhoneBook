package models;
import lombok.*;
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor

public class Contact {
    String name;
    String lastName;
    String phone;
    String email;
    String address;
    String description;
}
