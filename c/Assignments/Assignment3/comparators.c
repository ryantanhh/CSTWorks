/******************************************************************************/
/* File name  : comparator.c                                                  */
/* Description: Implementation of different compare functions related to dif- */
/*              ferent sorting options.                                       */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016/07/23                                                    */
/******************************************************************************/

/******************************************************************************/
/* Preprocesors: header files                                                 */
/******************************************************************************/
#include <ctype.h>
#include <string.h>
#include "comparators.h"

/******************************************************************************/
/* Declaration of helper funcitons used in the compare functions              */
/******************************************************************************/

/* Helper function to skip leading white spaces
 * parameter: const char *s - a string to be read 
 * return   : char* - a pointer pointing to the first none-whitespace character  
 *                    in the string */
static char* skipws(const char *s);

/* Helper function to compare string case insensitively
 * parameters: const char *s1, *s2 - two strings to be compared 
 * return    : integer. negative if s1 is less than s2, positive if s1 is  
               greater than s2. 0 if the two string are equal case insen-
			   sitively */
static int stricmp(const char *s1, const char *s2);

/******************************************************************************/
/* Defination of comparing functions                                          */
/******************************************************************************/
/* Compare two strings in asscending order, case sensitive and with leading 
 * white spaces */
int case_whitespace_compare(const void *p1, const void *p2){
	const char *a = *(char* const *)p1;
	const char *b = *(char* const *)p2;
	return strcmp(a, b);
}

/* Compare two strings in asscending order, case insensitive and with leading 
 * white spaces */
int nocase_whitespace_compare(const void *p1, const void *p2){
	const char *a = *(char* const *)p1;
	const char *b = *(char* const *)p2;
	return stricmp(a, b);
}

/* Compare two strings in asscending order, case sensitive and without leading 
 * white spaces */
int case_nowhitespace_compare(const void *p1, const void *p2){
	const char *a = *(char* const *)p1;
	const char *b = *(char* const *)p2;
	a = skipws(a);
	b = skipws(b);
	return strcmp(a, b);
} 

/* Compare two string in asscending order, case insensitive and without leading 
 * white spaces */
int nocase_nowhitespace_compare(const void *p1, const void *p2){
	const char *a = *(char* const *)p1;
	const char *b = *(char* const *)p2;
	a = skipws(a);
	b = skipws(b);
	return stricmp(a, b);
}

/* Defination of function skipws */
static char* skipws(const char *s){
	const char *p;
	for (p = s; *p != '\n'; p++)
		if (!isspace(*p))
			break;
	return (char*) p;
}

/* Defination of function stricmp */
static int stricmp(const char *s1, const char *s2){
	const char *p = s1;
	const char *q = s2;
	for (; *p != '\0' && *q != '\0'; p++, q++)
		if (tolower(*p) != tolower(*q))
			break;
	return tolower(*p) - tolower(*q);
}
