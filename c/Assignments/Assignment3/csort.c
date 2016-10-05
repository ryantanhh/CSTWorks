/******************************************************************************/
/* File name  : csort.c                                                       */
/* Description: Comp2511 assignment 3. A program that sorts lines of text in  */
/*              files with different sorting options indicated by command line*/
/*              arguments.                                                    */
/* Student    : Tan, Haihua A00950721                                         */
/* Date       : 2016-07-23                                                    */
/******************************************************************************/

/******************************************************************************/
/* Preprocesors: header files                                                 */
/******************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "macros.h"
#include "readlines.h"
#include "comparators.h"

/******************************************************************************/
/* Declaration of helper functions in main function                           */
/******************************************************************************/
static int parse_command(int argc, char **argv, unsigned *options); 

/******************************************************************************/
/* main function, entry of the program                                        */
/******************************************************************************/
int main(int argc, char *argv[]) {
	/*Customed program error code */
	int status = 0;
	
	/* Integer indicating the beginning of file names in the command 
	 * line arguments*/
	int file_idx;

	/* An unsigned integer to store command line switches in bits:   
	 *    Bit 0: c, ignore cases if set to 1
	 *    Bit 1: w, ignore leading white space if set to 1
	 *    Bit 2: r, sort inreverse order if set to 1
	 *    Other bits: unused */
	unsigned options = 0;

	/* File pointer to manipulate files*/
	lines_t lines; FILE* fp;

	/* An array of function pointers stroes the comparing function pointers.
	 * NOTE 1: The order of the functions is mapped to variable "options" so 
	 *         that the "options" can be used as index to invoke the core-
	 *         sponding function.
	 * NOTE 2: Reversed sorting is implemented by reversing the array after
	 *         normal sorting, so no reversed function is included in this
	 *         array. */

	int (*comparator[4])(const void *p1, const void *p2) = {
		case_whitespace_compare,
		nocase_whitespace_compare,  		
		case_nowhitespace_compare,
		nocase_nowhitespace_compare		
	};

	/* Use the helper function parse_command to split up the options and 
	 * file names. Show help message if not invoked correctly */
	if(!(file_idx = parse_command(argc, argv, &options))){
		fprintf(stderr,
			"Usage: %s [OPTION]... [FILE]...\n"
		    "Reads in lines from stdin or from files, sorts them and writes "
			"the output to stout.\n\n"
			"  -c  ignore case\n"
			"  -w  ignore leading white space\n"
			"  -r  sort in reverse order\n"
			"  --  mark the end of options\n\n"
			"With no FILE, read standard input.\n",
		   argv[0]);
		return INVALID_ARGUMENT; 
	}

#ifdef DEBUG
	/* Print the options information in debug mode */
	fprintf(stderr, 
			"\n-c: %s", 
			(options & IGNORE_CASE_MASK)? "on":"off"
			);
	fprintf(stderr, 
			"\n-w: %s", 
			(options & IGNORE_LEADING_WHITESPACE_MASK)? "on":"off"
			);
	fprintf(stderr, 
			"\n-r: %s", 
			(options & REVERSE_MASK)? "on":"off"
			);
#endif
	
	/* Read from stdin if no file specified */
	if (file_idx == argc)
		lines = readlines(stdin);
	else
		/* otherwise, read from the specified files */
		for (; file_idx<argc; file_idx++){
			if (!(fp = fopen(argv[file_idx],"r"))){
				perror("\nfopen");
				continue;
			}
#ifdef DEBUG
			/* Print openning file infomaition in debug mode */
			fprintf(stderr, "\n^%s", argv[file_idx]);
#endif
			lines = readlines(fp);

			if (fclose(fp) != 0){
				perror("fclose");
				status = FILE_CLOSE_ERROR;
			}

#ifdef DEBUG
			/* Print closing file infomaition in debug mode */
			fprintf(stderr, "\n~%s", argv[file_idx]);
#endif
		}

	if (lines.nused > 0){
		size_t i;

		/* Calculate the index of comparing function in the comparing 
		 * function pointer array by options */
		unsigned cmp_idx = options 
			& (IGNORE_CASE_MASK | IGNORE_LEADING_WHITESPACE_MASK);

		/* Invoke qsort with corespoding comparing function */
		qsort(lines.data,
			lines.nused, 
			sizeof(char*), 
			comparator[cmp_idx]
			);

		/* If reverse option specified, reverse all the lines */	
		if (options & REVERSE_MASK)
			reverse_lines(lines);
		
		/* Print the sorting result */
		for (i = 0; i < lines.nused; i++)
			fprintf(stdout, "%s", lines.data[i]);
	}

	/* Free all the dynamically allocated memory */	
	if (lines.nused >0)
		freelines(lines);

	/* End */
	return status;
}

/******************************************************************************/
/* Definition of helper functions in main function                            */
/******************************************************************************/

/* Helper function to parse the command line arguments, obtaining the options 
 * and the beginning of file names in commanline argumens 
 * parameters: int argc: command line argument from main 
 *             char ** argv: command line argument from main
 *             unsigned *options: pointers to options variable, used to store
 *                                the parsing result.
 * return:     integer representing the begin of file names in the command 
 *             line arguments */
 static int parse_command(int argc, char **argv, unsigned *options) {
	int i;
	for (i = 1; i < argc; i++){
		/* Find the implicit ending of switches, exit if encounted */
		if(argv[i][0] != '-')
			break;
		
		/* Find the explicit ending of switches, exit if encounted */
		if(argv[i][1] == '-'){
			i++;
			break;
		}

		/* Store the switches to the coresponding bits of "options" */
		if (strcmp(argv[i], "-c") == 0){
			*options |= IGNORE_CASE_MASK; 
			continue;
		}
		
		if (strcmp(argv[i], "-w") == 0){
			*options |= IGNORE_LEADING_WHITESPACE_MASK; 
			continue;
		}

		if (strcmp(argv[i], "-r") == 0){
			*options |= REVERSE_MASK; 
			continue;
		}

		/* Failed if not matching the option patterns */
		fprintf(stderr, "Invalid arguments.\n");
		return 0;
	}
	return i;
}

