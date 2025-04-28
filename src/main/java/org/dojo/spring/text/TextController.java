package org.dojo.spring.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class TextController {

    private static final Logger logger = LoggerFactory.getLogger(TextController.class);
    private final Random random = new Random();

    @GetMapping(value = "text")
    public ResponseEntity<Text> getText(@RequestParam int para, @RequestParam int maxWaitTime) {
        logger.info("Received request to generate {} paragraphs with max wait time {} seconds.", para, maxWaitTime);
        sleepRandomTime(maxWaitTime);
        Text text = new Text(para);
        return ResponseEntity.ok(text);
    }

    private void sleepRandomTime(int maxWaitTime) {
        try {
            double randomDelay = random.nextDouble() * maxWaitTime * 1000; // Convert to milliseconds
            logger.info("Sleeping for {} ms", randomDelay);
            TimeUnit.MILLISECONDS.sleep((long) randomDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread was interrupted during sleep", e);
        }
    }
}
