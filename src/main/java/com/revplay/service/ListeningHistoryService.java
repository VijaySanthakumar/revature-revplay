package com.revplay.service;

import java.util.List;
import com.revplay.model.ListeningHistory;

public interface ListeningHistoryService {

    void addHistory(int userId, int songId) throws Exception;

    List<ListeningHistory> getHistoryByUser(int userId) throws Exception;
}
