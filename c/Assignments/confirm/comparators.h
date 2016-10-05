/******************************************************************************/
/* File name  : comparator.h                                                  */
/* Description: Header file to declare prototypes of different compare func-  */
/*              tions invoked by qsort                                        */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016-07-23                                                    */
/******************************************************************************/
#ifndef COMPARATORS_H
#define COMPARATORS_H

/******************************************************************************/
/* Declaration of comparing functions                                         */
/******************************************************************************/
/* Compare functions with the same prototype as the standard compare function. 
 * parameters: const void *p1 - pointer to the first string
 *             const void *p2 -  pointer to the second string
 * return    : integer. Negative if the string pointed to by p1 goes first than 
 *             the one pointed to by p2; otherwise positive. 0 if they are equal
 *             */
 
/* Case sensitive, with leading white space*/
int case_whitespace_compare(const void *p1, const void *p2);

/* Case insensitive, with leading white space*/
int nocase_whitespace_compare(const void *p1, const void *p2);

/* Case sensitive, without leading white space*/
int case_nowhitespace_compare(const void *p1, const void *p2); 

/* Case insensitive, without leading white space*/
int nocase_nowhitespace_compare(const void *p1, const void *p2); 

#endif
