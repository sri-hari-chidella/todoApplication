import React, { useState } from 'react';
import { RiCloseCircleLine } from 'react-icons/ri';
import { TiEdit } from 'react-icons/ti';
import { Status, Task } from '../types/Model';

export interface TodoProps{
  todos: Task[],
  completeTodo: (id: Number) => void,
  removeTodo: (id: Number) => void,
  updateTodo: (todoId: number, newValue: Task) => void,
  selectTodo: (id: number) => void
}

const Todo: React.FC<TodoProps> = ({todos, completeTodo, removeTodo, updateTodo, selectTodo}) => {
  return (
    <>
      {todos.map((todo, index) => (
      <div
        className={todo.status === Status.COMPLETED ? 'todo-row complete' : 'todo-row'}
        key={index}
      >
        <div key={todo.taskId} onClick={() => completeTodo(todo.taskId)}>
          {todo.taskName}
        </div>
        <div className='icons'>
          <RiCloseCircleLine
            onClick={() => removeTodo(todo.taskId)}
            className='delete-icon'
          />
          <TiEdit
            onClick={() => selectTodo(todo.taskId)}
            className='edit-icon'
          />
        </div>
      </div>
      ))}
    </>
  );
};

export default Todo;
