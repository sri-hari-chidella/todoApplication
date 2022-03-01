import React, { useState, useEffect, useRef } from 'react';
import { Status, Task } from '../types/Model';

export interface TodoFormProps{
  addTodo: (todo: Task) => void,
  updateTodo: (todoId: number, newValue: Task) => void,
  selectTodo: (id?: number) => void,
  todo: Task | null
}

const TodoForm:React.FC<TodoFormProps> = ({addTodo, updateTodo, selectTodo, todo}) => {
  const [input, setInput] = useState(todo === null ? '' : todo?.taskName);
  const [activeTodo, setActiveTodo] = useState(todo);

  useEffect(()=>{
    setInput(todo === null ? '' : todo?.taskName);
    setActiveTodo(todo);
  },[todo]);

  const handleChange = (e:React.ChangeEvent<HTMLInputElement>) => {
    setInput(e.target.value);
  };

  const handleSubmit = (e:any) => {
    e.preventDefault();

    if (activeTodo === null){
      addTodo({
          taskId: Math.floor(Math.random() * 10000),
          description: '',
          taskName: input,
          taskDate: new Date(),
          status: Status.ACTIVE 
      });
    }
    else{
      activeTodo.taskName = input;
      updateTodo(activeTodo.taskId,activeTodo);
    }
    setActiveTodo(null);
    setInput('');
    selectTodo();
  };

  return (
    <form onSubmit={handleSubmit} className='todo-form'>
        <input
          placeholder='Add a todo'
          value={input}
          onChange={handleChange}
          name='text'
          className='todo-input'
        />
        <button onClick={e => handleSubmit(e)} className='todo-button'>
          Submit
        </button>
    </form>
  );
};

export default TodoForm;