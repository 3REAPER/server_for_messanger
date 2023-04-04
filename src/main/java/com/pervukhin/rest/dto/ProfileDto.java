package com.pervukhin.rest.dto;

import com.pervukhin.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ProfileDto> toDto(List<Profile> list){
        List<ProfileDto> result = new ArrayList<>();
        if (list != null) {
            for (Profile profile : list) {
                result.add(toDto(profile));
            }
        }
        return result;
    }

    public static List<Profile> toDomainObject(List<ProfileDto> list){
        List<Profile> result = new ArrayList<>();
        if (list != null) {
            for (ProfileDto profile : list) {
                result.add(toDomainObject(profile));
            }
        }
        return result;
    }
}
