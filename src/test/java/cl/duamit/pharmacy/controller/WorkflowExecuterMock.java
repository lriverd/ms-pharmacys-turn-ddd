package cl.duamit.pharmacy.controller;

import cl.duamit.pharmacy.core.Workflow;
import cl.duamit.pharmacy.core.WorkflowExecuter;
import org.springframework.http.ResponseEntity;

public class WorkflowExecuterMock extends WorkflowExecuter {

    @Override
    public ResponseEntity<Object> execute(Workflow workflow) {
        return ResponseEntity.ok(new Object());
    }

}

