package com.epam.maven.model.history;

import com.epam.maven.controller.CalculatorController;
import com.epam.maven.model.operation.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class OperationHistory {

    private static final Logger logger = LogManager.getLogger(OperationHistory.class);

    private Map<Key, Operation> history;

    private Key key;

    private Operation value;

    public OperationHistory() {
        history = new LinkedHashMap<Key, Operation>();
        key = new Key();
    }

    public void setKey(int number, String name) {
        this.key = new Key(number, name);
    }

    public Key getKey() {
        return key;
    }

    public void setValue(Operation value) {
        this.value = value;
    }

    public Operation getValue() {
        return value;
    }

    public Boolean keyValidation() {
        return history.containsKey(key);
    }

    public void put() {
        logger.info("put history for key {} and value {}", key.toString(), value.toString());

        history.put(key, value);
    }

    public Map<Key, Operation> getAll() {
        return history;
    }

    public Operation getHistoryByKey() {
        return history.get(key);
    }
}
