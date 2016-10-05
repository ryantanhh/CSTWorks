/******************************************************************************/
/* File name  : readlines.h                                                   */
/* Description: Header file to declare prototypes of functions to read lines  */
/*              of text from files and store them into dynamically allocated  */
/*              memory                                                        */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016-07-23                                                    */
/******************************************************************************/

#ifndef READLINES_H_
#define READLINES_H_
/******************************************************************************/
/* Preporcesor: header files                                                  */
/******************************************************************************/
#include <stdio.h> 

/******************************************************************************/
/* Declaration of data type and function prototypes in reading lines          */
/******************************************************************************/

typedef struct {
	char **data;	/* dynamic array of lines */
	size_t nused;	/* number of lines in the dynamic array */
} lines_t;

lines_t readlines(FILE *fp);
void freelines(lines_t lines); 
void reverse_lines(lines_t lines);

#endif
