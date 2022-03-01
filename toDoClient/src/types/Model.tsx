import { Key } from "react";

export interface getResponse {
    content: Task[],
    pageable: any[],
    last: boolean,
    totalPages: number,
    totalElements: number,
    size: number,
    number: number,
    sort: {
        empty: boolean,
        sorted: boolean,
        unsorted: boolean
    },
    first: boolean,
    numberOfElements: number,
    empty: boolean
}

export enum Status{
    ACTIVE,
    COMPLETED
}

export interface Task{
    taskId: number;
    taskName: string;
    description: string;
    taskDate: Date;
    status: Status;
}