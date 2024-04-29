package gov.transportation.railway.User;

import org.springframework.data.annotation.Id;

public record User(
        @Id
        Integer UserId,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {
}
