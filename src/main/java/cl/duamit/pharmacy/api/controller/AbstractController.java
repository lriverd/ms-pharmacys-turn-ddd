package cl.duamit.pharmacy.api.controller;

import cl.duamit.pharmacy.core.Workflow;
import cl.duamit.pharmacy.core.WorkflowExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * @author Luis Riveros
 * @version 1.0.0 - 04-08-2020
 * @since 1.0.0 - 04-08-2020
 */
public class AbstractController {

    protected static final String TOKEN_KEY = "JWT";

    @Autowired
    private WorkflowExecuter workflowExecuter;

    public ResponseEntity<Object> execute(Workflow workflow) throws Exception {
        return this.workflowExecuter.execute(workflow);
    }

    public WorkflowExecuter getWorkflowExecuter() {
        return workflowExecuter;
    }

    public void setWorkflowExecuter(WorkflowExecuter workflowExecuter) {
        this.workflowExecuter = workflowExecuter;
    }
}
