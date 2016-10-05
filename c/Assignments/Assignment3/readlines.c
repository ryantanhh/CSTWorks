/******************************************************************************/
/* File name  : readlines.c                                                   */
/* Description: Implementation of functions to read lines of text from files  */
/*              into dynamically allocated memory.                            */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016-07-23                                                    */
/******************************************************************************/

/******************************************************************************/
/* Preprocesors: header files                                                 */
/******************************************************************************/
#include <stdlib.h>
#include <string.h>
#include "readlines.h"
#include "macros.h"


/******************************************************************************/
/* Defination of functions                                                    */
/******************************************************************************/
/* Defination of readlines */
lines_t readlines(FILE *fp) {
	/* Static object of lines_t to stored texts read in from multiple files */
	static lines_t lines = {NULL,0};

	/* Static size_t variable to keep track of the amount of available string
	 * pointers */
	static size_t maxnu = 0;

	/* Buffer to store a line of text read in each time */
	char buffer[LINESIZE];
	char **temp;
	/* Loop until EOF*/
	while (fgets(buffer, LINESIZE, fp)){
		/*If available string pointers are used up, allocate more */
		if (lines.nused == maxnu) {
			if(maxnu == 0){	
				maxnu = 1;
			}
			else { 
				maxnu *= 2;
			}
			temp = (char**)realloc(lines.data, maxnu * sizeof(char*));
			
			if(temp == NULL){
				fprintf(stderr, "Memory allocated failed!");
				freelines(lines);
				exit(MEMORY_ALLOCATED_FAILURE);
			}
#ifdef DEBUG
			/* Print memory allocation information in debug mode */
				fprintf(stderr, "\n*");
#endif
			lines.data = temp;
		}
		/* Allocate memory to store the string */
		lines.data[lines.nused] = (char*)malloc((strlen(buffer) + 1) * sizeof(char));

		if (lines.data[lines.nused] == NULL){
			fprintf(stderr, "Memory allocated failed!");
			freelines(lines);
			exit(MEMORY_ALLOCATED_FAILURE);
		}

#ifdef DEBUG
		/* Print the amount of memory allocated in debug mode*/
		fprintf(stderr, "\n>%lu", strlen(buffer) + 1);
#endif

		/* Copy the string to the dynamically allocated memory 
		 * and increment the line numbers*/
		strcpy(lines.data[lines.nused++], buffer);
	}
	return lines;
}

/* Defination of freelines */
void freelines(lines_t lines) {
	size_t i;
	/* If no dynamic memory allocated, return */
	if (lines.nused == 0)
		return;
	
	for (i = 0; i < lines.nused; i++){
			free(lines.data[i]);
#ifdef DEBUG
			/* Print free memory infomation in debug mode*/
			fprintf(stderr,"\n<");
#endif
	}
		free(lines.data);
#ifdef DEBUG
		/* Print free memory infomation in debug mode*/
		fprintf(stderr,"\n<");
#endif

	/* Reset line number of the lines_t object*/		
	lines.nused = 0;
}

/* Defination of reverse_lines */
void reverse_lines(lines_t lines){
	size_t i, j;
	char *temp;

	for (i = 0, j = lines.nused - 1; i < j; i++, j--){
		temp = lines.data[i];
		lines.data[i] = lines.data[j];
		lines.data[j] = temp;
	}
}
