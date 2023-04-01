package com.pervukhin.rest.dto;

import com.pervukhin.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private int id;
    private String name;
    private String login;
    private String password;
    private String number;

    public static ProfileDto toDto(Profile profile){
        return new ProfileDto(
                profile.getId(),
                profile.getName(),
                profile.getLogin(),
                profile.getPassword(),
                profile.getNumber()
        );
    }

    public static Profile toDomainObject(ProfileDto profileDto){
        return new Profile(
                profileDto.getId(),
                profileDto.getName(),
                profileDto.getLogin(),
                profileDto.getPassword(),
                profileDto.getNumber()
        );
    }
}
