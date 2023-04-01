package com.pervukhin.service;

import com.pervukhin.dao.*;
import com.pervukhin.domain.ConditionSend;

import java.sql.SQLException;

public class ConditionSendServiceImpl implements ConditionSendService {
    private final ConditionSendDao conditionSendDao;

    public ConditionSendServiceImpl() throws SQLException, ClassNotFoundException {
        this.conditionSendDao = new ConditionSendDaoImpl();
    }

    @Override
    public void insert(ConditionSend conditionSend) {
        conditionSendDao.insert(conditionSend);
    }

    @Override
    public void update(ConditionSend conditionSend) {
        conditionSendDao.update(conditionSend);
    }

    @Override
    public ConditionSend getById(int id) {
        return conditionSendDao.getById(id);
    }

    @Override
    public void delete(int id) {
        conditionSendDao.delete(id);
    }
}
