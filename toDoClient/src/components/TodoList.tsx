import React, { useState, useEffect } from 'react';
import TodoForm from './TodoForm';
import Todo from './Todo';
import { getResponse, Status, Task } from '../types/Model';

function TodoList() {
  const [todos, setTodos] = useState<Task[]>([]);
  const [selectedTodo, setSelectedTodo] = useState<Task|null>(null);

  useEffect(()=>{
    fetch("http://localhost:8080/api/task").
    then(res=>res.json()).
    then((res: getResponse) => {
      setTodos(res.content);
    });
  }, []);

  const addTodo = (todo: Task) => {
    if (!todo.taskName || /^\s*$/.test(todo.taskName)) {
      return;
    }

    const newTodos = [todo, ...todos];

    setTodos(newTodos);

    fetch("http://localhost:8080/api/task", {
    method: "POST",
    body: JSON.stringify(todo),
    headers: {
        "Content-type": "application/json; charset=UTF-8",
    }}).then(response => console.log(response));
  };

  const updateTodo = (todoId: number, newValue: Task) => {
    if (!newValue.taskName || /^\s*$/.test(newValue.taskName)) {
      return;
    }
    setTodos(prev => prev.map(item => (item.taskId === todoId ? newValue : item)));
    
    fetch("http://localhost:8080/api/task", {
    method: "POST",
    body: JSON.stringify(newValue),
    headers: {
        "Content-type": "application/json; charset=UTF-8",
    }}).then(response => console.log(response));
};

  const removeTodo = (id: Number) => {
    const removedArr = [...todos].filter(todo => todo.taskId !== id);

    setTodos(removedArr);

    var url = new URL("http://localhost:8080/api/task");
    url.searchParams.append("id",id.toString());  
    fetch(url.toString(), {
      method: "DELETE",
    }).then(response => console.log(response));
  };

  const completeTodo = (id: Number) => {
    let updatedTodos = todos.map(todo => {
      if (todo.taskId === id) {
        todo.status = todo.status==Status.ACTIVE?Status.COMPLETED:Status.ACTIVE;
      }
      fetch("http://localhost:8080/api/task", {
      method: "POST",
      body: JSON.stringify(todo),
      headers: {
          "Content-type": "application/json; charset=UTF-8",
      }}).then(response => console.log(response));
      return todo;
    });
    setTodos(updatedTodos);
  };

  const selectTodo = (id?:number) => {
    if (id === null)setSelectedTodo(null);
    let selectedTask: Task;
    for (var todo of todos){
      if (todo.taskId === id){
        setSelectedTodo(todo);break;
      }
    }
  }

  return (
    <>
      <h1>What's the Plan for Today?</h1>
      <TodoForm addTodo={addTodo} updateTodo={updateTodo} selectTodo={selectTodo} todo={selectedTodo}/>
      <Todo
        todos={todos}
        completeTodo={completeTodo}
        removeTodo={removeTodo}
        updateTodo={updateTodo}
        selectTodo={selectTodo}
      />
    </>
  );
}

export default TodoList;
