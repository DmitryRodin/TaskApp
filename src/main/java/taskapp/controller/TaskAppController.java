package taskapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taskapp.model.Task;
import taskapp.model.snapshots.CreationTaskRequest;
import taskapp.model.snapshots.EditTaskRequest;
import taskapp.model.snapshots.FindTaskRequest;
import taskapp.service.TaskService;

import java.util.List;

@Controller
public class TaskAppController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Task> find(@RequestBody FindTaskRequest request) {
        return taskService.find(request);
    }

    @RequestMapping(value ="/create",method = RequestMethod.POST)
    public Long create(@RequestBody CreationTaskRequest request){
        return taskService.create(request);
    }

    @RequestMapping(value ="/edit", method = RequestMethod.POST)
    public void edit(@RequestBody EditTaskRequest request){
        taskService.edit(request);
    }

    @RequestMapping(value = "/delete}",method = RequestMethod.DELETE)
    public void delete(@RequestParam Long taskId){
        taskService.delete(taskId);
    }

    @RequestMapping(value = "/assign/{taskId}", method = RequestMethod.POST)
    public void assign(@RequestParam Long userId, @PathVariable(value = "taskId") Long taskId){
        taskService.assignTask(userId, taskId);
    }
}
