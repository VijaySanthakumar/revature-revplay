package com.revplay.controller;

import com.revplay.model.ListeningHistory;
import com.revplay.service.ListeningHistoryService;
import com.revplay.service.impl.ListeningHistoryServiceImpl;
import com.revplay.util.LoggedInUser;

import java.util.List;

public class ListeningHistoryController {

    private ListeningHistoryService historyService =
            new ListeningHistoryServiceImpl();

    // ===== VIEW LISTENING HISTORY =====
    public void viewHistory() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        List<ListeningHistory> history =
                historyService.getHistoryByUser(userId);

        if (history == null || history.isEmpty()) {
            System.out.println("No listening history found.");
            return;
        }

        System.out.println("\n===== LISTENING HISTORY =====");
        for (ListeningHistory h : history) {
            System.out.println(h);
        }
    }

}
