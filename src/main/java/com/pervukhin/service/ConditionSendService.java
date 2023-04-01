package com.pervukhin.service;

import com.pervukhin.domain.ConditionSend;

public interface ConditionSendService {

    void insert(ConditionSend conditionSend);

    void update(ConditionSend conditionSend);

    ConditionSend getById(int id);

    void delete(int id);
}
