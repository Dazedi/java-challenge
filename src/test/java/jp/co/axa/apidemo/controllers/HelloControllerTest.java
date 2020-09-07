package jp.co.axa.apidemo.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    
    @Test
    public void hello() {
        // Arrange
        HelloController controller = new HelloController(); 
        // Act
        String response = controller.hello("World"); 
        // Assert
        assertEquals("Hello, World", response);
    }
}
