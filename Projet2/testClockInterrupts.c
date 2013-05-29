<pre><div class="text_to_html">/*
 * testIOtransfer.c
 *
 *  Created on: Apr 23, 2012
 *      Author: root
 */

#include <stdio.h> 
#include <stdlib.h> 
#include "system_m.h"
#include "interrupt.h"

// Global variables

int counter;
Process p1=NULL;
Process p2=NULL;

void t1(){
    printf("Process 1...\n");
    while(1){
    	iotransfer(p2,0);
        counter++;
        printf("Process 1, counter = %d\n", counter);
        if(counter > 10){
            printf("Exiting with counter = %d\n", counter);
            exit(1);
        }

    }
}

void t2(){
    printf("Process 2...\n");
    while(1){}
}


int main() {
  printf("Main started...\n");
  int stackSize = 10024;
  int* stack1 = malloc(stackSize);
  p1 = newProcess(t1, stack1, stackSize);

  int* stack2 = malloc(stackSize);
  p2 = newProcess(t2, stack2, stackSize);

  init_clock();
  
  transfer(p1);
  return 0;

}













</div></pre>