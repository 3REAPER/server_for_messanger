package com.pervukhin.rest.dto;

import com.pervukhin.domain.ConditionSend;
import com.pervukhin.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionSendDto {
    private int id;
    private ProfileDto profile;
    private int condition;

    public static ConditionSendDto toDto(ConditionSend conditionSend){
        ProfileDto profileDto = ProfileDto.toDto(conditionSend.getProfile());

        return new ConditionSendDto(
                conditionSend.getId(),
                profileDto,
                conditionSend.getCondition()
        );
    }

    public static ConditionSend toDomainObject(ConditionSendDto conditionSendDto){
        Profile profile = ProfileDto.toDomainObject(conditionSendDto.getProfile());

        return new ConditionSend(
                conditionSendDto.getId(),
                profile,
                conditionSendDto.getCondition()
        );
    }
}
