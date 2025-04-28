package org.dojo.spring.text;

import net.datafaker.Faker;

import java.util.List;

public class Text {
    private final List<String> paragraphs;
    public List<String> getParagraphs() { return paragraphs; }

    public Text(int numberOfParagraphs) {
        var faker = new Faker();
        this.paragraphs = faker.lorem().paragraphs(numberOfParagraphs);
    }
}
