/******************************************************************************/
/* File name  : macros.h                                                      */
/* Description: Header file to define constants which would be used in multi- */
/*              ple files of the program                                      */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016-07-23                                                    */
/******************************************************************************/
#ifndef MACROS_H_
#define MACROS_H_ 

/******************************************************************************/
/* Customized error code of the programe                                      */
/******************************************************************************/
#define FILE_OPEN_ERROR 1 
#define FILE_CLOSE_ERROR 2
#define INVALID_ARGUMENT 3
#define MEMORY_ALLOCATED_FAILURE 4

/******************************************************************************/
/* Mask code to calculate options                                             */
/******************************************************************************/
#define IGNORE_CASE_MASK 1u
#define IGNORE_LEADING_WHITESPACE_MASK 2u
#define REVERSE_MASK 4u

/******************************************************************************/
/* BUFER SIZE CONSTANTS                                                       */
/******************************************************************************/
#define LINESIZE 512 
#define MESSAGESIZE 1024

#endif
