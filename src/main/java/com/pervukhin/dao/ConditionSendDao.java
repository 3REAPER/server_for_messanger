package com.pervukhin.dao;

import com.pervukhin.domain.ConditionSend;
import java.util.List;

public interface ConditionSendDao {

    void insert(ConditionSend conditionSend);

    List<Integer> insert(List<ConditionSend> conditionSends);

    void update(ConditionSend conditionSend);

    ConditionSend getById(int id);

    void delete(int id);
}
