package com.example.TaskApp.controller;

import com.example.TaskApp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.TaskApp.repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping
    public Task create(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id){
        return taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"+ id));
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task taskDetails){
        Task task=taskRepository.findById(id).orElseThrow();
        task.setTitle(taskDetails.getTitle());
        task.setdescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public  String deleteByID(@PathVariable Long id){
        taskRepository.deleteById(id);
        return "Task Deleted";
    }

    @DeleteMapping
    public String delete(){
        taskRepository.deleteAll();
        return "All Tasks Deleted";
    }
}
