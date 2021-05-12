package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    DBReader reader;

    @BeforeEach
    void setUp() {
        reader = new DBReader();
    }

    @Test
    void findAll() {
        System.out.println(reader.getList().toString());
    }
}