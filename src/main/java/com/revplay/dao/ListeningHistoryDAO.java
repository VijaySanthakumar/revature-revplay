package com.revplay.dao;

import java.util.List;
import com.revplay.model.ListeningHistory;

public interface ListeningHistoryDAO {

    // record when a song is played
    void addHistory(int userId, int songId) throws Exception;

    // fetch listening history of a user
    List<ListeningHistory> getHistoryByUser(int userId) throws Exception;
}
