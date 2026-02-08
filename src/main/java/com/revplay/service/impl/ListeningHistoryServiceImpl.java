package com.revplay.service.impl;

import java.util.List;

import com.revplay.dao.ListeningHistoryDAO;
import com.revplay.dao.impl.ListeningHistoryDAOImpl;
import com.revplay.model.ListeningHistory;
import com.revplay.service.ListeningHistoryService;

public class ListeningHistoryServiceImpl implements ListeningHistoryService {

    private ListeningHistoryDAO historyDAO = new ListeningHistoryDAOImpl();

    @Override
    public void addHistory(int userId, int songId) throws Exception {
        historyDAO.addHistory(userId, songId);
    }

    @Override
    public List<ListeningHistory> getHistoryByUser(int userId) throws Exception {
        return historyDAO.getHistoryByUser(userId);
    }
}
