package com.ros.journalApp.controller;

import com.ros.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
    private final Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        // Debug logging
        System.out.println("Received entry: " + myEntry);
        if (myEntry != null && myEntry.getProductname() != null) {
            journalEntries.put(myEntry.getProductname(), myEntry);
            return true;
        }
        return false;
    }
}
