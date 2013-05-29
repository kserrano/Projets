#include "kernel.h"
#include "system_m.h"
#include "interrupt.h"
#include <stdlib.h>
#include <stdio.h>

// Maximum number of semaphores.
#define MAXLOCK 3
// Maximum number of processes.
#define MAXPROCESS 5
#define MAXCONDITION 6

typedef struct {
    int next;
    Process p;
    int priority;
} ProcessDescriptor;


// pour libre: 0 = libre, 1 = fermé
typedef struct {
    int waitingList;
    int libre;
    int id;
} LockDescriptor;

typedef struct {
	int conditionID;
	int conditionWaitingList;
	int verrouID;
	int timeout;
} Condition;


// Global variables

// Pointer to the head of list of ready processes
int readyList1 = -1;
int readyList2 = -1;


// list of process descriptors
ProcessDescriptor processes[MAXPROCESS];
int nextProcessId = 0;

// list of ConditionID
Condition condition[MAXCONDITION];
int nextConditionID = 0;
int deadLine = 0;
int byClock = 0;


// list of lock descriptors
LockDescriptor locks[MAXLOCK];
int libre = 0;
int nextLockID = 0;

// process
Process clockProcess = NULL;
Process videProcess = NULL;

//
int currentTime=0;

/***********************************************************
 ***********************************************************
            Utility functions for list manipulation
************************************************************
* **********************************************************/

// add element to the tail of the list
void addLast(int* list, int processId) {

    if (*list == -1){
        // list is empty
        *list = processId;
    }
    else {
        int temp = *list;
        while (processes[temp].next != -1){
            temp = processes[temp].next;
        }
        processes[temp].next = processId;
        processes[processId].next = -1;
    }

}

// add element to the head of list
void addFirst(int* list, int processId){

    if (*list == -1){
        *list = processId;
    }
    else {
        processes[processId].next = *list;
        *list = processId;
    }
}

// remove element that is head of the list
int removeHead(int* list){
    if (*list == -1){
        printf("List is empty!");
        return(-1);
    }
    else {
        int head = *list;
        int next = processes[*list].next;
        processes[*list].next = -1;
        *list = next;
        return head;
    }
}

// returns head of the list
int head(int* list){
    if (*list == -1){
        printf("List is empty!\n");
        return(-1);
    }
    else {
        return *list;
    }
}

/***********************************************************
 ***********************************************************
                    Kernel functions
************************************************************
* **********************************************************/

void creerProcessus (void (*f), int stackSize, int priority) {
    if (nextProcessId == MAXPROCESS){
        printf("Error: Maximum number of processes reached!\n");
        exit(1);
    }

    Process process;
    int* stack = malloc(stackSize);
    process = newProcess(f, stack, stackSize, priority);
    processes[nextProcessId].next = -1;
    processes[nextProcessId].p = process;
    // add process to the list of ready Processes
    if(processes[nextProcessId].priority == 1) {
    addLast(&readyList1, nextProcessId);
    } else {
    	addLast(&readyList2, nextProcessId);
    }
    nextProcessId++;

}



void verrouiller(int verrouID){

    if (locks[verrouID].libre == 0){
    	locks[verrouID].libre = 1;
    }
    if (locks[verrouID].libre == 1){
    	if (readyList2 > -1) {
			int p0 = removeHead(&readyList2);
			addLast(&locks[verrouID].waitingList, p0);
			Process process = processes[head(&readyList2)].p;
			transfer(process);
    	} else if (readyList1 > -1) {
    		int p = removeHead(&readyList1);
			addLast(&(locks[verrouID].waitingList), p);
			Process process = processes[head(&readyList1)].p;
			transfer(process);
    	}
    }
}

void deverouiller(int verrouID){
    locks[verrouID].libre =  0;
	int p =  removeHead(&(locks[verrouID].waitingList));
	if(processes[p].priority==2){
		addLast(&readyList2, p);
	}
	if(processes[p].priority==1){
		addLast(&readyList1, p);
	}
	signal(condition[verrouID]);
}

void clock(){
	init_clock();
	while(1){
		Process process;
		// si vide tourne dans le vide
		// sinon prendre de la liste
		if(theHead()==-1){
			process = videProcess;
	} else {
		process = processes[theHead()].p;
		}
		iotransfer(process);

		currentTime++;
		int i =0;
		for(i;i < nextConditionID;i++){
			if(condition[i].deadLine > currentTime){
				signal(i);
				byClock = 1;
			}
		}
	}

}
	int theHead(){
		if(head(&readyList2) != -1){
			return head(&readyList2);
		}else{
			return head(&readyList1);
		}
		}
void start(){

    printf("Starting kernel...\n");
    if (readyList2 > -1){
    	Process process = processes[head(&readyList2)].p;
    	transfer(process);
    }
    if(readyList2 == -1){
    	printf("No process in the ready list 2!\n");
    }
    if (readyList1 == -1){
        printf("Error: No process in the ready list 1!\n");
        exit(1);
    }
    else {
    	Process process1 = processes[head(&readyList1)].p;
    	transfer(process1);
    }

}

int creerVerrou() {
	locks[nextLockID].libre = 0;
	locks[nextLockID].waitingList = -1;
	locks[nextLockID].id = nextLockID;
	return nextLockID = nextLockID+1;
}

int creerCondition(int verrouID) {

	
	deadLine = 0;
	byClock = 0;


	Condition condition0;
	condition0.conditionID = nextConditionID;
	condition0.verrouID = verrouID;
	condition.conditionWaitingList = -1;
	condition[condition0.conditionID] = condition0;
	nextConditionID++;
	return condition0.conditionID;

}

void await(int conditionID) {
	deverouiller(locks[condition[conditionID].verrouID]);
	int process = -1;
	if (readyList2 > -1) {
		process = removeHead(&readyList2);
	}
	else if (readyList1 > -1){
		process = removeHead(&readyList1);
	}
	addLast(* condition[conditionID].conditionWaitingList, process);
	if (readyList2 > -1) {
		transfer(readyList2);
	}
	if (readyList1 > -1) {
		transfer(readyList1);
	}
	verrouiller(condition[conditionID].verrouID);
}

void signal(int conditionID){
	if (conditionID > -1) {
		addLast(* locks[condition[conditionID].verrouID].waitingList , removeHead(&condition[conditionID].conditionWaitingList));
	}
}

void signalAll(int conditionID){
	while (condition[conditionID].conditionWaitingList =! -1) {
		addLast(* locks[condition[conditionID].verrouID].waitingList , removeHead(&condition[conditionID].conditionWaitingList));
	}
	verrouiller(condition[conditionID].verrouID);
}

int timedAwait(int conditionID, int time){
	if (time == 0) {
		await(conditionID);
	}else{
		//set timeOut
		condition[ConditionID].deadLine = time + currentTime;
		await(conditionID)
		if(condition[ConditionID].byClock){
			allowInterrupts()
				condition[ConditionID].byClock = 0;
			return 0;
		else {
			allowInterrupts()
			return 1;
		}
		}
	}
	return 1;
}

/*
 * kernel.c
 *
 *  Created on: 7 mai 2013
 *      Author: Quentin
 */


