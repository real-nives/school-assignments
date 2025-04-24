package com.landon.hotel.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class WelcomeMessageService {
    
    public List<String> getWelcomeMessages() {
        List<String> messages = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        try {
            // Create tasks for English and French messages
            Callable<String> englishTask = () -> {
                ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
                return bundle.getString("welcome.message");
            };
            
            Callable<String> frenchTask = () -> {
                ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.FRENCH);
                return bundle.getString("welcome.message");
            };
            
            // Submit tasks and get futures
            Future<String> englishFuture = executor.submit(englishTask);
            Future<String> frenchFuture = executor.submit(frenchTask);
            
            // Get results
            messages.add(englishFuture.get());
            messages.add(frenchFuture.get());
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        
        return messages;
    }
} 